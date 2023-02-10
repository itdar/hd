package com.hd.demo.function.domain;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProcessorTest {

    private Processor processor = new Processor();

    String expected = "Aa1Bb2Cc3Dd4e5fg";

    @DisplayName("문자열에서 중복없이 알파벳과 숫자만 남기고, 정렬한다.")
    @Test
    void processTest() {
        // 정렬 기준은 대문자소문자숫자대문자소문자숫자...
        String duplicatedHtml1 = "11222334555abcccdeeeffgggABBCCCD";
        String duplicatedHtml2 = "abcccdeeeffggg11222334555ABBCCCD";
        String duplicatedHtml3 = "abcccdeeeffgggABBCCCD11222334555";

        String result = processor.process(Arrays.asList(duplicatedHtml1, duplicatedHtml2, duplicatedHtml3));

        Assertions.assertThat(result).isEqualTo(expected);
    }

}
