package com.hd.demo.function.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FunctionResponse {

    @JsonProperty("Status")
    private int status;

    @JsonProperty("Merge")
    private String merge;

    public static FunctionResponse of(int status, String merge) {
        return new FunctionResponse(status, merge);
    }

}
