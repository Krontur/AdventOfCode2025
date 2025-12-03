package com.krontur.adventofcode2025.day1_secret_entrance;

import java.io.*;

public class SecretEntranceDecoder {

    static int decoder(BufferedReader input) throws IOException {

        int dialSize = 100;
        int dialIndex = 50;
        int password = 0;

        if(input == null)return -1;
        while(input.ready()){
            String dialRotation = input.readLine();
            if(dialRotation == null || dialRotation.isEmpty()) break;
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


    static int decoder2(BufferedReader input) throws IOException {

        int dialSize = 100;
        int dialIndex = 50;
        int password = 0;

        if(input == null)return -1;

        while(input.ready()){
            String dialRotation = input.readLine();
            if(dialRotation == null || dialRotation.isEmpty()) break;
            System.out.println("Dial rotation: " + dialRotation);
            char direction = dialRotation.charAt(0);
            int steps = Integer.parseInt(dialRotation.substring(1));
            int passedZeroes = 0;

            if(direction == 'L'){
                if(dialIndex == 0){
                    passedZeroes = steps / dialSize;
                } else {
                    if(steps >= dialIndex){
                        passedZeroes = 1 + (steps - dialIndex) / dialSize;
                    }
                }

                dialIndex = (dialIndex - steps ) % dialSize;
                if(dialIndex < 0) dialIndex += dialSize;
            } else if(direction == 'R'){
                passedZeroes += (steps + dialIndex) / dialSize;
                dialIndex = (dialIndex + steps) % dialSize;
            }

            password += passedZeroes;
            System.out.println("Current dial index: " + dialIndex + ", password: " + password);
        }
        return password;
    }

}
