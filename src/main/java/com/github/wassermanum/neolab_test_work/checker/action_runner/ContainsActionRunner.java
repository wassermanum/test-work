package com.github.wassermanum.neolab_test_work.checker.action_runner;

import com.github.wassermanum.neolab_test_work.Statement;

import java.util.List;
import java.util.Map;

public class ContainsActionRunner implements ActionRunner {
    @Override
    public boolean run(Map<String, String> entity, Statement statement) throws ActionRunnerException {
        List<String> arrayValue = statement.getArrayValue();
        String actualValue = entity.get(statement.getFieldName());
        if (arrayValue == null) {
            throw new ActionRunnerException("Для правила 'CONTAINS' требуется наличие 'arrayValue'");
        }
        return arrayValue.contains(actualValue);
    }
}
