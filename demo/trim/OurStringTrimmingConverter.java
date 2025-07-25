package com.example.demo.trim;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


@Configuration
public class OurStringTrimmingConverter implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
      
        SimpleModule stringTrimmingModule = new SimpleModule();
        stringTrimmingModule.addDeserializer(String.class, new StringTrimmingDeserializer());

 
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonConverter =
                        (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                objectMapper.registerModule(stringTrimmingModule);
            }
        }
    }
}