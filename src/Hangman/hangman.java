package Hangman;
import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = {"python", "java", "javascript", "kotlin"};
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("HANGMAN");
            System.out.println("Type 'play' to play the game, 'exit' to quit:");
            String command = scanner.nextLine();

            if (command.equals("play")) {
                playGame(words, scanner);
            } else if (command.equals("exit")) {
                keepPlaying = false;
            } else {
                System.out.println("Invalid command. Please type 'play' or 'exit'.");
            }
        }

        scanner.close();
    }

    public static void playGame(String[] words, Scanner scanner) {
        String chosenWord = words[new Random().nextInt(words.length)];
        StringBuilder displayWord = new StringBuilder("-".repeat(chosenWord.length()));
        HashSet<Character> guessedLetters = new HashSet<>();
        int mistakes = 0;
        int maxMistakes = 8;

        while (mistakes < maxMistakes && displayWord.toString().contains("-")) {
            System.out.println("Current word: " + displayWord);
            System.out.println("Input a letter: > ");
            String input = scanner.nextLine();

            if (input.length() != 1 || !Character.isLowerCase(input.charAt(0))) {
                System.out.println("Please enter a lowercase English letter.");
                continue;
            }

            char letter = input.charAt(0);

            if (guessedLetters.contains(letter)) {
                System.out.println("You've already guessed this letter.");
                continue;
            }

            guessedLetters.add(letter);

            if (chosenWord.indexOf(letter) >= 0) {
                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == letter) {
                        displayWord.setCharAt(i, letter);
                    }
                }
            } else {
                System.out.println("That letter doesn't appear in the word.");
                mistakes++;
            }
        }

        if (displayWord.toString().equals(chosenWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost! The word was " + chosenWord + ".");
        }
    }
}

