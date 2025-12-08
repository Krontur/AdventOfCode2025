package com.krontur.adventofcode2025.day6_trash_compactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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
            long subTotal = getSubTotal(mathTasks, col);
            total += subTotal;
        }
        return total;
    }

    private static long getSubTotal(List<List<String>> mathTasks, int col) {
        System.out.println(" MathTasks: " + mathTasks);
        String operator = mathTasks.getLast().get(col).trim();
        System.out.println("Processing column: " + col + ", operator: " + operator);
        long subTotal = 0L;
        for(int row = 0; row < mathTasks.size() - 1; row++){
            switch(operator.charAt(0)){
                case '+':
                    subTotal += Long.parseLong(mathTasks.get(row).get(col).trim());
                    System.out.println("Adding " + mathTasks.get(row).get(col) + ", subTotal: " + subTotal);
                    break;
                case '*':
                    if(subTotal == 0L){
                        subTotal = 1L;
                    }
                    subTotal *= Long.parseLong(mathTasks.get(row).get(col).trim());
                    System.out.println("Multiplying by " + mathTasks.get(row).get(col) + ", subTotal: " + subTotal);
                    break;
            }
        }
        return subTotal;
    }

    private static long getNewSubTotal(List<String> verticalProblem) {
        // Último elemento = operador
        String opStr = verticalProblem.getLast().trim();
        char op = opStr.charAt(0); // '+' o '*'

        long result = (op == '+') ? 0L : 1L;

        // Todos menos el último son números
        for (int i = 0; i < verticalProblem.size() - 1; i++) {
            String numStr = verticalProblem.get(i).trim();
            if (numStr.isEmpty()) continue; // por si hubiera huecos raros
            long value = Long.parseLong(numStr);

            if (op == '+') {
                result += value;
            } else if (op == '*') {
                result *= value;
            } else {
                throw new IllegalArgumentException("Operador no válido: " + op);
            }
        }

        return result;
    }


    public static long calculateNewMathTasks(BufferedReader br) throws IOException {

        long total = 0L;

        List<char[]> table = new ArrayList<>();

        if(br == null)return -1;
        while(br.ready()){
            String line = br.readLine();
            char[] row = line.toCharArray();
            table.add(row);
        }

        List<List<Integer>> splitPositions = new ArrayList<>();
        List<Integer> mergedSplitPositions = new ArrayList<>();
        for(int row = 0; row < table.size(); row++){
            splitPositions.add(new ArrayList<>());
            for(int col = 0; col < table.get(row).length; col++){
                if(table.get(row)[col] == ' '){
                    splitPositions.get(row).add(col);
                }
            }
            if(row != 0) {
                if (splitPositions.get(row).retainAll(splitPositions.get(row - 1))) {
                    mergedSplitPositions.clear();
                    mergedSplitPositions.addAll(splitPositions.get(row));
                }
            }
        }


        for (Integer mergedSplitPosition : mergedSplitPositions) {
            for (int row = 0; row < table.size() - 1; row++) {
                table.get(row)[mergedSplitPosition] = ';';
            }

        }

        List<List<String>> permutedTable = new ArrayList<>();
        List<String> permutedRow = new ArrayList<>();
        StringBuilder operatorSb = new StringBuilder();
        for(int col = 0; col < table.getFirst().length; col++){
            System.out.println("Processing column: " + col);
            StringBuilder sb = new StringBuilder();
            for(int row = 0; row < table.size(); row++){
                System.out.println("At row: " + row + ", char: " + table.get(row)[col]);
                if (table.get(row)[col] == ';') {
                    if (row == table.size() - 2) {
                        permutedRow.add(operatorSb.toString().trim());
                        operatorSb = new StringBuilder();
                        System.out.println("Added operator: " + permutedRow);
                        permutedTable.add(permutedRow);
                        sb = new StringBuilder();
                        System.out.println("Permuted table now: " + permutedTable);
                        permutedRow = new ArrayList<>();
                    }
                    continue;
                }
                if(row == table.size() - 1){
                    operatorSb.append(table.get(row)[col]);
                    System.out.println("Building operator: " + operatorSb.toString().trim());
                    if(!sb.isEmpty())permutedRow.add(sb.toString().trim());
                    continue;
                }
                sb.append(table.get(row)[col]);
                System.out.println("Building number: " + sb.toString().trim());
            }
        }

        if (!permutedRow.isEmpty() || !operatorSb.isEmpty()) {
            permutedRow.add(operatorSb.toString().trim());
            System.out.println("Added last operator: " + permutedRow);
            permutedTable.add(permutedRow);
        }


        System.out.println("Permuted Table: " + permutedTable);
        for (List<String> strings : permutedTable) {
            long subTotal = getNewSubTotal(strings);
            total += subTotal;
        }
        return total;

    }
}
