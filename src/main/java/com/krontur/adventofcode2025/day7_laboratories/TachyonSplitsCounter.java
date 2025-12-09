package com.krontur.adventofcode2025.day7_laboratories;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class TachyonSplitsCounter {

    public static int countTachyonSplits(BufferedReader br) throws IOException {

        List<char[]> grid = new ArrayList<>();
        Set<Integer> tachyonSplitsPositions = new HashSet<>();
        int tachyonSplitsCount = 0;
        if(br==null) return -1;
        while (br.ready()){
            String line = br.readLine();
            if(line == null || line.isEmpty()) break;
            grid.add(line.toCharArray());
        }

        boolean foundS = false;
        for(int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(row).length; col++) {
                if (grid.get(row)[col] == 'S') {
                    foundS = true;
                    tachyonSplitsPositions.add(col);
                    System.out.println("Found 'S' at position (" + row + ", " + col + ")");
                } else if (foundS && grid.get(row)[col] == '^') {
                    if (tachyonSplitsPositions.contains(col)) {
                        tachyonSplitsPositions.remove(col);
                        tachyonSplitsPositions.add(col - 1);
                        tachyonSplitsPositions.add(col + 1);
                        tachyonSplitsCount++;
                        System.out.println("Found new Tachyon Split '^' at position (" + row + ", " + col + "), total count: " + tachyonSplitsCount);
                    }
                }
            }
        }
        return tachyonSplitsCount;
    }

    public static long countTachyonSplitsManyWorlds(BufferedReader br) throws IOException {
        if (br == null) return -1L;

        List<char[]> grid = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            grid.add(line.toCharArray());
        }

        if (grid.isEmpty()) return 0L;

        int cols = grid.getFirst().length;

        long[] tachyonSplitsPositions = new long[cols]; 
        long[] accumulatorPositions  = new long[cols];

        boolean foundS = false;

        for (int row = 0; row < grid.size(); row++) {
            char[] currentRow = grid.get(row);

            for (int col = 0; col < currentRow.length; col++) {
                char cell = currentRow[col];

                if (cell == 'S') {
                    foundS = true;
                    tachyonSplitsPositions[col] += 1L;
                    accumulatorPositions[col] += 1L;

                    System.out.println("Found 'S' at position (" + row + ", " + col + ")");

                } else if (foundS && cell == '^') {
                    long tachyonsHere = tachyonSplitsPositions[col];

                    if (tachyonsHere > 0) {
                        
                        accumulatorPositions[col] += tachyonsHere;

                        tachyonSplitsPositions[col] = 0L;

                        if (col == 0) {
                            
                            tachyonSplitsPositions[col + 1] += tachyonsHere;

                        } else if (col == currentRow.length - 1) {
                            tachyonSplitsPositions[col - 1] += tachyonsHere;

                        } else {
                            tachyonSplitsPositions[col - 1] += tachyonsHere;
                            tachyonSplitsPositions[col + 1] += tachyonsHere;
                        }
                    }
                }
            }

            System.out.println(
                    "Accumulator positions after row " + row + ": " +
                            java.util.Arrays.toString(accumulatorPositions)
            );
        }

        long totalTimelines = 0L;
        for (long value : accumulatorPositions) {
            totalTimelines += value;
        }

        return totalTimelines;
    }


}
