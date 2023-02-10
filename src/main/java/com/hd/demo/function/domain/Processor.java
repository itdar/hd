package com.hd.demo.function.domain;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class Processor {

    private static Set<Character> set = new HashSet<>();
    static {
        for (int i = 'A'; i < 'Z'; ++i) {
            set.add((char) i);
        }
        for (int i = 'a'; i < 'z'; ++i) {
            set.add((char) i);
        }
        for (int i = '0'; i < '9'; ++i) {
            set.add((char) i);
        }
    }

    public String process(List<String> htmls) {
        StringBuilder singleChars = this.classifySingleAlphabetsAndDigits(htmls);

        PriorityQueue<Character> priorityQueueUpper = new PriorityQueue<>();
        PriorityQueue<Character> priorityQueueLower = new PriorityQueue<>();
        PriorityQueue<Character> priorityQueueDigit = new PriorityQueue<>();

        this.classifyAlphabetAndDigitToQueues(singleChars, priorityQueueUpper, priorityQueueLower, priorityQueueDigit);

        return this.integrate(priorityQueueUpper, priorityQueueLower, priorityQueueDigit);
    }

    private StringBuilder classifySingleAlphabetsAndDigits(List<String> htmls) {
        Set<Character> charBuilder = new HashSet<>();
        for (String html : htmls) {
            for (int i = 0; i < html.length(); ++i) {
                if (set.contains(html.charAt(i))) {
                    charBuilder.add(html.charAt(i));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charBuilder) {
            sb.append(character);
        }
        return sb;
    }

    private void classifyAlphabetAndDigitToQueues(final StringBuilder merged, PriorityQueue<Character> priorityQueueUpper, PriorityQueue<Character> priorityQueueLower, PriorityQueue<Character> priorityQueueDigit) {
        for (int i = 0; i < merged.length(); ++i) {
            char c = merged.charAt(i);

            if ('A' <= c && c <= 'Z') {
                priorityQueueUpper.add(c);
                continue;
            }

            if ('a' <= c && c <= 'z') {
                priorityQueueLower.add(c);
                continue;
            }

            if ('0' <= c && c <= '9') {
                priorityQueueDigit.add(c);
            }
        }
    }

    private String integrate(PriorityQueue<Character> priorityQueueUpper, PriorityQueue<Character> priorityQueueLower, PriorityQueue<Character> priorityQueueDigit) {
        StringBuilder sb = new StringBuilder();
        boolean isDone = false;
        while (!isDone) {
            if (!priorityQueueUpper.isEmpty()) {
                sb.append(priorityQueueUpper.poll());
            }

            if (!priorityQueueLower.isEmpty()) {
                sb.append(priorityQueueLower.poll());
            }

            if (!priorityQueueDigit.isEmpty()) {
                sb.append(priorityQueueDigit.poll());
            }

            if (priorityQueueUpper.isEmpty() && priorityQueueLower.isEmpty() && priorityQueueDigit.isEmpty()) {
                isDone = true;
            }
        }

        return sb.toString();
    }

}
