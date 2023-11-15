package org.example;

import org.example.Helpers.FileScanner;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    // Part one
    // boolean completeOverlap = true;
    // Part two
    static boolean completeOverlap = false;

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        String fileName = "Day4Input.txt";

        List<String> lines = FileScanner.getLines(fileName);

        int amountOfOverlaps = findAmountOfOverlaps(lines);

        System.out.println("Amount of overlaps: " + amountOfOverlaps);
    }

    private static int findAmountOfOverlaps(List<String> lines) {
        int counter = 0;

        for (String line : lines) {
            List<Integer> sectionIds = new ArrayList<>();

            String[] oneGroup = line.split(",");

            for (String oneElf : oneGroup) {
                String[] sections = oneElf.split("-");
                for (String sectionId : sections) {
                    sectionIds.add(Integer.valueOf(sectionId));
                }
            }
            if (findOverlap(completeOverlap, sectionIds.get(0), sectionIds.get(1), sectionIds.get(2), sectionIds.get(3))) {
                counter++;
            }
        }
        return counter;
    }

    private static boolean findOverlap(boolean isCompleteOverlap, int firstSectionIdOfFirstElf, int secondSectionIdOfFirstElf, int firstSectionIdOfSecondElf, int secondSectionIdOfSecondElf) {
        if (!isCompleteOverlap) {
            if (firstSectionIdOfFirstElf <= secondSectionIdOfSecondElf && firstSectionIdOfFirstElf >= firstSectionIdOfSecondElf) {
                return true;
            } else if (secondSectionIdOfFirstElf <= secondSectionIdOfSecondElf && secondSectionIdOfFirstElf >= firstSectionIdOfSecondElf) {
                return true;
            } else if (firstSectionIdOfFirstElf <= firstSectionIdOfSecondElf && secondSectionIdOfFirstElf >= secondSectionIdOfSecondElf) {
                return true;
            }
        }

        if (firstSectionIdOfFirstElf < firstSectionIdOfSecondElf) {
            return secondSectionIdOfFirstElf >= secondSectionIdOfSecondElf;
        } else if (firstSectionIdOfFirstElf > firstSectionIdOfSecondElf) {
            return secondSectionIdOfFirstElf <= secondSectionIdOfSecondElf;
        } else {
            return true;
        }
    }
}
