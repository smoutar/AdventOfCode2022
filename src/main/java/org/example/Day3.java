package org.example;

import org.example.Helpers.FileScanner;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    // ASCII
    // A = 65
    // a = 97
    // Priority
    // A = 27
    // a = 1
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        String fileName = "Day3Input.txt";

        List<String> lines = FileScanner.getLines(fileName);

        List<String> firstCompartment = getACompartment(true, lines);
        List<String> secondCompartment = getACompartment(false, lines);
        List<Character> sharedItems = getSharedItems(firstCompartment, secondCompartment);

        List<Integer> priorities = convertPriorities(sharedItems);

        System.out.println("Part one: ");
        getSumOfAllPriorities(priorities);

        // Part two
        List<Character> sharedItemsForGroupsOfThree = getSharedItemsForAllGroupsOfThree(lines);
        List<Integer> prioritiesForGroupsOfThree = convertPriorities(sharedItemsForGroupsOfThree);

        System.out.println("Part two: ");
        getSumOfAllPriorities(prioritiesForGroupsOfThree);
    }

    private static List<String> getACompartment(boolean firstCompartment, List<String> lines) {
        List<String> itemsPerElf = new ArrayList<>();

        for(String line: lines) {
            int halfOfString = line.length() / 2;

            if (firstCompartment) {
                itemsPerElf.add(line.substring(0, halfOfString));
            } else {
                itemsPerElf.add(line.substring(halfOfString));
            }
        }
        return itemsPerElf;
    }

    private static List<Character> getSharedItems(List<String> firstCompartment, List<String> secondCompartment) {
        List<Character> sharedItems = new ArrayList<>();

        for (int i = 0; i < firstCompartment.size(); i++) {
            for (char item: secondCompartment.get(i).toCharArray()) {
                int containsItem = firstCompartment.get(i).indexOf(item);
                if (containsItem >= 0) {
                    sharedItems.add(item);
                    break;
                }
            }
        }
        return sharedItems;
    }

    private static List<Integer> convertPriorities(List<Character> items) {
        List<Integer> priorities = new ArrayList<>();

        for (char item: items) {
            int result = item;

            if (Character.isUpperCase(item)) {
                result -= 38;
            } else {
                result -= 96;
            }
            priorities.add(result);
        }
        return priorities;
    }

    private static void getSumOfAllPriorities(List<Integer> priorities) {
        System.out.println("Total sum of all priorities: ");
        System.out.println(priorities.stream()
                .reduce(0, Integer::sum));
    }

    private static List<Character> getSharedItemsForAllGroupsOfThree(List<String> lines) {
        List<Character> priorities = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 3) {
            List<String> groupOfThree = new ArrayList<>();

            for (int x = 0; x < 3; x++) {
                String itemsOfOneElf = lines.get(x + i);
                groupOfThree.add(itemsOfOneElf);
            }

            priorities.add(findSharedItemInGroupOfThree(groupOfThree));
        }
        return priorities;
    }

    private static char findSharedItemInGroupOfThree(List<String> groupOfThree) {
        char sharedItem = ' ';

        for(char item: groupOfThree.get(0).toCharArray()) {
            if (groupOfThree.get(1).indexOf(item) > -1 && groupOfThree.get(2).indexOf(item) > -1) {
                sharedItem = item;
            }
        }
        return sharedItem;
    }


}
