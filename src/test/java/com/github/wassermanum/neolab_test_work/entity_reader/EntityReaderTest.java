package com.github.wassermanum.neolab_test_work.entity_reader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EntityReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldReadSuccessfully() throws IOException, EntityReaderException {
        File entities = folder.newFile("entities.csv");
        FileWriter entitiesWriter = new FileWriter(entities);
        entitiesWriter.append("weight,height,type\n" +
                "Легкое,Маленькое,Всеядное");
        entitiesWriter.flush();
        entitiesWriter.close();
        List<Map<String, String>> actualEntities = EntityReader.read(entities.toPath());
        assertEquals("Всеядное", actualEntities.get(0).get("type"));
    }

    @Test(expected = EntityReaderException.class)
    public void shouldThrowExceptionOnEmptyFile() throws EntityReaderException, IOException {
        File entities = folder.newFile("entities.csv");
        EntityReader.read(entities.toPath());
    }

    @Test(expected = EntityReaderException.class)
    public void shouldThrowExceptionOnWrongCsvSyntax() throws EntityReaderException, IOException {
        File entities = folder.newFile("entities.csv");
        FileWriter entitiesWriter = new FileWriter(entities);
        entitiesWriter.append("weight,height,type\n" +
                "ЛегкоеМаленькоеssssВсеядное");
        entitiesWriter.flush();
        entitiesWriter.close();
        EntityReader.read(entities.toPath());
    }

    @Test(expected = EntityReaderException.class)
    public void shouldThrowExceptionOnNonExistingFile() throws EntityReaderException {
        EntityReader.read(Paths.get("lalala"));
    }
}