package com.aldeamo.app.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorRS {

    private String message;
    private int status;
}
