package com.holidu.interview.assignment.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ErrorResponse {

    private final String errorMessage;
}
