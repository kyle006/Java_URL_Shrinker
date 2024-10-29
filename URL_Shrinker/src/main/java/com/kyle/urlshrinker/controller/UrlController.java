package com.kyle.urlshrinker.controller;

import com.kyle.urlshrinker.model.Url;
import com.kyle.urlshrinker.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService service;

    @Autowired
    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Url> addUrl(@RequestBody Url urlData) {
        Url savedUrl = service.storeUrl(urlData);
        return ResponseEntity.ok(savedUrl);
    }

    @GetMapping
    public ResponseEntity<List<Url>> getAllUrls() {
        List<Url> urls = service.fetchAllUrls();
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/{urlId}")
    public ResponseEntity<Url> retrieveUrl(@PathVariable Long urlId) {
        Optional<Url> optionalUrl = service.fetchUrlById(urlId);
        return optionalUrl.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{urlId}/deactivate")
    public ResponseEntity<Void> deactivateUrl(@PathVariable Long urlId) {
        service.markUrlInactive(urlId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{urlId}/statistics")
    public ResponseEntity<Url> getUrlStatistics(@PathVariable Long urlId) {
        Optional<Url> optionalUrl = service.fetchUrlStatistics(urlId);
        return optionalUrl.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
