package com.hb.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;


public class StringUtils {
    public static String toSnakeCase(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement).toLowerCase();
    }

    public static JsonNode objectFromCamelCaseToSnakeCase(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        try {
            return mapper.readTree(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            String jsonString = "{\"key\":\"value\", \"nested\":{\"nested_key\":\"nested_value\"}}";

            return mapper.readTree(jsonString);
        }

    }
    public static String upperCaseFirstLetter(String text) {
        return text == null || text.isEmpty() ? text : text.substring(0, 1).toUpperCase() + text.substring(1);
    }
    public static String toCamelCase(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (int i = 0; i < snakeCase.length(); i++) {
            char c = snakeCase.charAt(i);
            if (c == '_') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                camelCase.append(c);
            }
        }
        return camelCase.toString();
    }
    public static String[] toCamelCase(String[] values) {
        for(int i = 0; i < values.length; i++){
            values[i] = toCamelCase(values[i]);
        }
        return values;
    }
    public static String[] toLowerCase(String[] values){
        String[] convertedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            convertedValues[i] = values[i].toLowerCase();
        }
        return convertedValues;
    }
}
