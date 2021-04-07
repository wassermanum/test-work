package com.github.wassermanum.neolab_test_work;

import java.util.List;

public class Rule {
    private List<Statement> statements;
    private String description;

    public Rule() {
    }

    public Rule(List<Statement> statements, String description) {
        this.statements = statements;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
