package com.github.wassermanum.neolab_test_work.checker;

import com.github.wassermanum.neolab_test_work.Action;
import com.github.wassermanum.neolab_test_work.Rule;
import com.github.wassermanum.neolab_test_work.Statement;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckerTest {

    @Test
    public void shouldReturnTrue() {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Всеядное");
        List<Statement> statements = new ArrayList<>();
        statements.add(new Statement("type", "Всеядное", null, Action.EQUALS));
        Rule rule = new Rule(statements, "");
        assertTrue(Checker.check(entity, rule));
    }

    @Test
    public void shouldReturnFalse() {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Плотоядное");
        List<Statement> statements = new ArrayList<>();
        statements.add(new Statement("type", "Всеядное", null, Action.EQUALS));
        Rule rule = new Rule(statements, "");
        assertFalse(Checker.check(entity, rule));
    }

    @Test(expected = CheckerException.class)
    public void shouldThrowExceptionOnNullAction() {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Плотоядное");
        List<Statement> statements = new ArrayList<>();
        statements.add(new Statement(null, null, null, null));
        Rule rule = new Rule(statements, null);
        Checker.check(entity, rule);
    }

    @Test(expected = CheckerException.class)
    public void shouldThrowExceptionOnNullStatements() {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Плотоядное");
        Rule rule = new Rule(null, null);
        Checker.check(entity, rule);
    }
}

