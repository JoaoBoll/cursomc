package com.example.cursomc.resources.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class StandardError implements Serializable {
    private static final long serialVersionUid = 1L;

    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.msg = msg;
        this.status = status;
        this.timeStamp = timeStamp;
    }
}
