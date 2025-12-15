package com.krontur.adventofcode2025.day8_playground;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CircuitJunctionBoxesConnectorTest {
    
    @Test
    public void circuitJunctionBoxesConnectorFromExampleInputTest() {
        
        Path p = Path.of("src/main/resources/day8_playground/exampleinput.txt");
        BufferedReader br;
        try {
            br = Files.newBufferedReader(p);
            int result = CircuitJunctionBoxesConnector.getConnectedBoxesCount(br, 10);
            System.out.println("Example circuit junction boxes connector result: " + result);
            assert result == 40;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void circuitJunctionBoxesConnectorFromInputTest() {

        Path p = Path.of("src/main/resources/day8_playground/input.txt");
        BufferedReader br;
        try {
            br = Files.newBufferedReader(p);
            long result = CircuitJunctionBoxesConnector.getConnectedBoxesCountTogether(br);
            System.out.println("Product of their X coordinates result: " + result);
            assert result == 772452514L;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
