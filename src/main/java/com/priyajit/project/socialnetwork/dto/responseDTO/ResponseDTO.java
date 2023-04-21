package com.priyajit.project.socialnetwork.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class ResponseDTO {

    private final ObjectWriter objectWriter;

    public ResponseDTO() {

        ObjectMapper objectMapper = new ObjectMapper();
        // ignore null attributes while mapping to json string
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    public String toString() {

        try {
            return objectWriter.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
