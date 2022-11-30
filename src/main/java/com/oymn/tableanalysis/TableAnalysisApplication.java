package com.oymn.tableanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class TableAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableAnalysisApplication.class, args);
    }

}
