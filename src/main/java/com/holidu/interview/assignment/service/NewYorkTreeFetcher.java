package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.config.EnvProperties;
import com.holidu.interview.assignment.exceptions.NewYorkTreeFetchingException;
import com.holidu.interview.assignment.model.Point;
import com.holidu.interview.assignment.model.SearchBoundaries;
import com.holidu.interview.assignment.model.Tree;
import com.holidu.interview.assignment.model.TreeAggregationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ideally this class should implement an interface
 * but I don't see huge advantage of creating interface with only one realization
 */

@Service
@Slf4j
@AllArgsConstructor
public class NewYorkTreeFetcher {

    private final RestTemplate restTemplate;
    private final EnvProperties envProperties;
    private final BoundariesProviderService boundariesProviderService;

    public Map<String, Integer> fetchTrees(TreeAggregationRequest aggregationRequest) throws NewYorkTreeFetchingException {
        String targetUrl = envProperties.getNYCTreeResource();
        SearchBoundaries searchBoundaries = boundariesProviderService.provideBoundaries(aggregationRequest);
        targetUrl = targetUrl.concat(buildAdditionalFilter(searchBoundaries));
        log.info("Sending request to = {}", targetUrl);
        ResponseEntity<List<Tree>> responseEntity =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );

        if (responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null) {
            throw new NewYorkTreeFetchingException("Failed to download trees");
        }

        log.debug("Response entity = {}", responseEntity.getBody());

        List<Tree> trees = responseEntity.getBody();
        removeIfOutsideCircle(trees, envProperties.getMeterToFeetRatio().multiply(aggregationRequest.getRadius()), new Point(aggregationRequest.getX(), aggregationRequest.getY()));

        Map<String, Integer> treeMap = new HashMap<>();

        // Cannot use simple collectors cz of null values => https://www.nurkiewicz.com/2019/03/mapmerge-one-method-to-rule-them-all.html
        trees.forEach(tree ->
                treeMap.merge(tree.getSpc_common() == null ? "EmptyName" : tree.getSpc_common(), 1, Integer::sum));

        return treeMap;
    }

    // Can be extracted to the own service
    public List<Tree> removeIfOutsideCircle(List<Tree> trees, BigDecimal radius, Point center) {
        trees.removeIf(tree ->
                radius.compareTo(
                        getCartesianDistance(center, new Point(new BigDecimal(tree.getX_sp()), new BigDecimal(tree.getY_sp())))) < 0
        );
        // return for tests
        return trees;
    }


    private BigDecimal getCartesianDistance(Point p1, Point p2) {
        log.debug("calculating distance between p1= {} and p2= {}", p1, p2);
        double dx = p2.getX().subtract(p1.getX()).doubleValue();
        double dy = p2.getY().subtract(p1.getY()).doubleValue();
        log.debug("dx = {}, dy = {}", dx, dy);
        double distance = Math.sqrt(dx * dx + dy * dy);
        log.debug("distance={}", distance);
        return BigDecimal.valueOf(distance);
    }

    /**
     * Avoiding downloading whole json, query the square
     *
     * @param boundaries
     * @return
     */
    private String buildAdditionalFilter(SearchBoundaries boundaries) {
        return String.format(envProperties.getFilterTemplate(),
                boundaries.getYLimitUp(),
                boundaries.getYLimitLow(),
                boundaries.getXLimitUp(),
                boundaries.getXLimitLow());
    }

}
