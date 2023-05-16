package com.example.javainterview.shortener;

import org.springframework.stereotype.Component;

@Component
public class URLShortener {

    public String makeURLShort(String url) {
        int hash = url.hashCode();
        return "short.com/" + hash;
    }
}
