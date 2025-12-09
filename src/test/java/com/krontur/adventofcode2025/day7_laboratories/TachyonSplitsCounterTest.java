package com.krontur.adventofcode2025.day7_laboratories;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TachyonSplitsCounterTest {

    @Test
    void countTachyonSplitsFromExampleInputTest() {
        Path p = Paths.get("src", "main", "resources", "day7_laboratories", "exampleinput.txt");
        BufferedReader br;
        int result;
        try {
            br = Files.newBufferedReader(p);
            result = TachyonSplitsCounter.countTachyonSplits(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tachyon splits count: " + result);
        assert result == 21;
    }

    @Test
    void countTachyonSplitsFromInputTest() {
        Path p = Paths.get("src", "main", "resources", "day7_laboratories", "input.txt");
        BufferedReader br;
        int result;
        try {
            br = Files.newBufferedReader(p);
            result = TachyonSplitsCounter.countTachyonSplits(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tachyon splits count: " + result);
        assert result == 1698;
    }

    @Test
    void countTachyonSplitsManyWorldsFromExampleInputTest() {
        Path p = Paths.get("src", "main", "resources", "day7_laboratories", "exampleinput.txt");
        BufferedReader br;
        long result;
        try {
            br = Files.newBufferedReader(p);
            result = TachyonSplitsCounter.countTachyonSplitsManyWorlds(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tachyon splits count: " + result);
        assert result == 40;
    }

    @Test
    void countTachyonSplitsManyWorldsFromInputTest() {
        Path p = Paths.get("src", "main", "resources", "day7_laboratories", "input.txt");
        BufferedReader br;
        long result;
        try {
            br = Files.newBufferedReader(p);
            result = TachyonSplitsCounter.countTachyonSplitsManyWorlds(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tachyon splits count: " + result);
        assert result == 95408386769474L;
    }

}
