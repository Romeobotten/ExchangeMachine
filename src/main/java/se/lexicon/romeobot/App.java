package se.lexicon.romeobot;

import se.lexicon.romeobot.Data.CurrencyAndBalance;
import se.lexicon.romeobot.Data.TextManaging;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String input;
        String[] insertBill = new String[2];
        double inputAmount;
        CurrencyAndBalance MONDAY = new CurrencyAndBalance();
        TextManaging readingText = new TextManaging();

        while (true) {
            System.out.println("Welcome to my Exchange Machine!");
            System.out.print("Enter a symbol and a number to insert bills. Enter q to quit.");
            System.out.println("These bills are valid.");
            // for (JunkFoodMachine.DENOMINATIONS kr : JunkFoodMachine.DENOMINATIONS.values())
             //   System.out.print(kr + ", ");

            System.out.println("\nInsert amount: ");
            input = reader.nextLine();  // Reading a number

            if (input.equalsIgnoreCase("q")) { // Want to quit?

                System.out.println("Thank you and goodbye!");
                break; // Program ends if input is q
            }
            insertBill = readingText.resultInput(input);
            inputAmount = Double.parseDouble(insertBill[1]);
            MONDAY.setBalance(MONDAY.exchange(insertBill[0].charAt(0), inputAmount) );


            System.out.println("You have "+ MONDAY.getBalance() + " SEK.");
        }
    }
}

