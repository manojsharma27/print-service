package com.ms.printing.integration;

import com.ms.printing.bookprint.Application;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:applicationIT.properties")
@RunWith(SpringRunner.class)
public abstract class PrintServiceITBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintServiceITBase.class);

    @LocalServerPort
    private int port = 0;

    @Autowired
    private SecurityProperties securityProperties;

    protected RestTemplate restTemplate;
    protected TestRestTemplate authenticatedTemplate;
    protected HttpHeaders httpHeaders;

    protected void setupBase() {
        restTemplate = restTemplate();
        authenticatedTemplate = authenticatedTemplate();
        httpHeaders = getHeaders();
    }

    protected String url(String partialUrl) {
        return "http://localhost:" + this.port + partialUrl;
    }

    private TestRestTemplate authenticatedTemplate() {
        TestRestTemplate testRestTemplate = new TestRestTemplate(securityProperties.getUser().getName(), securityProperties.getUser().getPassword());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        messageConverters.add(converter);
        testRestTemplate.getRestTemplate().setMessageConverters(messageConverters);
        return testRestTemplate;
    }

    protected RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    protected HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
