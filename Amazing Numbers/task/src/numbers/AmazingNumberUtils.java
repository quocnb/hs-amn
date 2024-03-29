package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmazingNumberUtils {
    public static List<String> getSupportTypes() {
        return Arrays.stream(AmazingPropertyType.values())
                .map(s -> s.name().toUpperCase())
                .toList();
    }

    public static boolean isType(long inputNumber, AmazingPropertyType type) {
        return switch (type) {
            case BUZZ -> AmazingNumberUtils.isBuzzNumber(inputNumber);
            case DUCK -> AmazingNumberUtils.isDuckNumber(inputNumber);
            case PALINDROMIC -> AmazingNumberUtils.isPalindromicNumber(inputNumber);
            case GAPFUL -> AmazingNumberUtils.isGapfulNumber(inputNumber);
            case SPY -> AmazingNumberUtils.isSpyNumber(inputNumber);
            case SQUARE -> AmazingNumberUtils.isSquareNumber(inputNumber);
            case SUNNY -> AmazingNumberUtils.isSunnyNumber(inputNumber);
            case JUMPING -> AmazingNumberUtils.isJumpingNumber(inputNumber);
            case HAPPY -> AmazingNumberUtils.isHappyNumber(inputNumber);
            case SAD -> !AmazingNumberUtils.isHappyNumber(inputNumber);
            case EVEN -> AmazingNumberUtils.isEven(inputNumber);
            case ODD -> !AmazingNumberUtils.isEven(inputNumber);
        };
    }

    public static String getPropertiesConflictMessage(ArrayList<AmazingPropertyType> types, ArrayList<AmazingPropertyType> ignoreTypes) {
        ArrayList<String> conflictedTypeNames = new ArrayList<>();
        for (AmazingPropertyType type : types) {
            AmazingPropertyType conflictType = type.conflictType();
            if (types.contains(conflictType)) {
                conflictedTypeNames.add(type.name().toUpperCase());
                conflictedTypeNames.add(conflictType.name().toUpperCase());
                break;
            }
            if (ignoreTypes.contains(type)) {
                conflictedTypeNames.add(type.name().toUpperCase());
                conflictedTypeNames.add("-" + type.name().toUpperCase());
                break;
            }
        }
        for (AmazingPropertyType type: ignoreTypes) {
            AmazingPropertyType conflictType = type.conflictType();
            if (ignoreTypes.contains(conflictType)) {
                conflictedTypeNames.add("-" + type.name().toUpperCase());
                conflictedTypeNames.add("-" + conflictType.name().toUpperCase());
                break;
            }
        }
        if (!conflictedTypeNames.isEmpty()) {
            return String.format("The request contains mutually exclusive properties: [%s]\n",
                    String.join(", ", conflictedTypeNames)) +
                    "There are no numbers with these properties.";
        }
        return "";
    }

    /**
     * Check the number is even or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isEven(long inputNumber) {
        return inputNumber % 2 == 0;
    }

    /**
     * Check the number is a duck number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isDuckNumber(long inputNumber) {
        String inputText = String.valueOf(inputNumber);
        return inputText.contains("0");
    }

    /**
     * Check the number is a duck number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isBuzzNumber(long inputNumber) {
        return inputNumber % 7 == 0 || inputNumber % 10 == 7;
    }

    /**
     * Check the number is a Palindromic number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isPalindromicNumber(long inputNumber) {
        String inputText = String.valueOf(inputNumber);
        for (int i = 0; i < inputText.length()/2; i++) {
            if (inputText.charAt(i) != inputText.charAt(inputText.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check the number is a Gapful number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isGapfulNumber(long inputNumber) {
        if (inputNumber < 100) {
            return false;
        }
        String inputText = String.valueOf(inputNumber);
        String dividerText = inputText.charAt(0) + String.valueOf(inputText.charAt(inputText.length() - 1));
        int divider = Integer.parseInt(dividerText);
        return inputNumber % divider == 0;
    }

    /**
     * Check the number is a spy number or not
     * A number is said to be Spy if the sum of all digits is equal to the product of all digits.
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isSpyNumber(long inputNumber) {
        long sum = 0, product = 1;
        long digit;
        while (inputNumber > 0) {
            digit = inputNumber % 10;
            sum += digit;
            product *= digit;
            inputNumber = inputNumber / 10;
        }
        return sum == product;
    }

    /**
     * Check the number is a square number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isSquareNumber(long inputNumber) {
        double sqrt = Math.sqrt(inputNumber);
        return sqrt == (long)sqrt;
    }

    /**
     * Check the number is a sunny number or not
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isSunnyNumber(long inputNumber) {
        return isSquareNumber(inputNumber + 1);
    }

    /**
     * Check the number is a jumping number or not
     * A number is a Jumping number if the adjacent digits inside the number differ by 1
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isJumpingNumber(long inputNumber) {
        if (inputNumber < 10) {
            return true;
        }
        long lastNumber = inputNumber % 10;
        long checkingNumber;
        while (inputNumber > 10) {
            inputNumber = inputNumber / 10;
            checkingNumber = inputNumber % 10;
            if (Math.abs(lastNumber - checkingNumber) != 1) {
                return false;
            }
            lastNumber = checkingNumber;
        }
        return true;
    }

    /**
     * Check the number is a jumping number or not
     * A number is a Jumping number if the adjacent digits inside the number differ by 1
     * @param inputNumber a natural number (integer, > 0)
     * @return true/false
     */
    public static boolean isHappyNumber(long inputNumber) {
        long digitSquareSum = 0;
        long digit;
        while (inputNumber > 0) {
            digit = inputNumber % 10;
            digitSquareSum += digit * digit;
            inputNumber = inputNumber / 10;
            if (inputNumber == 0 && digitSquareSum >= 10) {
                inputNumber = digitSquareSum;
                digitSquareSum = 0;
            }
        }
        return digitSquareSum == 1;
    }
}