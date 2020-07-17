package se.lexicon.romeobot.Data;

//import java.util.Currency;

// import com.sun.org.apache.xpath.internal.operations.String;

public class TextManaging {

    public String[] resultInput(String input) {
        input = input.trim().toUpperCase().replaceAll("\\s", "");
        char[] inputArray = input.toCharArray();
        char output = '¤';
        String[] outputValues = new String[2];

        switch (inputArray[0]) {
            case '$':
                output = '$';
                //  outputValues[0] = new String(String.valueOf(output));
                //   System.out.println(outputValues[0]);
                // outputValues[1] = input.replaceAll(outputValues[0], "");
                break;

            case '£':

                output = '£';
                //   outputValues[0] = new String(String.valueOf(output));
                //   outputValues[1] = input.replaceAll(outputValues[0], "");
                break;

            case '€':
                output = '€';
                //    outputValues[0] = new String(String.valueOf(output));
                //    outputValues[1] = input.replaceAll(outputValues[0], "");
                break;

            case '¥':
                output = '¥';
                //   outputValues[0] = new String(String.valueOf(output));
                //   outputValues[1] = input.replaceAll(outputValues[0], "");
                break;

            default:
                //    outputValues[0] = new String(String.valueOf(output));
                //    outputValues[1] = input.replaceAll(outputValues[0], "");
                break;
        }
        outputValues[0] = new String(String.valueOf(output));
        //System.out.println(outputValues.toString());
        outputValues[1] = input.replace(outputValues[0], "").replaceAll("[^0-9]", "");

        return outputValues;
    }
}

