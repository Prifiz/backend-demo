package com.marchmenow.backenddemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.marchmenow.backenddemo.dto.QuoteDto;
import com.marchmenow.backenddemo.exception.QuoteException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    @Value("${quotes.file-path}")
    private String fileName;

    public QuoteDto getRandomQuote() throws QuoteException {
        try {
            List<String> quotes = readQuotes(fileName);
            Random random = new Random();
            return new QuoteDto(quotes.get(random.nextInt(quotes.size())));
        } catch (URISyntaxException e) {
            throw new QuoteException("Некорректный формат пути к файлу");
        } catch (IOException ex) {
            throw new QuoteException("Что-то пошло не так: " + ex.getMessage());
        }
    }

    private List<String> readQuotes(String filename) throws IOException, URISyntaxException {
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        }

//
//        if (Optional.ofNullable(ClassLoader.getSystemResource(filename)).isEmpty()) {
//            throw new QuoteException("Файл с цитатами не найден");
//        }
//        Path quotesPath = Paths.get(ClassLoader.getSystemResource(filename).toURI());
//        return Files.lines(quotesPath).
//                filter(it -> !it.strip().isBlank()).
//                collect(Collectors.toList());
    }
}
