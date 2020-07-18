package se.lexicon.romeobot.Data;

import java.util.Arrays;

public class CurrencyAndBalance {

    String[] wallet = new String[] {"$50","$20","$20","$10","$5","£50","£20","£10","£5",
            "€50","€20","€10","€10","€5","¥5000","¥5000","¥2000"};

    private final static double USD = 9.2 + (Math.random() * 2.8);
    private final static double GBP = 11.6 + (Math.random() * 2.1);
    private final static double EUR = 9.8 + (Math.random() * 1.8);
    private final static double JPY = 0.08 + (Math.random() * 0.03);
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
        int nrOfBills;

        if (getBalance() < 20) {
            System.out.println("No change:");
        }
        for (int i = sekBills.length - 1; i >= 0; i--) {
            nrOfBills = (int)getBalance() / sekBills[i];
            setBalance(-nrOfBills * sekBills[i]); // kolla om den avrundar?

            // System.out.println("Change left: kr " + getBalance());

            if (nrOfBills > 0) {
                System.out.println("Change returned: " + nrOfBills + " x kr." + sekBills[i]);
                //   System.out.println("Change left: kr " + (getBalance()) );
            }
        }
        return getBalance();
    }
    /**
    public double truncateAmount(double amount) {
        return (Math.floor(amount * 100) / 100d);
    }
     */

    public String getTodaysCurrency() {
        String s = "USD = " + (int)(USD * 10000) / 10000d + " - GBP = " + (int)(GBP * 10000) / 10000d +
                " - EUR = " + (int)(EUR * 10000) / 10000d + " - JPY = " + (int)(JPY * 10000) / 10000d;
        return s;
    }

    public String printWallet(){
        return Arrays.toString(wallet);
    }

    public boolean removeFromWallet(char symbol, int amount) {
        for (int i = 0; i < wallet.length; i++) {
            String bill = symbol + String.valueOf(amount);

            if (wallet[i].equals(bill)) {
                return true;
                // Remove bill from wallet and change array.wallet
            }
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance += amount;
    }
}
