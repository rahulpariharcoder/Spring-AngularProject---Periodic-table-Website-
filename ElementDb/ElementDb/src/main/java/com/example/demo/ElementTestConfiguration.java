package com.example.demo;

import com.example.demo.Domain.Element;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ElementTestConfiguration {
    @Bean
    @Primary
    public Element element() {
        return Mockito.mock(Element.class);
    }
}
