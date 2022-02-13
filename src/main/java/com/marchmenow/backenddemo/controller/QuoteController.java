package com.marchmenow.backenddemo.controller;

import com.marchmenow.backenddemo.exception.QuoteException;
import com.marchmenow.backenddemo.service.QuoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("random-quote")
    public ResponseEntity<Object> getRandomQuote() throws QuoteException {
//        try {
            return ResponseEntity.ok(quoteService.getRandomQuote());
//        } catch (QuoteException ex) {
//
//        }
    }
}
