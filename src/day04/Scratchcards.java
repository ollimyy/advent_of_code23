package day04;

import util.InputUtil;

import java.util.*;

public class Scratchcards {
    public static void main(String[] args) {
        List<String> input = InputUtil.readAllLinesFromFile("src/day04/input.txt");

        System.out.println("Part One: " + partOne(input));
        System.out.println("Part Two: " + partTwo(input));

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

    private static int partTwo(List<String> input) {
        record ScratchCard(int id, List<String> winningNumbers, String[] myNumbers){
        }

        Map<Integer, ScratchCard> originalCards = new HashMap<>();
        List<ScratchCard> cards = new ArrayList<>();

        // parse all the cards
        for (String line : input) {
            String[] lineParts = line.split(":");
            int id = Integer.parseInt(lineParts[0].replaceAll("[^\\d]", ""));
            String[] numbers = lineParts[1].split("[|]");
            List<String> winningNumbers = Arrays.asList(numbers[0].trim().split("\\s+"));
            String[] myNumbers = numbers[1].trim().split("\\s+");

            ScratchCard card = new ScratchCard(id, winningNumbers, myNumbers);
            originalCards.put(id, card);
            cards.add(card);
        }

        // go through all the original cards and copies and copies of copies and ...
        for(int i = 0; i < cards.size(); i++) {
            ScratchCard card = cards.get(i);
            // check if my numbers are winning numbers
            int matches = 0;

            // calculate matches for this card
            for(String myNumber : card.myNumbers) {
                if(card.winningNumbers.contains(myNumber)) {
                    matches += 1;
                }
            }

            // add copies
            for(int j = card.id + 1; j <= card.id + matches; j++){
                cards.add(originalCards.get(j));
            }
        }

        return cards.size();
    }
}
