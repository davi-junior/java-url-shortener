package com.example.javainterview.service;

import com.example.javainterview.model.ShortURL;
import com.example.javainterview.repository.ShortURLRepository;
import com.example.javainterview.shortener.URLShortener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class URLService {

    private final ShortURLRepository repository;
    private final URLShortener urlShortener;

    public URLService(final ShortURLRepository repository, final URLShortener urlShortener) {
        this.repository = repository;
        this.urlShortener = urlShortener;
    }

    public String urlShortener(String url) {
        var optional = findByURL(url);
        ShortURL entity = new ShortURL();

        if (optional.isPresent()) {
            entity = optional.get();
            entity.setNumberOfAccess(entity.getNumberOfAccess() + 1);
        } else {
            entity.setOriginalURL(url);
            entity.setShortVersion(makeURLShort(url));
            entity.setNumberOfAccess(1L);
        }

        repository.save(entity);

        return entity.getShortVersion();
    }

    public List<String> getTop100() {
        return repository.getTop100().stream().map(ShortURL::getOriginalURL).toList();
    }

    private Optional<ShortURL> findByURL(String url) {
        return repository.findByURL(url);
    }

    private String makeURLShort(String url) {
        return urlShortener.makeURLShort(url);
    }
}
