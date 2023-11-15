package org.example;

import org.example.Helpers.FileScanner;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        String fileName = "Day1Input.txt";

        List<String> lines = FileScanner.getLines(fileName);
        List<Integer> elves = getAllCaloriesPerElf(lines);

        determineMostCalories(elves);
        determineTop3MostCalories(elves);
    }

    private static List<Integer> getAllCaloriesPerElf(List<String> lines) throws FileNotFoundException, URISyntaxException {
        int totalForElf = 0;
        List<Integer> caloriesPerElf = new ArrayList<>();

        for(String line: lines) {
            if (!line.equals("")) {
                totalForElf += Integer.parseInt(line);
            } else {
                caloriesPerElf.add(totalForElf);
                totalForElf = 0;
            }
        }
        return caloriesPerElf;
    }

    private static void determineMostCalories(List<Integer> elves) {
        System.out.println("The most calories: ");
        System.out.println(elves.stream()
                .mapToInt(v -> v)
                .max()
                .getAsInt());
    }

    private static void determineTop3MostCalories(List<Integer> elves) {
        List<Integer> sortedElves = elves.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        int total = 0;

        for (int i = 0; i < 3; i++) {
            total += sortedElves.get(i);
        }

        System.out.println("Total of top 3 most calories: " + total);
    }

}
