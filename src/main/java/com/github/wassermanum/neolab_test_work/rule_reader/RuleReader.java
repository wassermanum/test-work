package com.github.wassermanum.neolab_test_work.rule_reader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wassermanum.neolab_test_work.Rule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuleReader {

    private RuleReader() {
    }

    public static List<Rule> read(final File file) throws RuleReaderException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, new TypeReference<List<Rule>>() {
            });
        } catch (JsonParseException e) {
            throw new RuleReaderException("Ошибка в синтаксисе JSON", e);
        } catch (JsonMappingException e) {
            throw new RuleReaderException("Прочитанный JSON не соответствует структуре правил", e);
        } catch (IOException e) {
            throw new RuleReaderException("Ошибка чтения файла с правилами", e);
        }
    }
}
