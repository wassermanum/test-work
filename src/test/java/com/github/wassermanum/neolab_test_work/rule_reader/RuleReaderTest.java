package com.github.wassermanum.neolab_test_work.rule_reader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RuleReaderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = RuleReaderException.class)
    public void shouldThrowExceptionOnWrongJsonSyntax() throws RuleReaderException, IOException {
        File rules = folder.newFile("rules.json");
        FileWriter rulesWriter = new FileWriter(rules);
        rulesWriter.append("qwerty");
        rulesWriter.flush();
        rulesWriter.close();
        RuleReader.read(rules);
    }

    @Test(expected = RuleReaderException.class)
    public void shouldThrowExceptionOnWrongJsonStructure() throws RuleReaderException, IOException {
        File rules = folder.newFile("rules.json");
        FileWriter rulesWriter = new FileWriter(rules);
        rulesWriter.append("{\n" +
                "    123123ccc \"Сколько животных – травоядных\",\n" + ")");
        rulesWriter.flush();
        rulesWriter.close();
        RuleReader.read(rules);
    }

    @Test(expected = RuleReaderException.class)
    public void shouldThrowExceptionOnNonExistingFile() throws RuleReaderException {
        RuleReader.read(new File(""));
    }

}