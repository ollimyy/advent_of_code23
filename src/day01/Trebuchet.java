package day01;

import util.InputUtil;

import java.util.List;

public class Trebuchet {
    public static void main(String[] args) {
        List<String> inputLines = InputUtil.readAllLinesFromFile("src/day01/input.txt");

        int sum = 0;

        for (String line : inputLines) {
            // separate digits from the input
            String digits = (line.replaceAll("[a-zA-Z]", ""));
            // get first and last digit
            int calibrationValue = Integer.parseInt(digits.charAt(0) + "" + (digits.charAt(digits.length() - 1)));
            sum += calibrationValue;
        }

        System.out.println(sum);
    }
}
