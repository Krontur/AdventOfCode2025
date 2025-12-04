package com.krontur.adventofcode2025.day4_printing_department;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AvailableRollOfPaperSelector {

    public static int selectAvailableRollOfPaper(BufferedReader br) throws IOException {

        List<List<Character>> grid = new ArrayList<>();

        if(br==null) return -1;
        while(br.ready()){
            String line = br.readLine();
            if(line == null || line.isEmpty()) break;
            grid.add(new ArrayList<>());
            for(char c : line.toCharArray()){
                grid.getLast().add(c);
            }
        }
        System.out.println("Grid loaded: " + grid);

        int[][] adyacentDirections = {
                {-1, 0}, // up
                {1, 0},  // down
                {0, -1}, // left
                {0, 1},  // right
                {-1, -1}, // up-left
                {-1, 1},  // up-right
                {1, -1},  // down-left
                {1, 1}    // down-right
        };

        System.out.println("Starting to evaluate grid for available roll of paper..." + adyacentDirections.length);

        int selectedRolls = 0;
        for(int row = 0; row < grid.size(); row++){
            for(int col = 0; col < grid.get(row).size(); col++){
                System.out.println("Evaluating cell at (" + row + ", " + col + ") with value: " + grid.get(col).get(row));
                if(grid.get(row).get(col) == '.'){
                    System.out.println("Cell at (" + row + ", " + col + ") is empty ('.'), skipping.");
                } else {
                    System.out.println("Cell at (" + row + ", " + col + ") contains roll of paper ('@'), checking adjacent cells...");
                    int rollPaperAdjacent = 0;
                    for (int[] adyacentDirection : adyacentDirections) {
                        System.out.println("Checking cell at (" + row + ", " + col + ") in direction (" + adyacentDirection[0] + ", " + adyacentDirection[1] + ")");
                        int newRow = row + adyacentDirection[1];
                        int newCol = col + adyacentDirection[0];
                        if (newRow >= 0 && newRow < grid.size() && newCol >= 0 && newCol < grid.getFirst().size()) {
                            if (grid.get(newRow).get(newCol) == '@') {
                                rollPaperAdjacent++;
                            }
                        } else {
                            System.out.println("Cell at (" + newRow + ", " + newCol + ") is out of bounds, skipping.");
                        }
                    }
                    if(rollPaperAdjacent < 4) {
                        selectedRolls++;
                        System.out.println("Selected roll of paper at (" + row + ", " + col + "). Total selected: " + selectedRolls);
                    }
                }

            }
        }
        System.out.println("Available roll of paper evaluation completed." + selectedRolls);
        return selectedRolls;
    }
}
