package com.holidu.interview.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import com.holidu.interview.assignment.service.NewYorkTreeFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AggregationController.class)
class AggregationControllerIntegrationTest {


    @MockBean
    private NewYorkTreeFetcher newYorkTreeFetcher;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void getAggregationMapTest() throws Exception {
        //given
        String url = "/aggregation/commonName";
        TreeAggregationRequest treeAggregationRequest = new TreeAggregationRequest(BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(1));
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("test", 1);
        when(newYorkTreeFetcher.fetchTrees(any())).thenReturn(testMap);

        //when-then
        mockMvc.perform(post(url).contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(treeAggregationRequest)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"test\":1}")));

    }

}