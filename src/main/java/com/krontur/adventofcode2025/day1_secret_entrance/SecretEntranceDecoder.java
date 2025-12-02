package com.krontur.adventofcode2025.day1_secret_entrance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SecretEntranceDecoder {

    static int decoder(BufferedReader input) throws IOException {

        int dialSize = 100;
        int dialIndex = 50;
        int password = 0;

        if(input == null)return -1;
        while(input.ready()){
            String dialRotation = input.readLine();
            System.out.println("Dial rotation: " + dialRotation);
            char direction = dialRotation.charAt(0);
            int steps = Integer.parseInt(dialRotation.substring(1));
            if(direction == 'L'){
                dialIndex = (dialIndex - steps + dialSize) % dialSize;
            } else if(direction == 'R'){
                dialIndex = (dialIndex + steps) % dialSize;
            }
            if(dialIndex == 0){
                password++;
            }
        }
        return password;
    }

    public static void main(String[] args) {
        try (InputStream is = SecretEntranceDecoder.class.getResourceAsStream("/input.txt")) {
            if (is != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                    System.out.println("The password is " + decoder(br));
                }
            } else {
                Path p = Paths.get("src", "main", "java", "com", "krontur", "adventofcode2025", "day1_secret_entrance", "input.txt");
                try (BufferedReader br = Files.newBufferedReader(p)) {
                    System.out.println("The password is " + decoder(br));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
