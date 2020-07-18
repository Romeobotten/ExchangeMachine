package se.lexicon.romeobot;

import se.lexicon.romeobot.Data.CurrencyAndBalance;
import se.lexicon.romeobot.Data.TextManaging;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String input;
        String[] insertBill; // = new String[2];
        int inputAmount;
        char symbol;

        CurrencyAndBalance MONDAY = new CurrencyAndBalance();
        TextManaging readingText = new TextManaging();
        System.out.print("Welcome to my Exchange Machine!  ");
        System.out.println(readingText.printCopyright());
        System.out.println("Today's currency exchange rate:");
        System.out.println(MONDAY.getTodaysCurrency());
        System.out.println("Bills in your wallet: " + MONDAY.printWallet());
        System.out.println("Enter a symbol and a number to insert bills. Enter q to quit.");

        while (true) {

            System.out.print("Insert amount: ");
            input = reader.nextLine();  // Reading a number

            if (input.equalsIgnoreCase("q")) { // Want to quit?

                System.out.println("Thank you and goodbye!");
                break; // Program ends if input is q
            }
            insertBill = readingText.resultInput(input);
            // inputAmount = Double.parseDouble(insertBill[1]);
            symbol = insertBill[0].charAt(0); // is this necessary?
            inputAmount = MONDAY.checkBill(symbol, insertBill[1]);
            if (symbol == '¤') {
                insertBill[0] = "Kr ";
            }

            if (inputAmount > 0) {
                if (insertBill[2].equals("-")) {
                    if (MONDAY.getBalance() > MONDAY.exchange(symbol, inputAmount)) {
                        MONDAY.setBalance(MONDAY.exchange(insertBill[0].charAt(0), inputAmount * -1));
                        System.out.println("You got a " + insertBill[0] + inputAmount + " bill.");
                    } else {
                        System.out.println("You don't have enough money!");
                    }
                } else {
                    MONDAY.setBalance(MONDAY.exchange(insertBill[0].charAt(0), inputAmount));
                    System.out.println("You put in a " + insertBill[0] + inputAmount + " bill.");
                    if (MONDAY.removeFromWallet(symbol,inputAmount)){
                        System.out.println("It was in your wallet:");
                    }
                }

            } else {
                System.out.println("This was not a valid bill!");
            }
            // System.out.println("You have " + MONDAY.getBalance() + " SEK.");
            System.out.println("Change left: kr " + readingText.print2decimals(MONDAY.getBalance()));
        }
        System.out.println("I will keep the change: Kr " + MONDAY.returnAllChange());
    }
}
