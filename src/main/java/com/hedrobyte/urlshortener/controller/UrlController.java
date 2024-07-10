package com.hedrobyte.urlshortener.controller;

import com.hedrobyte.urlshortener.controller.dto.ShortenUrlRequest;
import com.hedrobyte.urlshortener.controller.dto.ShortenUrlResponse;
import com.hedrobyte.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest) {
        String baseUrl = servletRequest.getRequestURL().toString();
        ShortenUrlResponse response = urlService.shortenUrl(request, baseUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        return urlService.redirect(id);
    }

}
