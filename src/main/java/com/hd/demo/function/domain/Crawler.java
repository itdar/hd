package com.hd.demo.function.domain;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class Crawler {

    private static final int MAX_RETRY = 3;

    private int retryCount = 0;

    @Cacheable(value = "crawler-execute", key = "#url")
    public String execute(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                .timeout(1000)
                .get();
        } catch (IOException e) {
            if (retryCount >= MAX_RETRY) {
                retryCount = 0;
                throw new RuntimeException("최대 재시도 후에도 크롤링에서 예외 발생");
            }

            ++retryCount;
            this.execute(url);
        }

        if (document != null) {
            return document.body().html();
        }
        return null;
    }

}
