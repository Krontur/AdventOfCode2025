package com.krontur.adventofcode2025.day6_trash_compactor;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MathTasksCalculatorTest {

    @Test
    public void mathTasksCalculatorFromExampleInputTest() throws IOException {

        Path p = Path.of("src/main/resources/day6_trash_compactor/exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = MathTasksCalculator.calculateMathTasks(br);
        System.out.println("Example math tasks calculator result: " + result);
        assert result == 4277556L;

    }

    @Test
    public void mathTasksCalculatorFromInputTest() throws IOException {

        Path p = Path.of("src/main/resources/day6_trash_compactor/input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = MathTasksCalculator.calculateMathTasks(br);
        System.out.println("Example math tasks calculator result: " + result);
        assert result == 4693159084994L;

    }

    @Test
    public void newMathTasksCalculatorFromExampleInputTest() throws IOException {

        Path p = Path.of("src/main/resources/day6_trash_compactor/exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = MathTasksCalculator.calculateNewMathTasks(br);
        System.out.println("Example math tasks calculator result: " + result);
        assert result == 3263827L;

    }

    @Test
    public void newMathTasksCalculatorFromInputTest() throws IOException {

        Path p = Path.of("src/main/resources/day6_trash_compactor/input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = MathTasksCalculator.calculateNewMathTasks(br);
        System.out.println("Example math tasks calculator result: " + result);
        assert result == 11643736116335L;

    }

}
