package org.example;

import org.example.Helpers.FileScanner;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        String fileName = "Day2Input.txt";

        List<String[]> allRounds = getAllRounds(fileName);

        int result = getTotalScore(allRounds);

        System.out.println("The total score is: " + result);
    }

    private static List<String[]> getAllRounds(String fileName) throws FileNotFoundException, URISyntaxException {
        List<String> lines = FileScanner.getLines(fileName);
        List<String[]> rounds = new ArrayList<>();

        for(String line: lines) {
            rounds.add(getOneRound(line));
        }
        return rounds;
    }

    private static int getTotalScore(List<String[]> allRounds) {
        int totalScore = 0;

        for (String[] oneRound: allRounds) {
            // Part one
            // String myPick = oneRound[1];
            // Part two
            String myPick = determineInputToBe(oneRound[0], oneRound[1]);

            totalScore += determineScoreBasedOnPick(myPick);

            if (determineSameInput(oneRound[0], myPick)) {
                totalScore += 3;
            } else if (determineWinOrLoss(oneRound[0], myPick)){
                totalScore += 6;
            }
        }
        return totalScore;
    }

    private static String[] getOneRound(String line) {
        return line.split(" ");
    }

    private static int determineScoreBasedOnPick(String input) {
        switch (input) {
            case "A", "X" -> {
                return 1;
            }
            case "B", "Y" -> {
                return 2;
            }
            case "C", "Z" -> {
                return 3;
            }
            default -> {
                return 0;
            }
        }
    }
    private static boolean determineWinOrLoss(String input1, String input2) {
        switch (input1) {
            case "A" -> {
                return input2.equals("Y");
            }
            case "B" -> {
                return input2.equals("Z");
            }
            case "C" -> {
                return input2.equals("X");
            }
            default -> {
                return false;
            }
        }
    }

    private static boolean determineSameInput(String input1, String input2) {
        switch(input1) {
            case "A" -> {
                return input2.equals("X");
            }
            case "B" -> {
                return input2.equals("Y");
            }
            case "C" -> {
                return input2.equals("Z");
            }
            default -> {
                return false;
            }
        }
    }

    private static String determineInputToBe(String input1, String input2) {
        switch (input2) {
            case "X" -> {
                if (input1.equals("A")) {
                    return "Z";
                } else if (input1.equals("B")) {
                    return "X";
                } else {
                    return "Y";
                }
            }
            case "Y" -> {
                if (input1.equals("A")) {
                    return "X";
                } else if (input1.equals("B")) {
                    return "Y";
                } else {
                    return "Z";
                }
            }
            case "Z" -> {
                if (input1.equals("A")) {
                    return "Y";
                } else if (input1.equals("B")) {
                    return "Z";
                } else {
                    return "X";
                }
            }
            default -> {
                return "";
            }
        }
    }
}
