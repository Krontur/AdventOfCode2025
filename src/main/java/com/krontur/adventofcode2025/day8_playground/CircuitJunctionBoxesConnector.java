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
    
    public static int getConnectedBoxesCount(BufferedReader input, int times) throws IOException {
        
        List<int[]> boxes = new ArrayList<>();
        AtomicInteger connectedBoxesCount = new AtomicInteger(1);
        if(input==null) return 0;
        while (input.ready()){
            String line = input.readLine();
            if(line == null || line.isEmpty()) break;
            String[] parts = line.split(",");
            boxes.add(new int[]{Integer.parseInt(parts[0].trim()),
                                Integer.parseInt(parts[1].trim()), 
                                Integer.parseInt(parts[2].trim())});
            
        }
        
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

}
