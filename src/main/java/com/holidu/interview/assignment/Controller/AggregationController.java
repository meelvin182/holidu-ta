package com.holidu.interview.assignment.Controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Tree aggregator", description = "Aggregate tree by common name")
@RequestMapping(path="/aggregation", produces = MediaType.APPLICATION_JSON_VALUE)
public class AggregationController {

    Logger logger;

    @PostMapping("commonName")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful retrieval of the exchange rate")})
    public ResponseEntity<Map<String, Integer>> aggregateTreeDataByCommonName() {
        Map<String, Integer> tree = new HashMap<>();
        tree.put("My brain", 1);
        logger.info("Hello from test");
        return ResponseEntity.ok(tree);
    }

}
