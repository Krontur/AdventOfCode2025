package com.krontur.adventofcode2025.day3_lobby;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MaxBanksJoltageTest {



    @Test
    void shouldGetMaxBanksJoltageFromExampleInputFile() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day3_lobby", "exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int maxJoltage = MaxBanksJoltage.getMaxJoltage(br);
        System.out.println("Example max banks joltage: " + maxJoltage);
        assert maxJoltage == 357;
        
    }

    @Test
    void shouldGetMaxBanksJoltageFromInputFile() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day3_lobby", "input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        int maxJoltage = MaxBanksJoltage.getMaxJoltage(br);
        System.out.println("Example max banks joltage: " + maxJoltage);
        assert maxJoltage == 17330;

    }

    @Test
    void shouldGetNMaxBanksJoltageFromExampleInputFile() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day3_lobby", "exampleinput.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long maxJoltage = MaxBanksJoltage.getNMaxJoltage(br);
        System.out.println("Example max banks joltage: " + maxJoltage);
        assert maxJoltage == 3121910778619L;

    }

    @Test
    void shouldGetNMaxBanksJoltageFromInputFile() throws IOException {

        Path p = Paths.get("src", "main", "resources", "day3_lobby", "input.txt");
        BufferedReader br = Files.newBufferedReader(p);
        long maxJoltage = MaxBanksJoltage.getNMaxJoltage(br);
        System.out.println("Max banks joltage: " + maxJoltage);
        assert maxJoltage == 171518260283767L;

    }

}
