package com.krontur.adventofcode2025.day6_trash_compactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MathTasksCalculator {

    public static long calculateMathTasks(BufferedReader br) throws IOException {

        List<List<String>> mathTasks = new ArrayList<>();
        long total = 0L;

        if(br == null)return -1;
        while(br.ready()){
            String line = br.readLine().trim();
            String[] current = line.split(" ");
            List<String> row = new ArrayList<>();
            for (String s : current) {
                if (!s.trim().isEmpty()) {
                    row.add(s);
                }
            }
            mathTasks.add(row);
        }
        for(int col = 0; col < mathTasks.getFirst().size(); col++){
            String operator = mathTasks.getLast().get(col);
            long subTotal = 0L;
            for(int row = 0; row < mathTasks.size() - 1; row++){
                switch(operator.charAt(0)){
                    case '+':
                        subTotal += Long.parseLong(mathTasks.get(row).get(col));
                        break;
                    case '*':
                        if(subTotal == 0L){
                            subTotal = 1L;
                        }
                        subTotal *= Long.parseLong(mathTasks.get(row).get(col));
                        break;
                }
            }
            total += subTotal;
        }
        return total;
    }
}
