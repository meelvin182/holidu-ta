package com.holidu.interview.assignment.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "holidu")
@Configuration("envValues")
@Data
public class EnvProperties {

    private String NYCTreeResource;
    private BigDecimal meterToFeetRatio;
    private String filterTemplate;
}
