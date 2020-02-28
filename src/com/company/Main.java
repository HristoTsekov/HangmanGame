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


        playGame(players);
    }

    private static void playGame(String[] players) {
        Scanner input = new Scanner(System.in);
        char[] enteredLetters;
        int[] scores = {0, 0};
        boolean continueGame = true;
        String wordToGuess;

        System.out.println("Вие започнахте играта \"Бесеница\".");
        int rounds = 1;

        while (continueGame) {
            System.out.println("Рунд " + rounds++);
            wordToGuess = randomWord();
            enteredLetters = new char[wordToGuess.length()];

            for (int tries = 0; tries <= 5; tries++) {
                printHangMan(tries);
                int player = tries % 2;
                System.out.println(players[player] + " е на ход");
                boolean currentPlayerMove = true;
                do {

                    String word = createWord(wordToGuess.toLowerCase(), enteredLetters);
                    if (!word.contains("*")) {
                        scores[player]++;
                        tries = 6;

                        System.out.println(players[player] + " печели този рунд. Получава 1 точка.");
                        System.out.println("Думата е: " + wordToGuess);
                        printCurrentScore(players, scores);
                        break;
                    }
                    System.out.print("Познайте думата: " + word);
                    char playerLetter = enterLetter();
                    if (!validateLetter(wordToGuess.toLowerCase(), enteredLetters, playerLetter))
                        currentPlayerMove = false;
                } while (currentPlayerMove);

                if (tries == 5) {
                    printHangMan(++tries);
                    printCurrentScore(players, scores);
                }
            }

            System.out.print("Искате ли да продължите да играете (д/н): ");
            char continueAnswer = input.nextLine().toLowerCase().charAt(0);
            while (continueAnswer != 'н' && continueAnswer != 'д') {
                System.out.print("Моля въведете \"д\"(да) или \"н\"(не): ");
                continueAnswer = input.nextLine().toLowerCase().charAt(0);
            }

            if (continueAnswer == 'н')
                continueGame = false;
        }

        printCurrentScore(players, scores);
        announceWinner(players, scores);

    }

    public static void announceWinner(String[] players, int[] scores) {
        if (scores[0] > scores[1])
            System.out.println("Победителят е: " + players[0]);
        else if (scores[0] == scores[1])
            System.out.println("Резултатът е равен");
        else System.out.println("Победителят е: " + players[1]);
    }

    public static void printCurrentScore(String[] players, int[] scores) {
        System.out.println("Резултата е: " + players[0] + " " + scores[0] + " точки; " + players[1] + " " + scores[1] + " точки.");
    }

    public static boolean validateLetter(String wordToGuess, char[] enteredLetters, char playerLetter) {

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
                System.out.println("Никой не печели точка в този рунд.");
                break;
        }

    }

    public static String createWord(String wordToGuess, char[] enteredLetters) {

        String word = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            char letter = wordToGuess.charAt(i);
            if (isEnteredLetter(letter, enteredLetters)) {
                if (i == 0 || wordToGuess.charAt(i - 1) == ' ') {
                    word += Character.toUpperCase(letter);

                } else {
                    word += letter;

                }
            } else if (letter == ' ') {
                word += " ";

            } else {

                word += "*";
            }
        }
        return word;

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

    public static int findEmptyPosition(char[] enteredLetters) {
        int i = 0;
        while (i < enteredLetters.length && enteredLetters[i] != '\u0000') i++;
        return i;
    }

    public static boolean isCyrillic(char c) {
        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
    }
}
