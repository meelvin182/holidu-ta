package com.holidu.interview.assignment;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
        "holidu.NYCTreeResource=https://data.cityofnewyork.us/resource/uvpi-gqnh.json?$select=x_sp,y_sp,spc_common",
        "holidu.meterToFeetRatio=3.28084",
        "holidu.filterTemplate=&$where=y_sp <= %s AND y_sp >= %s AND x_sp <= %s AND x_sp >= %s"})
public class ApplicationTestBase {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldLoadContext() {
        assertThat(applicationContext).isNotNull();
    }

}
