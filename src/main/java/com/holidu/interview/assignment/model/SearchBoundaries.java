package com.holidu.interview.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBoundaries {
    private BigDecimal xLimitUp;
    private BigDecimal xLimitLow;
    private BigDecimal yLimitUp;
    private BigDecimal yLimitLow;
}
