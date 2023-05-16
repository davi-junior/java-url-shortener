package com.example.javainterview.shortener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(
        classes = { URLShortener.class }, loader = AnnotationConfigContextLoader.class
)
public class URLShortenerTest {

    @Autowired
    private URLShortener urlShortener;

    private String url;
    private int hash;

    @BeforeEach
    void setup() {
        url = "google.com";
        hash = url.hashCode();
    }

    @Test
    void shouldHaveTheURLShorterSuccessfully() {
        // given

        // when
        String response = urlShortener.makeURLShort(url);

        // then
        assertEquals("short.com/" + hash, response);
    }
}
