package com.krontur.adventofcode2025.day1_secret_entrance;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretEntranceDecoderTest {

    @Test
    void shouldCountPasswordsFromInputFile() throws IOException {
        Path p = Paths.get("src", "main", "java", "com", "krontur", "adventofcode2025", "day1_secret_entrance", "input.txt");
        try (BufferedReader br = Files.newBufferedReader(p)) {
            int password = SecretEntranceDecoder.decoder(br);
            assertEquals(1180, password);
        }
    }

    @Test
    void shouldCountPasswordsFromExampleInputFile() throws IOException {
        Path p = Paths.get("src", "main", "java", "com", "krontur", "adventofcode2025", "day1_secret_entrance", "testinput.txt");
        try (BufferedReader br = Files.newBufferedReader(p)) {
            int password = SecretEntranceDecoder.decoder(br);
            assertEquals(3, password);
        }
    }

    @Test
    void shouldReturnMinusOneForNullReader() throws IOException {
        assertEquals(-1, SecretEntranceDecoder.decoder(null));
    }

    @Test
    void shouldHandleWrapAroundScenarios() throws IOException {
        String data = """
                R25
                L75
                R50
                L50
                """;
        try (BufferedReader reader = new BufferedReader(new StringReader(data))) {
            int password = SecretEntranceDecoder.decoder(reader);
            assertEquals(2, password);
        }
    }

    @Test
    void shouldCountPasswordsFromInputFilePart2() throws IOException {
        Path p = Paths.get("src", "main", "java", "com", "krontur", "adventofcode2025", "day1_secret_entrance", "input.txt");
        try (BufferedReader br = Files.newBufferedReader(p)) {
            int password = SecretEntranceDecoder.decoder2(br);
            assertEquals(6892, password);
        }
    }

    @Test
    void shouldCountPasswordsFromExampleInputFilePart2() throws IOException {
        Path p = Paths.get("src", "main", "java", "com", "krontur", "adventofcode2025", "day1_secret_entrance", "testinput.txt");
        try (BufferedReader br = Files.newBufferedReader(p)) {
            int password = SecretEntranceDecoder.decoder2(br);
            assertEquals(6, password);
        }
    }

    @Test
    void shouldHandleWrapAroundScenarios2() throws IOException {
        String data = """
                R50
                L1
                L99
                R100
                L101
                L51
                R150
                L250
                R0
                """;
        try (BufferedReader reader = new BufferedReader(new StringReader(data))) {
            int password = SecretEntranceDecoder.decoder2(reader);
            assertEquals(7, password);
        }
    }
}
