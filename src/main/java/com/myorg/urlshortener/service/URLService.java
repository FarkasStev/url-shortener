package com.myorg.urlshortener.service;

import com.myorg.urlshortener.model.URLMapping;
import com.myorg.urlshortener.respository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLService {

    @Autowired
    private URLMappingRepository repo;

    public String createShortUrl(String orig) {
        String code = generateCode(orig);
        URLMapping m = new URLMapping();
        m.setShortCode(code);
        m.setOriginalUrl(orig);
        repo.save(m);
        return code;
    }

    public Optional<String> resolve(String code) {
        URLMapping m = repo.findByShortCode(code);
        return m != null ? Optional.of(m.getOriginalUrl()) : Optional.empty();
    }

    private String generateCode(String url) {
        return Integer.toHexString(url.hashCode());
    }
}
