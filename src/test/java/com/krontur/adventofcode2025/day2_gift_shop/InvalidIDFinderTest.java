package com.krontur.adventofcode2025.day2_gift_shop;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InvalidIDFinderTest {

    static String[] getInputFromResource(String resourceName){
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream is = cl.getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = br.read()) != -1) {
                    sb.append((char) c);
                }
                return sb.toString().split(",");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testExampleFindInvalidIDSum(){
        String[] input = getInputFromResource("./day2_gift_shop/exampleinput.txt");
        long result = InvalidIDFinder.findInvalidIDSum(input);
        System.out.println("Example invalid ID sum: " + result);
        assert result == 1227775554L;
    }

    @Test
    void testInputFindInvalidIDSum(){
        String[] input = getInputFromResource("./day2_gift_shop/input.txt");
        long result = InvalidIDFinder.findInvalidIDSum(input);
        System.out.println("Example invalid ID sum: " + result);
        assert result == 38437576669L;
    }

    @Test
    void testExampleFindInvalidIDSumPart2(){
        String[] input = getInputFromResource("./day2_gift_shop/exampleinput.txt");
        long result = InvalidIDFinder.findInvalidIDSumPart2(input);
        System.out.println("Example invalid ID sum: " + result);
        assert result == 4174379265L;
    }

    @Test
    void testInputFindInvalidIDSumPart2(){
        String[] input = getInputFromResource("./day2_gift_shop/input.txt");
        long result = InvalidIDFinder.findInvalidIDSumPart2(input);
        System.out.println("Example invalid ID sum: " + result);
        assert result == 49046150754L;
    }

}
