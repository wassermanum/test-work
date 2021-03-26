package com.github.wassermanum.neolab_test_work.checker;

import com.github.wassermanum.neolab_test_work.Action;
import com.github.wassermanum.neolab_test_work.Rule;
import com.github.wassermanum.neolab_test_work.Statement;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Checker {

    private Checker() {
    }

    public static boolean check(Map<String, String> entity, Rule rule) throws CheckerException {
        List<Statement> statements = rule.getStatements();
        AtomicBoolean result = new AtomicBoolean(true);

        statements.forEach(statement -> {
            String stringValue = statement.getStringValue();
            List<String> arrayValue = statement.getArrayValue();
            Action action = statement.getAction();
            String fieldName = statement.getFieldName();
            String actualValue = entity.get(fieldName);
            if (action == null) {
                throw new CheckerException("Поля '" + fieldName + "' в сущностях нет");
            }
            switch (action) {
                case EQUALS:
                    if (stringValue == null) {
                        throw new CheckerException("Для правила 'EQUALS' требуется наличие 'stringValue'");
                    }
                    result.set(result.get() && Objects.equals(actualValue, stringValue));
                    break;
                case NOT_EQUALS:
                    if (stringValue == null) {
                        throw new CheckerException("Для правила 'NOT_EQUALS' требуется наличие 'stringValue'");
                    }
                    result.set(result.get() && !Objects.equals(actualValue, stringValue));
                    break;
                case CONTAINS:
                    if (arrayValue == null) {
                        throw new CheckerException("Для правила 'CONTAINS' требуется наличие 'arrayValue'");
                    }
                    result.set(result.get() && arrayValue.contains(actualValue));
                    break;
                // default не описан, поскольку варианты берутся из enum
            }
        });
        return result.get();
    }
}
