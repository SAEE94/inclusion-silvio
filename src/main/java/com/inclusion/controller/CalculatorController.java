package com.inclusion.controller;

import com.inclusion.dto.CalculatorCommand;
import com.inclusion.service.CalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<List<Integer>> getCalculate(@RequestParam("testCases") int testCases,
                                                      @RequestParam("array") String array) {
        return ResponseEntity.ok(calculatorService.calculate(testCases, array));
    }

    @PostMapping
    public ResponseEntity<List<Integer>> postCalculate(@RequestBody @Valid CalculatorCommand command) {
        return ResponseEntity.ok(calculatorService.calculate(command));
    }
}
