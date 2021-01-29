package com.holidu.interview.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Point {
    private BigDecimal x;
    private BigDecimal y;
}
