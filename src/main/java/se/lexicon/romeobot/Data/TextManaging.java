package se.lexicon.romeobot.Data;

public class TextManaging {

    char[] copyright = new char[] {'©',' ','Ο','Ǝ','Ϻ','Ο','Я',};

    public String[] resultInput(String input) {
        input = input.trim().toUpperCase().replaceAll("\\s", "");
        char[] inputArray = input.toCharArray();
        char output = '¤';
        String[] outputValues = new String[3];

        if (inputArray[0] == '-') {
            outputValues[2] = "-";
            inputArray[0] = inputArray[1];
        } else {
            outputValues[2] = "+";
        }

        switch (inputArray[0]) {
            case '$':
                output = '$';
                break;

            case '£':
                output = '£';
                break;

            case '€':
                output = '€';
                break;

            case '¥':
                output = '¥';
                break;

            default:

                break;
        }
        outputValues[0] = (String.valueOf(output));
        //System.out.println(outputValues.toString());
        outputValues[1] = input.replaceAll("[^0-9]", "");
        return outputValues;
    }

    public String print2decimals(double amount) {
        int twoDecimals = (int)(amount * 100.0) % 100;
        // int noDecimals = Math.floor(amount);
        String print2decimals = String.valueOf((int)amount);
        if (twoDecimals < 10) {
            print2decimals = print2decimals + ".0" + twoDecimals;
        } else {
            print2decimals = print2decimals +"." + twoDecimals;
        }
        return print2decimals;
    }

    public String printCopyright() {
        return String.valueOf(copyright);
    }
}
