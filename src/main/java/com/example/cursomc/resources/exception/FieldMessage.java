package com.example.cursomc.resources.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldMessage implements Serializable {
    private static final long serialVersionUid = 1L;

    private String fieldName;
    private String fieldMessage;

    public FieldMessage() {

    }

    public FieldMessage(String fieldName, String fieldMessage) {
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

}
