package com.holidu.interview.assignment.controller;

import com.holidu.interview.assignment.exceptions.NewYorkTreeFetchingException;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import com.holidu.interview.assignment.service.NewYorkTreeFetcher;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Tree aggregator", description = "Aggregates tree")
@RequestMapping(path = "/aggregation", produces = MediaType.APPLICATION_JSON_VALUE)
public class AggregationController {

    NewYorkTreeFetcher newYorkTreeFetcher;

    @PostMapping("commonName")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tree aggregation")})
    public ResponseEntity<Map<String, Integer>> aggregateTreeDataByCommonName(@RequestBody TreeAggregationRequest treeAggregationRequest) throws NewYorkTreeFetchingException {
        return ResponseEntity.ok(newYorkTreeFetcher.fetchTrees(treeAggregationRequest));
    }

}
