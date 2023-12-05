package day03;

import util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {
    public static void main(String[] args) {
        List<String> input = InputUtil.readAllLinesFromFile("src/day03/input.txt");

        System.out.println(partOne(input));
    }

    private static int partOne(List<String> input) {
        record PossiblePartNumber(int value, int row, int start, int end){ }
        List<PossiblePartNumber> numbers = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("\\d+"); // one or more digits

        // find all numbers and their positions
        for(int i = 0; i < input.size(); i++) {
           Matcher numberMatcher = numberPattern.matcher(input.get(i));

            while (numberMatcher.find()) {
                int number = Integer.parseInt(numberMatcher.group());
                int startIndex = numberMatcher.start();
                int endIndex = numberMatcher.end();

                numbers.add(new PossiblePartNumber(number, i, startIndex, endIndex));
            }
        }

        Pattern symbolPattern = Pattern.compile("[^\\d.]"); // everything but numbers and periods
        int sum = 0;

        // check around all numbers for symbols
        for(PossiblePartNumber number : numbers) {
            StringBuilder searchString = new StringBuilder();
            int currentRow = Math.min(input.size(), Math.max(0, number.row - 1));
            // check the row above the number, below the number and the row the number is in
            while(currentRow <= Math.min(input.size() - 1, number.row + 1)) {
                // check from one index before start of number to one index after end of number
                int searchStart =  Math.max(0, number.start - 1);
                int searchEnd = Math.min(input.get(0).length(), number.end + 1);
                searchString.append(input.get(currentRow), searchStart, searchEnd);

                currentRow++;
            }

            // if the characters around the number includes a symbol add the number to the sum
            Matcher symbolMatcher = symbolPattern.matcher(searchString.toString());
            if(symbolMatcher.find()) {
                sum += number.value;
            }

        }
        return sum;
    }
}
