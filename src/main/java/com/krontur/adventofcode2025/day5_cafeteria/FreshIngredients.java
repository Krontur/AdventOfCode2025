package com.krontur.adventofcode2025.day5_cafeteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class FreshIngredients {


    public static long getFreshIngredients(BufferedReader br) throws IOException {

        if(br == null) return -1;
        long freshIngredientsCount = 0;
        boolean checkIngredients = false;
        List<long[]> freshIngredientsSet = new ArrayList<>();
        List<Long> ingredientsToCheck = new ArrayList<>();
        while(br.ready()){
            String line = br.readLine();
            if(line == null) break;
            if(line.trim().isEmpty()){
                checkIngredients = true;
                continue;
            }
            if(checkIngredients){
                ingredientsToCheck.add(Long.parseLong(line));
            } else {
                long lowerBound = Long.parseLong(line.split("-")[0].trim());
                long upperBound = Long.parseLong(line.split("-")[1].trim());
                freshIngredientsSet.add(new long[]{lowerBound, upperBound});
            }
        }

        freshIngredientsSet.sort(Comparator.comparing(a -> a[0]));
        List<long[]> mergedFreshIngredientsSet = new ArrayList<>();
        for(int rangeIndex = 0; rangeIndex < freshIngredientsSet.size(); rangeIndex++){
            if(rangeIndex + 1 >= freshIngredientsSet.size()){
                mergedFreshIngredientsSet.add(freshIngredientsSet.get(rangeIndex));
                break;
            }
            long[] currentRange = freshIngredientsSet.get(rangeIndex);
            long[] nextRange = freshIngredientsSet.get(rangeIndex + 1);
            if(currentRange[1] >= nextRange[0]){
                long mergedLowerBound = currentRange[0];
                long mergedUpperBound = Math.max(currentRange[1], nextRange[1]);
                mergedFreshIngredientsSet.add(new long[]{mergedLowerBound, mergedUpperBound});
                rangeIndex++;
            } else {
                mergedFreshIngredientsSet.add(currentRange);
            }
        }

        mergedFreshIngredientsSet.stream().map(Arrays::toString).forEach(System.out::println);
        for (long ingredient : ingredientsToCheck) {
            boolean isFresh = false;
            for (long[] freshRange : mergedFreshIngredientsSet) {
                if (ingredient >= freshRange[0] && ingredient <= freshRange[1]) {
                    isFresh = true;
                    break;
                }
            }
            if (isFresh) {
                freshIngredientsCount++;
            }
        }

        return freshIngredientsCount;
    }

    public static long getPosibleFreshIngredientsCount(BufferedReader br) throws IOException {

        if(br == null) return -1;
        long posibleFreshIngredientsCount = 0;
        List<long[]> freshIngredientsSet = new ArrayList<>();
        while(br.ready()){
            String line = br.readLine();
            if(line == null) break;
            long lowerBound = Long.parseLong(line.split("-")[0].trim());
            long upperBound = Long.parseLong(line.split("-")[1].trim());
            freshIngredientsSet.add(new long[]{lowerBound, upperBound});
        }

        freshIngredientsSet.sort(Comparator.comparing(a -> a[0]));
        List<long[]> mergedFreshIngredientsSet;
        int previousSize;
        do {
            previousSize = freshIngredientsSet.size();
            mergedFreshIngredientsSet = mergeRanges(freshIngredientsSet);
            freshIngredientsSet = mergedFreshIngredientsSet;
        } while (mergedFreshIngredientsSet.size() < previousSize);

        mergedFreshIngredientsSet.stream().map(Arrays::toString).forEach(System.out::println);

        for(long[] freshRange : mergedFreshIngredientsSet){
            posibleFreshIngredientsCount += (freshRange[1] - freshRange[0] + 1);
        }

        return posibleFreshIngredientsCount;
    }

    private static List<long[]> mergeRanges(List<long[]> freshIngredientsSetToMerge) {


        List<long[]> mergedFreshIngredientsSetResult = new ArrayList<>();

        for(int rangeIndex = 0; rangeIndex < freshIngredientsSetToMerge.size(); rangeIndex++){
            if(rangeIndex + 1 >= freshIngredientsSetToMerge.size()){
                mergedFreshIngredientsSetResult.add(freshIngredientsSetToMerge.get(rangeIndex));
                break;
            }
            long[] currentRange = freshIngredientsSetToMerge.get(rangeIndex);
            long[] nextRange = freshIngredientsSetToMerge.get(rangeIndex + 1);
            if(currentRange[1] >= nextRange[0] || currentRange[1] + 1 == nextRange[0]){
                long mergedLowerBound = currentRange[0];
                long mergedUpperBound = Math.max(currentRange[1], nextRange[1]);
                mergedFreshIngredientsSetResult.add(new long[]{mergedLowerBound, mergedUpperBound});
                rangeIndex++;
            } else {
                mergedFreshIngredientsSetResult.add(currentRange);
            }
        }
        return mergedFreshIngredientsSetResult;
    }
}
