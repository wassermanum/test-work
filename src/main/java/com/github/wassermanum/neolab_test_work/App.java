package com.github.wassermanum.neolab_test_work;

import com.github.wassermanum.neolab_test_work.checker.Checker;
import com.github.wassermanum.neolab_test_work.checker.CheckerException;
import com.github.wassermanum.neolab_test_work.entity_reader.EntityReader;
import com.github.wassermanum.neolab_test_work.entity_reader.EntityReaderException;
import com.github.wassermanum.neolab_test_work.rule_reader.RuleReader;
import com.github.wassermanum.neolab_test_work.rule_reader.RuleReaderException;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class App {


    public static void main(String[] args) {
        Path entitiesPath;
        try {
            entitiesPath = Paths.get(ClassLoader.getSystemResource("entities.csv").toURI());
        } catch (URISyntaxException e) {
            System.out.println("Невалидный путь к файлу");
            return;
        }
        List<Map<String, String>> entities;
        try {
            entities = EntityReader.read(entitiesPath);
        } catch (EntityReaderException e) {
            System.out.println(e.getMessage());
            return;
        }

        File rulesFile = new File(ClassLoader.getSystemResource("rules.json").getFile());
        List<Rule> rules;
        try {
            rules = RuleReader.read(rulesFile);
        } catch (RuleReaderException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            rules.forEach(rule -> {
                AtomicInteger count = new AtomicInteger(0);
                entities.forEach(entity -> {
                    if (Checker.check(entity, rule)) {
                        count.getAndIncrement();
                    }
                });
                System.out.println(rule.getDescription() + ": " + count.get());
            });
        } catch (CheckerException e) {
            System.out.println(e.getMessage());
        }
    }
}
