package com.github.wassermanum.neolab_test_work.checker.action_runner;

import com.github.wassermanum.neolab_test_work.Statement;

import java.util.Map;
import java.util.Objects;

public class EqualsActionRunner implements ActionRunner {
    @Override
    public boolean run(Map<String, String> entity, Statement statement) throws ActionRunnerException {
        String stringValue = statement.getStringValue();
        String actualValue = entity.get(statement.getFieldName());
        if (stringValue == null) {
            throw new ActionRunnerException("Для правила 'NOT_EQUALS' требуется наличие 'stringValue'");
        }
        return Objects.equals(actualValue, stringValue);
    }
}
