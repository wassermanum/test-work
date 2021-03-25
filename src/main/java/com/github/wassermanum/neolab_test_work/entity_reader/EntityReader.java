package com.github.wassermanum.neolab_test_work.entity_reader;

import com.opencsv.CSVReaderHeaderAware;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityReader {

    private EntityReader() {
    }

    public static List<Map<String, String>> read(final Path path) throws EntityReaderException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<Map<String, String>> result = new ArrayList<>();
            try (CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)) {
                Map<String, String> next;
                while ((next = csvReader.readMap()) != null) result.add(next);
                return result;
            } catch (IOException e) {
                throw new EntityReaderException("Ошибка парсинга CSV с сущностями", e);
            }
        } catch (IOException e) {
            throw new EntityReaderException("Ошибка чтения файла с сущностями", e);
        }
    }
}
