package com.krontur.adventofcode2025.day4_printing_department;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AvailableRollOfPaperSelectorTest {

    @Test
    void availableRollOfPaperSelectorExampleInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day4_printing_department", "exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int result = AvailableRollOfPaperSelector.selectAvailableRollOfPaper(br);
        System.out.println("Example available roll of paper selector result: " + result);
        assert result == 13;

    }

    @Test
    void availableRollOfPaperSelectorInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day4_printing_department", "input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int result = AvailableRollOfPaperSelector.selectAvailableRollOfPaper(br);
        System.out.println("Example available roll of paper selector result: " + result);
        assert result == 1346;

    }

    @Test
    void allAvailableInLoopRollOfPaperSelectorExampleInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day4_printing_department", "exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int result = AvailableRollOfPaperSelector.selectAllAvailableRollOfPaper(br);
        System.out.println("Example available roll of paper selector result: " + result);
        assert result == 43;

    }

    @Test
    void allAvailableInLoopRollOfPaperSelectorInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day4_printing_department", "input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int result = AvailableRollOfPaperSelector.selectAllAvailableRollOfPaper(br);
        System.out.println("Example available roll of paper selector result: " + result);
        assert result == 8493;

    }

}
