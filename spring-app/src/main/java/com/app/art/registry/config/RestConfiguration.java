package com.app.art.registry.config;

import com.app.art.registry.converters.GenericBigDecimalConverter;
import com.app.art.registry.converters.UserRestConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class RestConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserRestConverter());
        registry.addConverter(new GenericBigDecimalConverter());
    }

}
