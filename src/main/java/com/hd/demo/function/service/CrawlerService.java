//package com.hd.demo.function.service;
//
//import com.hd.demo.function.domain.Crawler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CrawlerService {
//
//    private final Crawler crawler;
//
//
//    @Cacheable(value = "htmlCrawler", key = "#url")
//    public String execute(String url) {
//        return crawler.execute(url);
//    }
//
//}
