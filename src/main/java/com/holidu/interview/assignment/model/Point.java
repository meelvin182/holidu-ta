package com.holidu.interview.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@AllArgsConstructor
@Value
public class Point {
    BigDecimal x;
    BigDecimal y;
}
