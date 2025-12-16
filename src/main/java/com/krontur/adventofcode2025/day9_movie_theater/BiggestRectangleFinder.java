package com.krontur.adventofcode2025.day9_movie_theater;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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

    public static long getBiggestValidRectangleArea(BufferedReader br) {

        List<int[]> positions;
        try {
            positions = readTilePositions(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (positions.isEmpty()) {
            System.out.println("Read 0 tile positions.");
            return 0;
        }

        final long encodeMask = 0xffffffffL;
        class CoordinateEncoder {
            long encode(int x, int y) { return ((long)x << 32) | (y & encodeMask); }
            int getX(long e) { return (int)(e >> 32); }
            int getY(long e) { return (int)(e & encodeMask); }
        }
        CoordinateEncoder encoder = new CoordinateEncoder();

        Set<Long> redTiles = new LinkedHashSet<>();
        for (int[] p : positions) {
            redTiles.add(encoder.encode(p[0], p[1]));
        }

        List<int[]> verticalSegments = new ArrayList<>();
        List<int[]> horizontalSegments = new ArrayList<>();

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (int i = 0; i < positions.size(); i++) {
            int[] current = positions.get(i);
            int[] next = positions.get((i + 1) % positions.size());

            if (current[0] == next[0]) {
                verticalSegments.add(new int[]{
                        current[0],
                        Math.min(current[1], next[1]),
                        Math.max(current[1], next[1])
                });
            } else if (current[1] == next[1]) {
                horizontalSegments.add(new int[]{
                        current[1],
                        Math.min(current[0], next[0]),
                        Math.max(current[0], next[0])
                });
            } else {
                throw new IllegalArgumentException("Consecutive red tiles are not aligned");
            }

            minX = Math.min(minX, Math.min(current[0], next[0]));
            minY = Math.min(minY, Math.min(current[1], next[1]));
            maxX = Math.max(maxX, Math.max(current[0], next[0]));
            maxY = Math.max(maxY, Math.max(current[1], next[1]));
        }

        TreeSet<Integer> xCoordinates = new TreeSet<>();
        TreeSet<Integer> yCoordinates = new TreeSet<>();

        for (long e : redTiles) {
            xCoordinates.add(encoder.getX(e));
            xCoordinates.add(encoder.getX(e) + 1);
            yCoordinates.add(encoder.getY(e));
            yCoordinates.add(encoder.getY(e) + 1);
        }

        for (int[] segment : verticalSegments) {
            xCoordinates.add(segment[0]);
            xCoordinates.add(segment[0] + 1);
            yCoordinates.add(segment[1]);
            yCoordinates.add(segment[2] + 1);
        }

        for (int[] segment : horizontalSegments) {
            yCoordinates.add(segment[0]);
            yCoordinates.add(segment[0] + 1);
            xCoordinates.add(segment[1]);
            xCoordinates.add(segment[2] + 1);
        }

        xCoordinates.add(minX);
        xCoordinates.add(maxX + 1);
        yCoordinates.add(minY);
        yCoordinates.add(maxY + 1);

        List<Integer> xLines = new ArrayList<>(xCoordinates);
        List<Integer> yLines = new ArrayList<>(yCoordinates);

        int gridWidth = xLines.size() - 1;
        int gridHeight = yLines.size() - 1;

        Map<Integer, Integer> xToIndex = new HashMap<>();
        Map<Integer, Integer> yToIndex = new HashMap<>();

        for (int i = 0; i < xLines.size(); i++) xToIndex.put(xLines.get(i), i);
        for (int i = 0; i < yLines.size(); i++) yToIndex.put(yLines.get(i), i);

        boolean[][] blockedGrid = new boolean[gridHeight][gridWidth];

        for (int[] segment : verticalSegments) {
            int column = xToIndex.get(segment[0]);
            int rowStart = yToIndex.get(segment[1]);
            int rowEnd = yToIndex.get(segment[2] + 1);
            for (int r = rowStart; r < rowEnd; r++) blockedGrid[r][column] = true;
        }

        for (int[] segment : horizontalSegments) {
            int row = yToIndex.get(segment[0]);
            int colStart = xToIndex.get(segment[1]);
            int colEnd = xToIndex.get(segment[2] + 1);
            for (int c = colStart; c < colEnd; c++) blockedGrid[row][c] = true;
        }

        for (long e : redTiles) {
            blockedGrid[yToIndex.get(encoder.getY(e))][xToIndex.get(encoder.getX(e))] = true;
        }

        int expandedWidth = gridWidth + 2;
        int expandedHeight = gridHeight + 2;

        boolean[][] outsideVisited = new boolean[expandedHeight][expandedWidth];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        outsideVisited[0][0] = true;

        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cell[0] + dirX[d];
                int ny = cell[1] + dirY[d];
                if (nx < 0 || ny < 0 || nx >= expandedWidth || ny >= expandedHeight) continue;
                if (outsideVisited[ny][nx]) continue;

                int gridX = nx - 1;
                int gridY = ny - 1;
                if (gridX >= 0 && gridY >= 0 &&
                        gridX < gridWidth && gridY < gridHeight &&
                        blockedGrid[gridY][gridX]) continue;

                outsideVisited[ny][nx] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        boolean[][] allowedGrid = new boolean[gridHeight][gridWidth];
        for (int r = 0; r < gridHeight; r++) {
            for (int c = 0; c < gridWidth; c++) {
                if (blockedGrid[r][c] || !outsideVisited[r + 1][c + 1]) {
                    allowedGrid[r][c] = true;
                }
            }
        }

        long[][] allowedAreaPrefix = new long[gridHeight + 1][gridWidth + 1];
        for (int r = 0; r < gridHeight; r++) {
            long rowSum = 0;
            long height = yLines.get(r + 1) - yLines.get(r);
            for (int c = 0; c < gridWidth; c++) {
                long width = xLines.get(c + 1) - xLines.get(c);
                if (allowedGrid[r][c]) rowSum += width * height;
                allowedAreaPrefix[r + 1][c + 1] = allowedAreaPrefix[r][c + 1] + rowSum;
            }
        }

        long biggestArea = 0;
        List<long[]> redPoints = new ArrayList<>();
        for (long e : redTiles) redPoints.add(new long[]{encoder.getX(e), encoder.getY(e)});

        for (int i = 0; i < redPoints.size(); i++) {
            for (int j = i + 1; j < redPoints.size(); j++) {
                long[] a = redPoints.get(i);
                long[] b = redPoints.get(j);

                int x1 = (int) Math.min(a[0], b[0]);
                int x2 = (int) Math.max(a[0], b[0]);
                int y1 = (int) Math.min(a[1], b[1]);
                int y2 = (int) Math.max(a[1], b[1]);

                long area = (long)(x2 - x1 + 1) * (y2 - y1 + 1);
                if (area <= biggestArea) continue;

                int c1 = xToIndex.get(x1);
                int c2 = xToIndex.get(x2 + 1) - 1;
                int r1 = yToIndex.get(y1);
                int r2 = yToIndex.get(y2 + 1) - 1;

                long allowedArea =
                        allowedAreaPrefix[r2 + 1][c2 + 1]
                                - allowedAreaPrefix[r1][c2 + 1]
                                - allowedAreaPrefix[r2 + 1][c1]
                                + allowedAreaPrefix[r1][c1];

                if (allowedArea == area) {
                    biggestArea = area;
                }
            }
        }

        System.out.println("Biggest valid rectangle area: " + biggestArea);
        return biggestArea;
    }

}
