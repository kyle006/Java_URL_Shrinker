package com.kyle.urlshrinker.controller;

import com.kyle.urlshrinker.model.Url;
import com.kyle.urlshrinker.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{shortCode}")
    public ResponseEntity<Url> retrieveUrl(@PathVariable String shortCode) {
        Optional<Url> optionalUrl = service.fetchUrlByShortCode(shortCode);
        return optionalUrl.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{urlId}/deactivate")
    public ResponseEntity<Void> deactivateUrl(@PathVariable Long urlId) {
        service.markUrlInactive(urlId);
        return ResponseEntity.noContent().build();
    }
}
