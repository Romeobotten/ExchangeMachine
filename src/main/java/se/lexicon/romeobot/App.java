package se.lexicon.romeobot;

import se.lexicon.romeobot.Data.CurrencyAndBalance;
import se.lexicon.romeobot.Data.TextManaging;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String input;
        String[] insertBill; // = new String[2];
        String[] weekDay = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
        int inputAmount;
        char symbol;
        int days = 0;

        CurrencyAndBalance calculate = new CurrencyAndBalance();
        TextManaging readingText = new TextManaging();

        calculate.setNewCurrencyRate();
        System.out.println();
        System.out.print("Welcome to my Exchange Machine!  ");
        System.out.print(readingText.printCopyright());
        System.out.println("  Today it is " + weekDay[days]);
        System.out.print(weekDay[days] + "s currency exchange rate:  ");
        System.out.println(calculate.getCurrencyRate());
        calculate.printAvailableBills();
        System.out.println("Bills in your wallet: " + calculate.printWallet());
        System.out.println("Total value of your wallet: " + readingText.print2decimals(calculate.getWalletValue()) + " kr.");
        System.out.print("Enter a symbol and a number to insert bills. Enter q to quit. ");
        System.out.println("Enter a - before to redraw a bill.");
        System.out.println("Enter w to see what's in your wallet. Enter q to quit. Enter d to get to the next day. ");

        while (days < 5) {

            System.out.print("Insert amount: ");
            input = reader.nextLine();  // Reading a number, or whatever

            if(input.equals("")) {
                continue;
            }
            switch (input.charAt(0)) {

                case 'q':
                    days = 5;
                    break; // Program ends if input is q

                case 'w':
                    System.out.println("Bills in your wallet: " + calculate.printWallet());
                    System.out.println("Total value of your wallet: " + readingText.print2decimals(calculate.getWalletValue()) + " kr.");
                    break;

                case 'b':
                    calculate.printAvailableBills();
                    break;

                case 'x':
                    System.out.print(weekDay[days] + "s currency exchange rate:  ");
                    System.out.println(calculate.getCurrencyRate());
                    break;

                case 'h':
                    System.out.println("Bills in your wallet: " + calculate.printWallet());
                    System.out.println("Total value of your wallet: " + readingText.print2decimals(calculate.getWalletValue()) + " kr.");
                    System.out.println("I will keep the change: kr " + readingText.print2decimals(calculate.returnAllChange()));
                    calculate.setBalance(-calculate.getBalance());
                    days++;

                    if (days > 4) {
                        break;
                    }
                    calculate.setNewCurrencyRate();
                    System.out.println();
                    System.out.print("Welcome to my Exchange Machine!  ");
                    System.out.print(readingText.printCopyright());
                    System.out.println("  Today it is " + weekDay[days]);
                    System.out.print(weekDay[days] + "s currency exchange rate:  ");
                    System.out.println(calculate.getCurrencyRate());
                    System.out.println("Bills in your wallet: " + calculate.printWallet());
                    System.out.println("Total value of your wallet: " + readingText.print2decimals(calculate.getWalletValue()) + " kr.");
                    System.out.print("Enter a symbol and a number to insert bills. Enter q to quit. ");
                    System.out.println("Enter a - before to redraw a bill.");
                    System.out.println("Enter w to see what's in your wallet. Enter q to quit. Enter d to get to the next day. ");
                    break;

                default:
                    if(input.length() < 2) {
                        break;
                    }
                    insertBill = readingText.resultInput(input);
                    symbol = insertBill[0].charAt(0); // is this necessary? Yes looks better
                    inputAmount = calculate.checkBill(symbol, insertBill[1]);
                    if (symbol == '¤') {
                        insertBill[0] = "kr";
                    }

                    if (inputAmount > 0) {
                        if (insertBill[2].equals("-")) {
                            if (calculate.getBalance() > calculate.exchange(symbol, inputAmount)) {
                                calculate.setBalance(calculate.exchange(insertBill[0].charAt(0), inputAmount * -1));
                                calculate.addToWallet(symbol, inputAmount);
                                System.out.println("You got a " + inputAmount + " " + insertBill[0] + " bill in your wallet.");
                            } else {
                                System.out.println("You don't have enough money to redraw that!");
                            }
                        } else {
                            if (calculate.removeFromWallet(symbol, inputAmount)) {
                                System.out.println("It was in your wallet:");
                                calculate.setBalance(calculate.exchange(symbol, inputAmount));
                                System.out.println("You put in a " + inputAmount + " " + insertBill[0] + " bill.");
                            } else {
                                System.out.println("That bill was not in your wallet:");
                            }
                        }
                    } else {
                        System.out.println("This was not a valid bill!");
                    }
            }
            System.out.println("Change left: kr " + readingText.print2decimals(calculate.getBalance()));
        }
// Finally the end

        System.out.println("Bills in your wallet: " + calculate.printWallet());
        System.out.println("Swedish bills value: " + calculate.getWalletValue('¤'));
        System.out.println("Foreign bills value: " + (calculate.getWalletValue() - calculate.getWalletValue('¤')));
        System.out.println("Total value of your wallet: " + readingText.print2decimals(calculate.getWalletValue()) + " kr.");
        System.out.println("High score? ");
        System.out.println("Thank you and goodbye!");
        reader.close();
    }
}
