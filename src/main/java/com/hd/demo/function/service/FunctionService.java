package com.hd.demo.function.service;

import com.hd.demo.function.domain.Crawler;
import com.hd.demo.function.domain.Processor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FunctionService {

    private final Crawler crawler;
    private final Processor processor;

    public static Set<String> urls = new HashSet<>();
    static {
        urls.add("https://shop.hyundai.com");
        urls.add("https://www.kia.com");
        urls.add("https://www.genesis.com");
    }

    public String execute() {
        List<String> htmls = urls.stream()
            .parallel()
            .map(crawler::execute)
            .collect(Collectors.toList());

        return processor.process(htmls);
    }

}
