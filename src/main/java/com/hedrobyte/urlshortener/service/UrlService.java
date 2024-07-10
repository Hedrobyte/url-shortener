package com.hedrobyte.urlshortener.service;

import com.hedrobyte.urlshortener.controller.dto.ShortenUrlRequest;
import com.hedrobyte.urlshortener.controller.dto.ShortenUrlResponse;
import com.hedrobyte.urlshortener.entities.UrlEntity;
import com.hedrobyte.urlshortener.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request, String baseUrl) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        String redirectUrl = baseUrl.replace("shorten-url", id);

        UrlEntity urlEntity = new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1));
        urlRepository.save(urlEntity);

        return new ShortenUrlResponse(redirectUrl);
    }

    public ResponseEntity<Void> redirect(String id) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(id);

        if (urlEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlEntity.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
