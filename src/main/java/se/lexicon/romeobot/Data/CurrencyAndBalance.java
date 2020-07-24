package se.lexicon.romeobot.Data;

import java.util.Arrays;

public class CurrencyAndBalance {

    TextManaging readText = new TextManaging();

    String[] wallet = new String[]{"£20","$50","€10","¤100"};

//            {"$50", "$20", "$20", "$10", "$5", "£50", "£20", "£10", "£5",
//            "€50", "€20", "€10", "€10", "€5", "¥5000", "¥5000", "¥2000", "¤500", "¤200", "¤200", "¤50", "¤20"};

    private double USD = 9.1;
    private double GBP = 11.5;
    private double EUR = 9.7;
    private double JPY = 0.08;
    private double SEK = 1;

    int[] usdBills = new int[]{1, 2, 5, 10, 20, 50, 100};
    int[] gbpBills = new int[]{5, 10, 20, 50};
    int[] eurBills = new int[]{5, 10, 20, 50, 100, 200, 500};
    int[] jpyBills = new int[]{1000, 2000, 5000, 10000};
    int[] sekBills = new int[]{20, 50, 100, 200, 500, 1000};

    private double balance;

    public void setNewCurrencyRate() {
        USD = 9.1 + (Math.random() * 2.8);
        GBP = 11.5 + (Math.random() * 2.4);
        EUR = 9.7 + (Math.random() * 1.8);
        JPY = 0.08 + (Math.random() * 0.03);
    }

    public double exchange(char symbol, double amount) { // input foreign currency, output sek

        switch (symbol) {
            case '$':
                amount = amount * USD;
                break;

            case '£':
                amount = amount * GBP;
                break;

            case '€':
                amount = amount * EUR;
                break;

            case '¥':
                amount = amount * JPY;
                break;

            default:
                amount = amount * SEK;
        }
        return amount;
    }

    public double exchange(double amount, char symbol) { // input SEK, output foreign currency

        switch (symbol) {
            case '$':
                amount = amount / USD;
                break;

            case '£':
                amount = amount / GBP;
                break;

            case '€':
                amount = amount / EUR;
                break;

            case '¥':
                amount = amount / JPY;
                break;
            default:
                amount = amount / SEK;
        }
        return amount;
    }

    public int checkBill(char symbol, String textValue) {

        int billValue = Integer.parseInt(textValue);
        switch (symbol) {
            case '$':
                for (int usdBill : usdBills) {
                    if (billValue == usdBill) {
                        return billValue;
                    }
                }
                return 0;

            case '£':
                for (int gbpBill : gbpBills) {
                    if (billValue == gbpBill) {
                        return billValue;
                    }
                }
                return 0;

            case '€':
                for (int eurBill : eurBills) {
                    if (billValue == eurBill) {
                        return billValue;
                    }
                }
                return 0;

            case '¥':
                for (int jpyBill : jpyBills) {
                    if (billValue == jpyBill) {
                        return billValue;
                    }
                }
                return 0;

            case '¤':
                for (int sekBill : sekBills) {
                    if (billValue == sekBill) {
                        return billValue;
                    }
                }
                return 0;

            default:
                return 0;
        }
    }

    public double returnAllChange() {
        //int nrOfBills;

        if (getBalance() < sekBills[0]) {
            System.out.println("No change:");
        } else {
            for (int i = sekBills.length - 1; i >= 0; i--) { // Skriv om denna helt
                // nrOfBills = (int) getBalance() / sekBills[i];

                while (getBalance() >= sekBills[i]) {

                    if (getBalance() < sekBills[i] + 20 && getBalance() >= sekBills[i] + 10 && i > 0) {
                        System.out.println("Helping with right change ---");// Test
                        break;
                    }
                    if (getBalance() < sekBills[i] + 40 && getBalance() >= sekBills[i] + 30 && i > 0) {
                        System.out.println("Helping with right change ---");// Test
                        break;
                    }
                    setBalance(-sekBills[i]);
                    addToWallet('¤', sekBills[i]);
                    System.out.println("You got a " + sekBills[i] + " kr bill in your wallet.");
                }
                System.out.println("Change left: kr " + readText.print2decimals(getBalance())); // Just for test
            }
        }
        return getBalance();
    }

    public String getCurrencyRate () { // Prints the exchange rate for all currency
        String s = "USD = " + (int) (USD * 10000) / 10000d + " - GBP = " + (int) (GBP * 10000) / 10000d +
                " - EUR = " + (int) (EUR * 10000) / 10000d + " - JPY = " + (int) (JPY * 10000) / 10000d;
        return s;
    }

    public String printWallet() {
        return Arrays.toString(wallet);
    }
    public double getWalletValue() { // sum of all bills in wallet in sek
        double total = 0;
        double amount;
        char symbol;
        for (int i = 0; i < wallet.length; i++) {
            symbol = wallet[i].charAt(0);
            amount = Double.parseDouble(wallet[i].replace(String.valueOf(symbol),""));
            total += exchange(symbol, amount);
            // System.out.println(total);
        }
        return total;
    }
    public double getWalletValue(char symbol) { // sum of all bills from a currency in wallet, in SEK
        double total = 0;
        double amount;
//        char symbol;
        for (int i = 0; i < wallet.length; i++) {

            if (symbol == wallet[i].charAt(0)) {
                amount = Double.parseDouble(wallet[i].replace(String.valueOf(symbol), ""));
                total += exchange(symbol, amount);
                System.out.println(total);
            }
        }
        return total;
    }

    public void addToWallet(char symbol, int amount) {
        int size = wallet.length;
        String bill = symbol + String.valueOf(amount);
        wallet = Arrays.copyOf(wallet, size + 1);
        wallet[size] = bill;
    }

    public boolean removeFromWallet(char symbol, int amount) {
        int size = wallet.length;
        String bill = symbol + String.valueOf(amount);
        for (int i = 0; i < size; i++) {

            if (wallet[i].equals(bill)) { // Är det något fel här?
                for (int j = i; j < size - 1; j++) {
                    wallet[j] = wallet[j + 1];
                }
                wallet = Arrays.copyOf(wallet, size - 1);
                return true;       // Remove bill from wallet and change array.wallet
            }
        }
        return false;
    }

    public void printAvailableBills() {
        System.out.println("USD: " + Arrays.toString(usdBills));
        System.out.println("GBP: " + Arrays.toString(gbpBills));
        System.out.println("EUR: " + Arrays.toString(eurBills));
        System.out.println("JPY: " + Arrays.toString(jpyBills));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance += amount;
    }
}
