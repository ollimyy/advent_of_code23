
package day01;

import util.InputUtil;

import java.util.*;

public class Trebuchet {
    public static void main(String[] args) {
        List<String> inputLines = InputUtil.readAllLinesFromFile("src/day01/input.txt");

        System.out.println("Part One: " + partOne(inputLines));
        System.out.println("Part Two: " + partTwo(inputLines));
    }

    private static int partOne(List<String> inputLines) {

        int sum = 0;

        for (String line : inputLines) {
            // separate digits from the input
            String digits = (line.replaceAll("[a-zA-Z]", ""));
            // get first and last digit
            int calibrationValue = Integer.parseInt(digits.charAt(0) + "" + (digits.charAt(digits.length() - 1)));
            sum += calibrationValue;
        }

        return sum;
    }

    private static int partTwo(List<String> inputLines) {

        Map<String, String> digitMap = new HashMap<>();
        /*  eightwothree -> e8t2ot3e -> 83
            instead of: eightwothree -> eigh23 -> 23 */
        digitMap.put("one", "o1e");
        digitMap.put("two", "t2o");
        digitMap.put("three", "t3e");
        digitMap.put("four", "f4r");
        digitMap.put("five", "f5e");
        digitMap.put("six", "s6x");
        digitMap.put("seven", "s7n");
        digitMap.put("eight", "e8t");
        digitMap.put("nine", "n9e");

        Set<String> digitsSpelled = digitMap.keySet();
        List<String> formattedInputLines = new ArrayList<>();

        for (String line : inputLines) {
            // replace spelled digits with numbers
            for (String digitSpelled : digitsSpelled) {
                line = line.replaceAll(digitSpelled, digitMap.get(digitSpelled));
            }

            formattedInputLines.add(line);
        }

        // calculate sum of values made by first and last digit on each line
        return partOne(formattedInputLines);
    }
}
