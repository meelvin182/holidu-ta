package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.config.EnvProperties;
import com.holidu.interview.assignment.model.SearchBoundaries;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BoundariesProviderService {

    private final EnvProperties envProperties;

    public SearchBoundaries provideBoundaries(TreeAggregationRequest aggregationRequest) {
        BigDecimal radiusInFeet = envProperties.getMeterToFeetRatio().multiply(aggregationRequest.getRadius());
        SearchBoundaries searchBoundaries = new SearchBoundaries();
        searchBoundaries.setXLimitUp(aggregationRequest.getX().add(radiusInFeet));
        searchBoundaries.setXLimitLow(aggregationRequest.getX().subtract(radiusInFeet));
        searchBoundaries.setYLimitLow(aggregationRequest.getY().subtract(radiusInFeet));
        searchBoundaries.setYLimitUp(aggregationRequest.getY().add(radiusInFeet));
        return searchBoundaries;
    }
}
