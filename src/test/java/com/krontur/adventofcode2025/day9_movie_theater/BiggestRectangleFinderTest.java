package com.krontur.adventofcode2025.day9_movie_theater;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class BiggestRectangleFinderTest {

    @Test
    public void getBiggestRectangleFromExampleInputTest() {

        Path p = Path.of("src/main/resources/day9_movie_theater/exampleinput.txt");
        BufferedReader br;

        try {
            br = Files.newBufferedReader(p);
            long result = BiggestRectangleFinder.getBiggestRectangleArea(br);
            System.out.println("Example biggest rectangle area result: " + result);
            assert result == 50L;
        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Test
    public void getBiggestRectangleFromInputTest() {

        Path p = Path.of("src/main/resources/day9_movie_theater/input.txt");
        BufferedReader br;

        try {
            br = Files.newBufferedReader(p);
            long result = BiggestRectangleFinder.getBiggestRectangleArea(br);
            System.out.println("Biggest rectangle area result: " + result);
            assert result == 4760959496L;
        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }

}
