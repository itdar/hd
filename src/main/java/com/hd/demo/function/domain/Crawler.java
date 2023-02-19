package com.hd.demo.function.domain;

import com.hd.demo.exception.CrawlerExecuteException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class Crawler {
    private static final String CACHE_NAME = "crawler-execute";

    private Logger logger =  LoggerFactory.getLogger(Crawler.class);

    @Cacheable(value = CACHE_NAME, key = "#url")
    @Retryable(value = IOException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    public String execute(final String url) {
        Document document;
        try {
            document = Jsoup.connect(url)
                .timeout(1000)
                .get();
        } catch (IOException e) {
            throw new CrawlerExecuteException("최대 재시도 후에도 크롤링에서 예외 발생");
        }

        if (document != null) {
            return document.body().html();
        }
        return null;
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.crawlerTtl}")
    public void emptyCrawlerCache() {
        logger.info("Crawler.emptyCrawlerCache(): " + CACHE_NAME + " - is done.");
    }

}
