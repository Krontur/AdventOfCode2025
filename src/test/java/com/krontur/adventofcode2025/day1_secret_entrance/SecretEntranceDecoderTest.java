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
}
