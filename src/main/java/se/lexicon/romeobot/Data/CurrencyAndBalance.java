package se.lexicon.romeobot.Data;

public class CurrencyAndBalance {

    private final static double USD = 10.1;
    private final static double GBP = 12.2;
    private final static double EUR = 11.1;
    private final static double JPY = 0.09;
    private final static double SEK = 1;

    int[] usdBills = new int[]{1, 2, 5, 10, 20, 50, 100};
    int[] gbpBills = new int[]{5, 10, 20, 50};
    int[] eurBills = new int[]{5, 10, 20, 50, 100, 200};
    int[] jpyBills = new int[]{1000, 2000, 5000, 10000};
    int[] sekBills = new int[]{20, 50, 100, 200, 500};

    private double balance;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance += amount;
    }
}
