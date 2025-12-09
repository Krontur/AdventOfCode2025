package com.krontur.adventofcode2025.day7_laboratories;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
