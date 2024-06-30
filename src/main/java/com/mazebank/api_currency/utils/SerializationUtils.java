package com.mazebank.api_currency.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SerializationUtils {

    public <T> T serialize(String json, Class<T> classType) {
        try {
            return new ObjectMapper().readValue(json, classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
