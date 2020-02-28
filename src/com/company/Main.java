package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Играч 1: ");
        String playerOne = input.nextLine();

        System.out.print("Играч 2: ");
        String playerTwo = input.nextLine();
        String[] players = {playerOne, playerTwo};
        String wordToGuess = randomWord().toLowerCase();

        startGame(wordToGuess, players);
    }

    private static void startGame(String wordToGuess, String[] players) {
        char[] enteredLetters = new char[wordToGuess.length()];
        int guessedWords = 0;

        for (int tries = 0; tries <= 5; tries++) {
            printHangMan(tries);
            int player = tries % 2;
            System.out.println(players[player] + " е на ход");
            boolean currentPlayerMove = true;
            do {

                String word = createWord(wordToGuess, enteredLetters);
                if (!word.contains("*")) {
                    System.out.println(players[player] + " печели");
                    System.out.println("Думата е: " + wordToGuess);

                    tries = 6;
                    break;
                }
                System.out.print("Познайте думата: " + word);
                char playerLetter = enterLetter();
                if (!validateLetter(wordToGuess, enteredLetters, playerLetter, guessedWords))
                    currentPlayerMove = false;
            } while (currentPlayerMove);
        }
    }

    public static boolean validateLetter(String wordToGuess, char[] enteredLetters, char playerLetter, int guessedWords) {
        wordToGuess = wordToGuess.toLowerCase();
        int emptyPosition = findEmptyPosition(enteredLetters);

        if (!isCyrillic(playerLetter) || playerLetter == '\u0000') {
            System.out.println("Въведете буква на кирилица!");
            return true;
        }
        if (isEnteredLetter(playerLetter, enteredLetters)) {
            System.out.println("\"" + playerLetter + "\"" + " вече е в думата");
            return true;
        }
        if (isLetterGuessed(wordToGuess, playerLetter)) {
            enteredLetters[emptyPosition] = playerLetter;
            guessedWords++;
            return true;
        } else {
            System.out.println("\"" + playerLetter + "\"" + " я няма в думата.");
            return false;
        }
    }

    public static boolean isLetterGuessed(String wordToGuess, char playerLetter) {
        return wordToGuess.indexOf(String.valueOf(playerLetter)) > -1;
    }

    public static char enterLetter() {
        System.out.print("\nВъведете буква: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine().toLowerCase().charAt(0);
    }

    private static void printHangMan(int tries) {
        switch (tries) {
            case 0:
                System.out.println("Вие започнахте играта \"Бесеница\".");
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                break;
            case 1:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                break;

            case 2:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |   | ");
                System.out.println("    |     ");
                System.out.println("    |     ");
                break;

            case 3:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |  -| ");
                System.out.println("    |     ");
                System.out.println("    |      ");
                break;
            case 4:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |  -|-");
                System.out.println("    |     ");
                System.out.println("    |     ");
                break;
            case 5:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |  -|-");
                System.out.println("    |  /  ");
                System.out.println("    |     ");
                break;

            case 6:
                System.out.println("    _____ ");
                System.out.println("    |   | ");
                System.out.println("    |   O ");
                System.out.println("    |  -|-");
                System.out.println("    |  / \\");
                System.out.println("    |     ");
                System.out.println("   GAME OVER");
                break;
        }

    }

    public static String createWord(String wordToGuess, char[] enteredLetters) {
        //System.out.print("Познайте думата: ");
        String word = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            char letter = wordToGuess.charAt(i);
            if (isEnteredLetter(letter, enteredLetters)) {
                if (i == 0 || wordToGuess.charAt(i - 1) == ' ') {
                    word += Character.toUpperCase(letter);
                    //System.out.print();
                } else {
                    word += letter;
                    //System.out.print(letter);
                }
            } else if (letter == ' ') {
                word += "";
                //System.out.print(' ');
            } else {
                //System.out.print('*');
                word += "*";
            }
        }
        return word;
        //System.out.println();
    }

    public static boolean isEnteredLetter(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }

    private static String randomWord() {
        String[] words = {//"Благоевград", "Бургас", "Варна", "Велико Търново", "Видин",
                // "Враца", "Габрово", "Добрич", "Кърджали", "Кюстендил", "Ловеч", "Монтана",
                //  "Пазарджик", "Перник", "Плевен", "Пловдив", "Разград", "Русе", "Сливен",
                "Смолян", "София", "Стара Загора", "Търговище", "Шумен", "Ямбол"};

        int randomWordNumber = (int) (Math.random() * words.length);
        return words[randomWordNumber];
    }

    public static int findEmptyPosition(char[] enteredLetters) {
        int i = 0;
        while (i < enteredLetters.length && enteredLetters[i] != '\u0000') i++;
        return i;
    }

    public static boolean isCyrillic(char c) {
        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
    }
}