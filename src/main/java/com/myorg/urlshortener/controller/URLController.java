package com.myorg.urlshortener.controller;

import com.myorg.urlshortener.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class URLController {

    @Autowired
    private URLService service;

    @PostMapping("/shorten")
    @ResponseBody
    public ResponseEntity<String> shorten(@RequestParam String url) {
        String code = service.createShortUrl(url);
        return new ResponseEntity<>(code, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public void redirect(@PathVariable String code, HttpServletResponse response){
        service.resolve(code)
                .ifPresentOrElse(
                        u -> {
                            try {
                                response.sendRedirect(u);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> response.setStatus(HttpServletResponse.SC_NOT_FOUND));
    }
}
