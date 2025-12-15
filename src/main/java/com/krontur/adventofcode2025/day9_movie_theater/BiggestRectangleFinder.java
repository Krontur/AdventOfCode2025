package com.krontur.adventofcode2025.day9_movie_theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BiggestRectangleFinder {

    private static List<int[]> readTilePositions(BufferedReader input) throws IOException {
        List<int[]> positions = new ArrayList<>();
        if(input==null) return positions;
        while (input.ready()){
            String line = input.readLine();
            if(line == null || line.isEmpty()) break;
            String[] parts = line.split(",");
            positions.add(new int[]{Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim())});

        }
        return positions;
    }

    public static long getBiggestRectangleArea(BufferedReader br) {

        List<int[]> positions;
        try {
            positions = readTilePositions(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Read " + positions.size() + " tile positions.");
        System.out.println("Calculating biggest rectangle area...");
        if(positions.isEmpty()) return 0;
        long biggestArea = 0;
        for(int i = 0; i < positions.size(); i++) {
            for(int j = i + 1; j < positions.size(); j++) {
                int sideX = Math.abs(positions.get(i)[0] - positions.get(j)[0]) + 1;
                System.out.println("Side X between points " + positions.get(i)[0] + " and " + positions.get(j)[0] + " is: " + sideX);
                int sideY = Math.abs(positions.get(i)[1] - positions.get(j)[1]) + 1;
                System.out.println("Side Y between points " + positions.get(i)[1] + " and " + positions.get(j)[1] + " is: " + sideY);
                long area = (long) sideX * sideY;
                System.out.println("Area between points " + positions.get(i)[0] + "," + positions.get(i)[1] +
                        " and " + positions.get(j)[0] + "," + positions.get(j)[1] + " is: " + area);
                if(area > biggestArea) {
                    biggestArea = area;
                }
            }
        }
        return biggestArea;
    }

}
