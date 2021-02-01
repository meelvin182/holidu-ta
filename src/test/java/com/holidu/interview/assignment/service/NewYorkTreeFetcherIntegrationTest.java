package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.ApplicationTestBase;
import com.holidu.interview.assignment.exceptions.NewYorkTreeFetchingException;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class NewYorkTreeFetcherIntegrationTest extends ApplicationTestBase {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NewYorkTreeFetcher newYorkTreeFetcher;

    @Autowired
    ResourceLoader resourceLoader;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getAggregationMapTest() throws NewYorkTreeFetchingException {

        Resource TEST_RESPONSE = resourceLoader.getResource("new_york_tree_response.json");

        mockServer.expect(requestTo("https://data.cityofnewyork.us/resource/uvpi-gqnh.json?$select=x_sp,y_sp,spc_common&$where=y_sp%20%3C%3D%20211712.08400%20AND%20y_sp%20%3E%3D%20211055.91600%20AND%20x_sp%20%3C%3D%201018890.13400%20AND%20x_sp%20%3E%3D%201018233.96600"))
                .andExpect(method(GET))
                .andRespond(withSuccess(TEST_RESPONSE, APPLICATION_JSON));

        //when
        Map<String, Integer> stringIntegerMap = newYorkTreeFetcher.fetchTrees(new TreeAggregationRequest(BigDecimal.valueOf(1018562.05), BigDecimal.valueOf(211384), BigDecimal.valueOf(100)));

        //then
        mockServer.verify();
        assertThat(stringIntegerMap).hasSize(10);

    }

}