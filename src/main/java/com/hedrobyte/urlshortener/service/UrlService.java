package com.hedrobyte.urlshortener.service;

import com.hedrobyte.urlshortener.controller.dto.ShortenUrlRequest;
import com.hedrobyte.urlshortener.controller.dto.ShortenUrlResponse;
import com.hedrobyte.urlshortener.entities.UrlEntity;
import com.hedrobyte.urlshortener.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
