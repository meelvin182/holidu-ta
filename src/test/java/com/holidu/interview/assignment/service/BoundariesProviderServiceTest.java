package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.ApplicationTestBase;
import com.holidu.interview.assignment.model.SearchBoundaries;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BoundariesProviderServiceTest extends ApplicationTestBase {

    @Autowired
    BoundariesProviderService boundariesProviderService;

    @Test
    void provideBoundaries() {

        //Given
        TreeAggregationRequest treeAggregationRequest = new TreeAggregationRequest(BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(1));

        //When
        SearchBoundaries searchBoundaries = boundariesProviderService.provideBoundaries(treeAggregationRequest);

        //Then
        assertThat(searchBoundaries.getXLimitUp()).isEqualTo("8.28084");
        assertThat(searchBoundaries.getXLimitLow()).isEqualTo("1.71916");
        assertThat(searchBoundaries.getYLimitUp()).isEqualTo("8.28084");
        assertThat(searchBoundaries.getYLimitLow()).isEqualTo("1.71916");

    }
}