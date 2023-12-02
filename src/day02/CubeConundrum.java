package day02;

import util.InputUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubeConundrum {
    public static void main(String[] args) {
        List<String> input = InputUtil.readAllLinesFromFile("src/day02/input.txt");

        System.out.println("Part One: " + partOne(input));
        System.out.println("Part Two: " + partTwo(input));
    }

    private static int partOne(List<String> input) {
        int sum = 0;

        Map<String, Integer> cubes = new HashMap<>();
        cubes.put("red", 12);
        cubes.put("green", 13);
        cubes.put("blue", 14);

        for (String line : input) {
            // divide into id and sets
            String[] gameParts = line.split(":");

            // split the sets
            String[] sets = (gameParts[1].trim().split(";"));

            // check if all the sets are possible
            boolean isPossible = true;
            // divide the set into "{count}, {color}..."
            for (String set : sets) {
                String[] setParts = (set.trim().split(" "));

                // check all colors against the number of cubes
                for (int i = 0; i < setParts.length; i = i + 2) {
                    String color = setParts[i + 1].replaceAll("[^a-z]", "");
                    int cubesOfColor = cubes.get(color);
                    int count = Integer.parseInt(setParts[i]);

                    if (count > cubesOfColor) {
                        isPossible = false;
                        break;
                    }
                }
            }

            if (isPossible) {
                // extract the id by replacing all but digits from "Game {id}:"
                int id = Integer.parseInt(gameParts[0].replaceAll("\\D+", "").trim());
                sum += id;
            }
        }

        return sum;
    }

    private static int partTwo(List<String> input) {
        int sum = 0;

        for (String line : input) {
            Map<String, Integer> cubes = new HashMap<>();
            cubes.put("red", 0);
            cubes.put("green", 0);
            cubes.put("blue", 0);

            // divide into id and sets
            String[] gameParts = line.split(":");

            // split the sets
            String[] sets = (gameParts[1].trim().split(";"));

            // divide the set into "{count}, {color}..."
            for (String set : sets) {
                String[] setParts = (set.trim().split(" "));

                // determine the largest amount of each color (minimum set of cubes)
                for (int i = 0; i < setParts.length; i = i + 2) {
                    String color = setParts[i + 1].replaceAll("[^a-z]", "");
                    int cubesOfColor = cubes.get(color);
                    int count = Integer.parseInt(setParts[i]);

                    if (count > cubesOfColor) {
                        cubes.put(color, count);
                    }
                }
            }

            // calculate the power of the minimum set
            int power = 1;

            for (int value : cubes.values()) {
                power *= value;
            }

            sum += power;
        }

        return sum;
    }
}
