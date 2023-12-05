package day03;

import util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    record Number(int value, int row, int start, int end) {
    }

    public static void main(String[] args) {
        List<String> input = InputUtil.readAllLinesFromFile("src/day03/input.txt");
        List<Number> numbers = findNumbers(input);

        System.out.println("Part One: " + partOne(input, numbers));
        System.out.println("Part Two: " + partTwo(input, numbers));
    }


    private static List<Number> findNumbers(List<String> input) {
        List<Number> numbers = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("\\d+"); // one or more digits

        // find all numbers and their positions
        for (int i = 0; i < input.size(); i++) {
            Matcher numberMatcher = numberPattern.matcher(input.get(i));

            while (numberMatcher.find()) {
                int value = Integer.parseInt(numberMatcher.group());
                int startIndex = numberMatcher.start();
                int endIndex = numberMatcher.end() - 1; // index of last matching character

                numbers.add(new Number(value, i, startIndex, endIndex));
            }
        }
        return numbers;
    }

    private static int partOne(List<String> input, List<Number> numbers) {
        int sum = 0;
        Pattern symbolPattern = Pattern.compile("[^\\d.]"); // everything but numbers and periods

        // check around all numbers for symbols
        for (Number number : numbers) {
            StringBuilder searchString = new StringBuilder();
            int currentRow = Math.min(input.size(), Math.max(0, number.row - 1));
            // check the row above the number, below the number and the row the number is in
            while (currentRow <= Math.min(input.size() - 1, number.row + 1)) {
                // check from one index before start of number to one index after end of number
                int searchStart = Math.max(0, number.start - 1);
                int searchEnd = number.end + 1;
                // include char at searchEnd to substring
                searchString.append(input.get(currentRow), searchStart, Math.min(input.get(0).length(), searchEnd + 1));

                currentRow++;
            }

            // if the characters around the number includes a symbol add the number to the sum
            Matcher symbolMatcher = symbolPattern.matcher(searchString.toString());
            if (symbolMatcher.find()) {
                sum += number.value;
            }

        }
        return sum;
    }

    private static int partTwo(List<String> input, List<Number> numbers) {
        record Star(int row, int index) {
        }
        List<Star> stars = new ArrayList<>();
        Pattern starPattern = Pattern.compile("\\*"); // stars

        // find all stars and their positions
        for (int i = 0; i < input.size(); i++) {
            Matcher starMatcher = starPattern.matcher(input.get(i));

            while (starMatcher.find()) {
                int index = starMatcher.start();

                stars.add(new Star(i, index));
            }
        }

        int sum = 0;

        // check all stars
        for (Star star : stars) {
            List<Integer> adjacentNumbers = new ArrayList<>();

            // check all numbers
            for (Number number : numbers) {
                // check is number on the star's row, the previous row and the next row
                if (star.row - 1 <= number.row && number.row <= star.row + 1) {
                    // check is number adjacent to star
                    if ((-1 <= star.index - number.start && star.index - number.start <= 1) ||
                            (-1 <= star.index - number.end && star.index - number.end <= 1)) {
                        adjacentNumbers.add(number.value);
                    }
                }
            }

            // a gear is a star that has exactly 2 adjacent numbers, calculate ratio and add to sum
            if (adjacentNumbers.size() == 2) {
                int ratio = 1;
                for (int number : adjacentNumbers) {
                    ratio *= number;
                }
                sum += ratio;
            }
        }

        return sum;
    }
}
