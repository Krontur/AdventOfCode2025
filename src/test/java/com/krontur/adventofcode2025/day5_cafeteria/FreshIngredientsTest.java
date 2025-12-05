package com.krontur.adventofcode2025.day5_cafeteria;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FreshIngredientsTest {

    @Test
    void  getFreshIngredientsFromExampleInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day5_cafeteria", "exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = FreshIngredients.getFreshIngredients(br);
        System.out.println("Example fresh ingredients result: " + result);
        assert result == 3;

    }

    @Test
    void  getFreshIngredientsFromInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day5_cafeteria", "input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = FreshIngredients.getFreshIngredients(br);
        System.out.println("Example fresh ingredients result: " + result);
        assert result == 712;

    }

    @Test
    void  getPosibleFreshIngredientsCountFromExampleInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day5_cafeteria", "exampleinputPart2.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = FreshIngredients.getPosibleFreshIngredientsCount(br);
        System.out.println("ExampleInput fresh ingredients result: " + result);
        assert result == 14;

    }

    @Test
    void  getPosibleFreshIngredientsCountFromInputTest() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day5_cafeteria", "inputPart2.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long result = FreshIngredients.getPosibleFreshIngredientsCount(br);
        System.out.println("Input fresh ingredients result: " + result);
        assert result == 332998283036769L;
    }

}
