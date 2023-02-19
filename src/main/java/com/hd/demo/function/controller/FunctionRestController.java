package com.hd.demo.function.controller;

import com.hd.demo.function.dto.FunctionResponse;
import com.hd.demo.function.service.FunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/htmls")
public class FunctionRestController {

    private final FunctionService functionService;

    @GetMapping
    public ResponseEntity<FunctionResponse> execute() {
        return ResponseEntity.ok().body(FunctionResponse.of(HttpStatus.OK.value(), functionService.execute()));
    }

}
