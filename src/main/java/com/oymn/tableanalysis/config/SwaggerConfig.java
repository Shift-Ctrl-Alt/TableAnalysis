package com.oymn.tableanalysis.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
//@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {

    //配置 Swagger的Docket的Bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                //.apiInfo(apiInfo())
                //.enable(true) //设置是否启动Swagger
                //.select()
                ////RequestHandlerSelectors，配置要扫描的接口方法
                ////basePackage：指定要扫描的包
                ////any()：扫描全部
                ////none()：都不扫描
                ////withClassAnnotation()：扫描类上的注解——参数是一个注解的反射对象
                ////withMethodAnnotation()：扫描方法上的注解——get post
                //.apis(RequestHandlerSelectors.basePackage("com.oymn.geoinvestigate.controller"))
                //.paths(PathSelectors.ant("/**"))//过滤地址
                //.build();//工厂模式
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oymn.tableanalysis.controller"))
                .paths(PathSelectors.ant("/**"))//过滤地址
                .build()//工厂模式
                .securityContexts(Arrays.asList(securityContext()))
                // ApiKey的name需与SecurityReference的reference保持一致
                .securitySchemes(Arrays.asList(new ApiKey("token", "token", SecurityScheme.In.HEADER.name())));

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("token", authorizationScopes));
    }
    
    //配置Swagger信息   apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact DEFAULT_CONTACT = new Contact("数据平台", null, null);
        return new ApiInfo("数据平台",
                "数据平台",
                "1.0",
                null,
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    
    
}


