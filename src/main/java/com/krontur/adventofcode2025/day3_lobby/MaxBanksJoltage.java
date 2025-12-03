package com.krontur.adventofcode2025.day3_lobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MaxBanksJoltage {

    static int getMaxJoltage(BufferedReader input) throws IOException {

        List<Integer> joltages = new ArrayList<>();
        int totalJoltage = 0;

        if(input==null)return -1;
        while(input.ready()){
            String batteryJoltages = null;
            try {
                batteryJoltages = input.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(batteryJoltages == null || batteryJoltages.isEmpty()) break;
            for(int i = 0; i < batteryJoltages.length(); i++){
                joltages.add(Integer.parseInt(String.valueOf(batteryJoltages.charAt(i))));
            }
            int maxJoltage = 0;
            for(int j = 0; j < joltages.size(); j++){
                for(int k = j+1; k < joltages.size(); k++){
                    int Joltage = Integer.parseInt(String.valueOf(joltages.get(j)).concat(String.valueOf(joltages.get(k))));
                    if(Joltage > maxJoltage){
                        maxJoltage = Joltage;
                    }
                }
            }
            totalJoltage += maxJoltage;
            joltages.clear();
        }
        return totalJoltage;
    }

    static long getNMaxJoltage(BufferedReader input) throws IOException {

        long totalJoltage = 0;

        if(input==null)return -1;
        while(input.ready()){
            String batteryJoltages = null;
            try {
                batteryJoltages = input.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(batteryJoltages == null || batteryJoltages.isEmpty()) break;
            int n = batteryJoltages.length();
            int toDelete = n - 12;
            List<Integer> joltageList = batteryJoltages.chars().mapToObj(c -> (char) c).map(c -> Integer.parseInt(String.valueOf(c))).collect(Collectors.toList());
            System.out.println(joltageList);
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                int d = batteryJoltages.charAt(i) - '0';

                while (!stack.isEmpty() && toDelete > 0 && stack.peekLast() < d) {
                    stack.removeLast();
                    toDelete--;
                }

                stack.addLast(d);
            }

            while (toDelete > 0) {
                stack.removeLast();
                toDelete--;
            }
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int digit : stack) {
                if (count == 12) break;
                sb.append(digit);
                count++;
            }
            System.out.println(sb);
            totalJoltage += Long.parseLong(sb.toString());
        }
        return totalJoltage;
    }

}
