package com.github.wassermanum.neolab_test_work.checker.action_runner;

import com.github.wassermanum.neolab_test_work.Action;
import com.github.wassermanum.neolab_test_work.Statement;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotEqualsActionRunnerTest {

    private ActionRunner actionRunner;

    @Before
    public void setUp() {
        actionRunner = new NotEqualsActionRunner();
    }

    @Test
    public void shouldReturnTrue() throws ActionRunnerException {
        Map<String, String> entity = new HashMap<>();
        entity.put("weight", "Легкое");
        assertTrue(actionRunner.run(entity, new Statement(
                "weight", "Тяжелое", null, null))
        );
    }

    @Test
    public void shouldReturnFalseOnEqualValues() throws ActionRunnerException {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Всеядное");
        assertFalse(actionRunner.run(
                entity,
                new Statement("type", "Всеядное", null, Action.NOT_EQUALS))
        );
    }

    @Test
    public void shouldReturnTrueOnNonExistingField() throws ActionRunnerException {
        assertTrue(actionRunner.run(
                new HashMap<>(),
                new Statement("type", "Всеядное", null, Action.EQUALS))
        );
    }

    @Test(expected = ActionRunnerException.class)
    public void shouldThrowExceptionOnNullStringValue() throws ActionRunnerException {
        actionRunner.run(
                new HashMap<>(),
                new Statement(null, null, null, null)
        );
    }
}