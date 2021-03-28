package com.github.wassermanum.neolab_test_work;

import java.util.List;

public class Statement {
    private String fieldName;
    private String stringValue;
    private List<String> arrayValue;
    private Action action;

    public Statement(String fieldName, String stringValue, List<String> arrayValue, Action action) {
        this.fieldName = fieldName;
        this.stringValue = stringValue;
        this.arrayValue = arrayValue;
        this.action = action;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getStringValue() {
        return stringValue;
    }

    public List<String> getArrayValue() {
        return arrayValue;
    }

    public Action getAction() {
        return action;
    }
}