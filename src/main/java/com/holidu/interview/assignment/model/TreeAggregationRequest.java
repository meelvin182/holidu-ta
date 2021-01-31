package com.holidu.interview.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeAggregationRequest {

    @NotNull
    private BigDecimal x;
    @NotNull
    private BigDecimal y;
    @NotNull
    private BigDecimal radius;

}


