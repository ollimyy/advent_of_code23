package day04;

import util.InputUtil;

import java.util.Arrays;
import java.util.List;

public class Scratchcards {
    public static void main(String[] args) {
        List<String> input = InputUtil.readAllLinesFromFile("src/day04/input.txt");

        System.out.println("Part One: " + partOne(input));
    }

    private static int partOne(List<String> input) {
        int sum = 0;

        for (String line : input) {
            String[] numbers = line.split(":")[1].split("[|]");
            List<String> winningNumbers = Arrays.asList(numbers[0].split("\\s+"));
            boolean first = true;
            int points = 0;

            for (String myNumber : numbers[1].trim().split("\\s+")) {
                if(winningNumbers.contains(myNumber)) {
                    if(first) {
                        points += 1;
                        first = false;
                    } else {
                        points *= 2;
                    }
                }
            }
            sum += points;
        }
        return sum;
    }
}
