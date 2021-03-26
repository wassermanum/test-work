package com.github.wassermanum.neolab_test_work.checker;

import com.github.wassermanum.neolab_test_work.Action;
import com.github.wassermanum.neolab_test_work.Rule;
import com.github.wassermanum.neolab_test_work.Statement;
import com.github.wassermanum.neolab_test_work.checker.action_runner.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Checker {

    private static final Map<Action, ActionRunner> actionRunnersForActions;

    static {
        actionRunnersForActions = new HashMap<>();
        actionRunnersForActions.put(Action.EQUALS, new EqualsActionRunner());
        actionRunnersForActions.put(Action.NOT_EQUALS, new NotEqualsActionRunner());
        actionRunnersForActions.put(Action.CONTAINS, new ContainsActionRunner());
    }

    private Checker() {
    }

    public static boolean check(Map<String, String> entity, Rule rule) throws CheckerException {
        List<Statement> statements = rule.getStatements();
        AtomicBoolean result = new AtomicBoolean(true);

        statements.forEach(statement -> {
            Action action = statement.getAction();
            String fieldName = statement.getFieldName();
            if (action == null) {
                throw new CheckerException("Поля '" + fieldName + "' в сущностях нет");
            }

            ActionRunner actionRunner = actionRunnersForActions.get(action);
            if (actionRunner == null) {
                throw new CheckerException("Для действия '" + action.toString() + "' нет обработчика");
            }
            try {
                result.set(result.get() && actionRunner.run(entity, statement));
            } catch (ActionRunnerException e) {
                throw new CheckerException(e.getMessage(), e);
            }
        });
        return result.get();
    }
}
