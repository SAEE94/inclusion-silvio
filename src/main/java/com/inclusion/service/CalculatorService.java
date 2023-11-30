package com.inclusion.service;

import com.inclusion.dto.CalculatorCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CalculatorService {

    private List<Integer> doCalculate(CalculatorCommand command) {

        if (command.testCases() != command.params().size()) {
            throw new RuntimeException("The size of test cases is not equals to size of scenarios");
        }

        List<Integer> response = new ArrayList<>();


        for (int i = 0; i < command.testCases(); i++) {
            int x = command.params().get(i).x();
            int y = command.params().get(i).y();
            int n = command.params().get(i).n();
            int k = ((n - y) / x) * x + y;

            if (k > n || (k - y) % x != 0) {
                k = 0;
            }

            response.add(k);
        }


        return response;
    }

    public List<Integer> calculate(CalculatorCommand command) {
        return doCalculate(command);
    }

    public List<Integer> calculate(int testCases, String params) {
        List<CalculatorCommand.CalculatorParams> paramList = Arrays.stream(
                        params.split("\\|"))
                .map(innerArray -> Arrays.stream(innerArray.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray())
                .map(innerArray -> new CalculatorCommand.CalculatorParams((Integer) innerArray[0], (Integer) innerArray[1], (Integer) innerArray[2]))
                .toList();
        return calculate(new CalculatorCommand(testCases, paramList));

    }
}
