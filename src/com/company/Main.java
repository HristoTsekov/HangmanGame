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
        String wordToGuess = randomWord();
        startGame(wordToGuess, players);
    }

    private static void startGame(String wordToGuess, String[] players) {
        char[] enteredLetters = new char[wordToGuess.length()];


        for (int tries = 0; tries <= 6; tries++) {
            System.out.println(players[tries % 2] + " е на ход");
            printWord(wordToGuess, enteredLetters);
            printHangMan(tries);
        }
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

    public static void printWord(String wordToGuess, char[] enteredLetters) {
        System.out.print("Познайте думата: ");
        for (int i = 0; i < wordToGuess.length(); i++) {
            char letter = wordToGuess.charAt(i);
            if (isEnteredLetter(letter, enteredLetters)) {
                if (i == 0 || wordToGuess.charAt(i - 1) == ' ') {
                    System.out.print(Character.toUpperCase(letter));
                } else {
                    System.out.print(letter);
                }
            } else if (letter == ' ') {
                System.out.print(' ');
            } else {
                System.out.print('*');
            }
        }
        System.out.println();
    }

    public static boolean isEnteredLetter(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }

    private static String randomWord() {
        String[] words = {"Благоевград", "Бургас", "Варна", "Велико Търново", "Видин",
                "Враца", "Габрово", "Добрич", "Кърджали", "Кюстендил", "Ловеч", "Монтана",
                "Пазарджик", "Перник", "Плевен", "Пловдив", "Разград", "Русе", "Сливен",
                "Смолян", "София", "Стара Загора", "Търговище", "Шумен", "Ямбол"};

        int randomWordNumber = (int) (Math.random() * words.length);
        return words[randomWordNumber];
    }
}
