package com.krontur.adventofcode2025.day2_gift_shop;

import java.util.Arrays;
import java.util.Iterator;

public class InvalidIDFinder {

    public static boolean isValidID(String id){
        if (id.length() % 2 != 0) {
            return true;
        }
        long idFirstHalf = Long.parseLong(id.substring(0, id.length() / 2));
        long idSecondHalf = Long.parseLong(id.substring(id.length() / 2));
        if(idFirstHalf == idSecondHalf){
            return false;
        } else {
            return true;
        }
    }

    public static long findInvalidIDSum(String[] input){
        Iterator<String> inputIterator = Arrays.stream(input).toList().listIterator();
        long invalidIDSum = 0;
        while (inputIterator.hasNext()){
            String[] id = inputIterator.next().split("-");
            long startID = Long.parseLong(id[0].trim());
            long endID = Long.parseLong(id[1].trim());
            for(long idToCheck = startID; idToCheck <= endID; idToCheck++){
                if (!isValidID(String.valueOf(idToCheck))){
                    System.out.println("Found invalid ID: " + idToCheck);
                    invalidIDSum += idToCheck;
                }
            }
        }
        return invalidIDSum;
    }

}
