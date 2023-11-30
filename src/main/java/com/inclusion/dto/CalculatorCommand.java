package com.inclusion.dto;

import java.util.List;

public record CalculatorCommand(int testCases,
                                List<CalculatorParams> params) {
    public record CalculatorParams(int x,
                                   int y,
                                   int n) {
    }
}
