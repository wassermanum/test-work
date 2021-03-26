package com.github.wassermanum.neolab_test_work.checker.action_runner;

import com.github.wassermanum.neolab_test_work.Statement;

import java.util.Map;

public interface ActionRunner {
    boolean run(Map<String, String> entity, Statement statement) throws ActionRunnerException;
}
