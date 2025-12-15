package com.krontur.adventofcode2025.day8_playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitJunctionBoxesConnector {

    public static void mergeSetLists(List<Set<Integer>> connectedBoxes){
        boolean merged;
        do {
            merged = false;
            for (int i = 0; i < connectedBoxes.size() - 1; i++) {
                for (int j = i + 1; j < connectedBoxes.size(); j++) {
                    Set<Integer> setA = connectedBoxes.get(i);
                    Set<Integer> setB = connectedBoxes.get(j);
                    Set<Integer> intersection = new HashSet<>(setA);
                    intersection.retainAll(setB);
                    if (!intersection.isEmpty()) {
                        setA.addAll(setB);
                        connectedBoxes.set(i, setA);
                        connectedBoxes.remove(j);
                        merged = true;
                        break;
                    }
                }
                if (merged) {
                    break;
                }
            }
        } while (merged);
    }

    private static List<int[]> readBoxes(BufferedReader input) throws IOException {
        List<int[]> boxes = new ArrayList<>();
        if(input==null) return boxes;
        while (input.ready()){
            String line = input.readLine();
            if(line == null || line.isEmpty()) break;
            String[] parts = line.split(",");
            boxes.add(new int[]{Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim())});

        }
        return boxes;
    }

    private static Map<Double, String> calculateDistances(List<int[]> boxes) {
        Map<Double, String> connections = new HashMap<>();

        for(int i=0;i<boxes.size() - 1;i++){
            for (int j=i+1;j<boxes.size();j++){

                double dx = boxes.get(i)[0] - boxes.get(j)[0];
                double dy = boxes.get(i)[1] - boxes.get(j)[1];
                double dz = boxes.get(i)[2] - boxes.get(j)[2];
                double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);
                connections.put(distance, i + ", " + j);
            }
        }
        return connections;
    }

    public static int getConnectedBoxesCount(BufferedReader input, int times) throws IOException {

        List<int[]> boxes = readBoxes(input);
        AtomicInteger connectedBoxesCount = new AtomicInteger(1);

        Map<Double, String> connections = calculateDistances(boxes);

        List<Set<Integer>> connectedBoxes = new ArrayList<>();

        connections.entrySet().stream().sorted(Comparator.comparingDouble(
                Map.Entry::getKey
        )).limit(times).forEach(entry -> {
            System.out.println("Distance: " + entry.getKey() + " between boxes: " + entry.getValue());
            String[] boxIndices = entry.getValue().split(", ");
            int junctionBoxA = Integer.parseInt(boxIndices[0]);
            int junctionBoxB = Integer.parseInt(boxIndices[1]);

            boolean boxAFound = false;
            boolean boxBFound = false;
            for(Set<Integer> boxSet : connectedBoxes){
                if(boxSet.contains(junctionBoxA)){
                    boxAFound = true;
                    boxSet.add(junctionBoxB);
                }
                if(boxSet.contains(junctionBoxB)){
                    boxBFound = true;
                    boxSet.add(junctionBoxA);
                }
            }
            if(!boxAFound && !boxBFound){
                Set<Integer> newBoxSet = new HashSet<>();
                newBoxSet.add(junctionBoxA);
                newBoxSet.add(junctionBoxB);
                connectedBoxes.add(newBoxSet);
            }

        });

        mergeSetLists(connectedBoxes);
        System.out.println("After merging connected boxes sets:");
        // Ordenar por tamaño descendente y mostrar las 3 mayores con su tamaño
        connectedBoxes.stream()
                .sorted(Comparator.comparingInt((Set<Integer> s) -> s.size()).reversed())
                .limit(3)
                .forEach(s -> connectedBoxesCount.updateAndGet(v -> v * s.size()));



        System.out.println("Connected boxes sets: " + connectedBoxes);
        return connectedBoxesCount.get();
    }

    private static class BoxConnection implements Comparable<BoxConnection> {
        int boxA;
        int boxB;
        double distance;

        public BoxConnection(int boxA, int boxB, double distance) {
            this.boxA = boxA;
            this.boxB = boxB;
            this.distance = distance;
        }

        @Override
        public int compareTo(BoxConnection o) {
            return (int) (this.distance - o.distance);
        }
    }

    private static class Graph {
        int V, E;
        BoxConnection[] edges;

        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new BoxConnection[E];
        }

        int find(int[] parent, int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        void union(int[] parent, int x, int y) {
            int xset = find(parent, x);
            int yset = find(parent, y);
            parent[xset] = yset;
        }

        BoxConnection kruskalMST() {
            BoxConnection[] result = new BoxConnection[V - 1];
            int e = 0;
            int i = 0;

            Arrays.sort(edges);
            int[] parent = new int[V];
            Arrays.fill(parent, -1);

            while (e < V - 1) {
                BoxConnection next_edge = edges[i++];

                int x = find(parent, next_edge.boxA);
                int y = find(parent, next_edge.boxB);

                if (x != y) {
                    result[e++] = next_edge;
                    union(parent, x, y);
                }
            }

            System.out.println("Following are the edges in the constructed MST");
            for (i = 0; i < e; i++)
                System.out.println(result[i].boxA + " -- " + result[i].boxB + " == " + result[i].distance);
            return result[e - 1];
        }

    }

    public static long getConnectedBoxesCountTogether(BufferedReader br) {

        try {
            List<int[]> boxes = readBoxes(br);
            Map<Double, String> connections = calculateDistances(boxes);

            Graph graph = new Graph(boxes.size(), connections.size());
            int index = 0;
            for (Map.Entry<Double, String> entry : connections.entrySet()) {
                String[] boxIndices = entry.getValue().split(", ");
                int junctionBoxA = Integer.parseInt(boxIndices[0]);
                int junctionBoxB = Integer.parseInt(boxIndices[1]);
                double distance = entry.getKey();
                graph.edges[index++] = new BoxConnection(junctionBoxA, junctionBoxB, distance);
            }

            BoxConnection last = graph.kruskalMST();
            if(last == null) return 0L;

            long junctionBoxA = boxes.get(last.boxA)[0];
            long junctionBoxB = boxes.get(last.boxB)[0];

            return junctionBoxA * junctionBoxB;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



