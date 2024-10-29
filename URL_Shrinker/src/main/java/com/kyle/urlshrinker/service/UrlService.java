package com.kyle.urlshrinker.service;

import com.kyle.urlshrinker.model.Url;
import com.kyle.urlshrinker.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository repository;

    @Autowired
    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public List<Url> fetchAllUrls() {
        return repository.findAll();
    }

    public Url storeUrl(Url urlData) {
        return repository.save(urlData);
    }

    public Optional<Url> fetchUrlById(Long urlId) {
        return repository.findById(urlId);
    }

    public void markUrlInactive(Long urlId) {
        repository.findById(urlId).ifPresent(item -> {
            item.setActive(false);
            repository.save(item);
        });
    }

    public Optional<Url> fetchUrlStatistics(Long urlId) {
        return repository.findById(urlId);
    }
}
