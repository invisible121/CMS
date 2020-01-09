package luoma.test_cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class MvcConfig implements WebMvcConfigurer {

    @Override

    public void addCorsMappings(CorsRegistry registry) {

// 这里匹配了所有的URL，允许所有的外域发起跨域请求，允许外域发起请求任意HTTP Method，允许跨域请求包含任意的头信息。

        registry.addMapping("/**")

                .allowedHeaders("*")

                .allowedMethods("*")

                .allowedOrigins("*")

                .allowCredentials(true);

    }

}
