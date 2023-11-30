package com.inclusion.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("inputs")
    public void calculateWithCorrentInputAndGetMethod(String testCase, String array, String result) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculator")
                        .param("testCases", testCase)
                        .param("array", array))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[6]").value(""));
    }

    @ParameterizedTest
    @MethodSource("wrongInput")
    public void calculateWithWrongInputAndGetMethod(String testCase, String array, String message) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/calculator")
                        .param("testCases", testCase)
                        .param("array", array))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is(message)));
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.arguments("1","10,3,123450","[123443]"),
                Arguments.arguments("7","7,5,12345|5,0,4|10,5,15|17,8,54321|499999993,9,1000000000|10,5,187|2,0,999999999","[12339,0,15,54306,999999995,185,999999998]"),
                Arguments.arguments("1","72,29,9","[29]")
        );
    }

    private static Stream<Arguments> wrongInput() {
        return Stream.of(
                Arguments.arguments("2","10,3,123450","The size of test cases is not equals to size of scenarios"),
                Arguments.arguments("6","7,5,12345|5,0,4|10,5,15|17,8,54321|499999993,9,1000000000|10,5,187|2,0,999999999","The size of test cases is not equals to size of scenarios"),
                Arguments.arguments("3","72,29,9","The size of test cases is not equals to size of scenarios")
        );
    }
}
