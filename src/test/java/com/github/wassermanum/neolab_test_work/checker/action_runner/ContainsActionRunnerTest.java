package com.github.wassermanum.neolab_test_work.checker.action_runner;

import com.github.wassermanum.neolab_test_work.Action;
import com.github.wassermanum.neolab_test_work.Statement;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsActionRunnerTest {
    private ActionRunner actionRunner;

    @Before
    public void setUp() {
        actionRunner = new ContainsActionRunner();
    }

    @Test
    public void shouldReturnTrue() throws ActionRunnerException {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Всеядное");
        assertTrue(actionRunner.run(
                entity,
                new Statement("type", null, Arrays.asList("Всеядное", "Травоядное"), null)
        ));
    }

    @Test
    public void shouldReturnFalseOnNonExistingValues() throws ActionRunnerException {
        Map<String, String> entity = new HashMap<>();
        entity.put("type", "Плотоядное");
        assertFalse(actionRunner.run(
                entity,
                new Statement("type", null, Arrays.asList("Всеядное", "Травоядное"), null)
        ));
    }

    @Test
    public void shouldReturnTrueOnNonExistingField() throws ActionRunnerException {
        assertFalse(actionRunner.run(
                new HashMap<>(),
                new Statement("type", null, Arrays.asList("Всеядное", "Травоядное"), Action.EQUALS))
        );
    }

    @Test(expected = ActionRunnerException.class)
    public void shouldThrowExceptionOnNullArrayValue() throws ActionRunnerException {
        actionRunner.run(
                new HashMap<>(),
                new Statement(null, null, null, null));
    }
}