package com.crell;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by chenqi on 16/11/11.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket cnoilApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cnoil")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/cnoil/*.*"))//过滤的接口
                .build()
                .apiInfo(cnoilApiInfo());
    }

    private ApiInfo cnoilApiInfo() {
        ApiInfo apiInfo = new ApiInfo("cnoil API",//大标题
                "cnoil .",//小标题
                "1.0",//版本
                "NO terms of serice",
                "crell",//作者
                "",//链接显示文字
                ""//网站链接
        );

        return apiInfo;
    }
}
