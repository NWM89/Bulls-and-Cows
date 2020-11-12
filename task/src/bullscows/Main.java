package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Input the length of the secret code:");
        String inputMax = scanner.nextLine();
        int max = 0;
        try {
            max = Integer.parseInt(inputMax);
            if (max < 1) {
                System.out.println("Error: Generation value < 1");
                System.exit(0);
            }
        } catch (Exception ex) {
            System.out.println("Error: \"" + inputMax +"\" isn't a valid number.");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        inputMax = scanner.nextLine();
        int maxSymbols = 0;
        try {
            maxSymbols = Integer.parseInt(inputMax);
            if (maxSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
            if (maxSymbols < max) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
                System.exit(0);
            }
        } catch (Exception ex) {
            System.out.println("Error: \"" + inputMax +"\" isn't a valid number.");
            System.exit(0);
        }
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < max; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-");
        if (maxSymbols < 10) {
            System.out.print(maxSymbols + ").");
        } else {
            if (maxSymbols == 10) {
                System.out.print("9, a).");
            } else {
                System.out.print("9, a-" + returnChar(maxSymbols - 1) + ").");
            }
        }
        System.out.println("\nOkay, let's start a game!");
        String sCode = randomGenerator(max, maxSymbols);
        int turn = 1;
        int bulls = 0;
        int cows = 0;
        String answer = "";
        do {
            System.out.println("Turn " + turn);
            bulls = 0;
            cows = 0;
            answer = scanner.next();
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < max; j++) {
                    if (j == i) {
                        if (answer.charAt(i) == sCode.charAt(i)) {
                            bulls++;
                        }
                    } else {
                        if (answer.charAt(i) == sCode.charAt(j)) {
                            cows++;
                        }
                    }
                }
            }
            printBullsCows(cows, bulls, max);
            turn++;
        } while (turn > 0);
    }

    public static String randomGenerator(int max, int maxSymbols) {
        StringBuilder OutputString = new StringBuilder("");
        Random random = new Random();
        int[] i = new int[max];
        for (int j = 0; j < max; j++) {
            i[j] = random.nextInt(maxSymbols);
        }
        do {
            for (int j = 0; j < max; j++) {
                for (int k = 0; k < max; k++) {
                    if (j != k && i[j] == i[k]) {
                        i[k] = random.nextInt(maxSymbols);
                    }
                }
                OutputString.append(returnChar(i[j]));
            }
            break;
        } while (i[0] > 0);
        return OutputString.toString();
    }

    public static void printBullsCows(int cows, int bulls, int max) {
        if (bulls == max) {
            System.out.println("Grade : " + bulls + " bulls.");
            System.out.println("Congratulations! You guessed the secret code.");
            System.exit(0);
        }
        if (cows == 0 && bulls == 0) {
            System.out.println("Grade: None.");
        } else {
            if (cows > 0) {
                if (bulls > 0) {
                    System.out.println("Grade: " + bulls + " bulls and " + cows + " cows.");
                } else {
                    System.out.println("Grade: " + cows + " cows.");
                }
            } else {
                System.out.println("Grade: " + bulls + " bulls.");
            }
        }
    }

    public static char returnChar(int number) {
        char outputChar;
        if (number >= 10) {
            outputChar = (char) (number + 87);
        } else {
            outputChar = (char) (number + 48);
        }
        return outputChar;
    }
}
