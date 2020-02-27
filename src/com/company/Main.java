package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] words = {"Благоевград", "Бургас", "Варна", "Велико Търново", "Видин",
                "Враца", "Габрово", "Добрич", "Кърджали", "Кюстендил", "Ловеч", "Монтана",
                "Пазарджик", "Перник", "Плевен", "Пловдив", "Разград", "Русе", "Сливен",
                "Смолян", "София", "Стара Загора", "Търговище", "Шумен", "Ямбол"};

        int randomWordNumber = (int) (Math.random() * words.length);

        int tries = 0;
        String word = words[randomWordNumber];
        char[] enteredLetters = new char[word.length()];
        int rounds = 0;
        while (true) {
            int player = rounds % 2;
            tries = play(words, randomWordNumber, tries, enteredLetters, player);
            if (tries == -1 || tries > 5) {
                break;
            }
            rounds++;
        }
    }

    public static int play(String[] words, int randomWordNumber, int triesCount, char[] enteredLetters, int player) {
        boolean wordIsGuessed = false;

        if (triesCount == 0) {
            System.out.println("Вие започнахте играта \"Бесеница\".");
            System.out.println("    _____ ");
            System.out.println("    |   | ");
            System.out.println("    |     ");
            System.out.println("    |     ");
            System.out.println("    |     ");
            System.out.println("    |     ");
        }

        System.out.println("Играч " + (player + 1));
        do {
            switch (enterLetter(words[randomWordNumber], enteredLetters)) {
                case 0:
                    triesCount++;
                    if (triesCount == 1) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |     ");
                        System.out.println("    |     ");
                        System.out.println("    |     ");


                    } else if (triesCount == 2) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |   | ");
                        System.out.println("    |     ");
                        System.out.println("    |     ");

                    } else if (triesCount == 3) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |  -| ");
                        System.out.println("    |     ");
                        System.out.println("    |         ");
                    } else if (triesCount == 4) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |  -|-");
                        System.out.println("    |     ");
                        System.out.println("    |     ");
                    } else if (triesCount == 5) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |  -|-");
                        System.out.println("    |  /  ");
                        System.out.println("    |     ");
                    } else if (triesCount == 6) {
                        System.out.println("    _____ ");
                        System.out.println("    |   | ");
                        System.out.println("    |   O ");
                        System.out.println("    |  -|-");
                        System.out.println("    |  / \\");
                        System.out.println("    |     ");
                        System.out.println("   GAME OVER");
                        wordIsGuessed = true;
                        break;
                    }

                    return triesCount;
                case 1:
                    break;
                case 2:
                    wordIsGuessed = true;
                    break;
            }

        }
        while (!wordIsGuessed);
        if (triesCount != 6) {
            System.out.print("\nТи победи!");
            triesCount = -1;
        }
        System.out.println("\nДумата е " + "\"" + words[randomWordNumber] + "\"" + ".");

        return triesCount;
    }

    public static boolean isCyrillic(char c) {
        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
    }

    public static int enterLetter(String word, char[] enteredLetters) {
        word = word.toLowerCase();
        int emptyPosition = findEmptyPosition(enteredLetters);
        System.out.print("Познайте думата: ");
        if (!printWord(word, enteredLetters))
            return 2;
        System.out.print("\nВъведете буква: ");
        Scanner input = new Scanner(System.in);
        char userInput = input.nextLine().toLowerCase().charAt(0);
        if (!isCyrillic(userInput)) {
            System.out.println("Въведете буква на кирилица!");
            return 1;
        }
        if (inEnteredLetters(userInput, enteredLetters)) {
            System.out.println("\"" + userInput + "\"" + " вече е в думата");
            return 1;
        } else if (word.contains(String.valueOf(userInput))) {
            enteredLetters[emptyPosition] = userInput;
            return 1;
        } else {
            System.out.println("\"" + userInput + "\"" + " я няма в думата.");
            return 0;
        }
    }

    public static boolean printWord(String word, char[] enteredLetters) {
        boolean isPrinted = false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (inEnteredLetters(letter, enteredLetters)) {
                if (i == 0 || word.charAt(i - 1) == ' ') {
                    System.out.print(Character.toUpperCase(letter));
                } else {
                    System.out.print(letter);
                }
            } else if (letter == ' ') {
                System.out.print(' ');
            } else {
                System.out.print('*');
                isPrinted = true;
            }
        }
        return isPrinted;
    }

    public static boolean inEnteredLetters(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }

    public static int findEmptyPosition(char[] enteredLetters) {
        int i = 0;
        while (i < enteredLetters.length && enteredLetters[i] != '\u0000') i++;
        return i;

    }
}


