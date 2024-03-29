package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AmazingNumber {
    private final long number;
    private final HashMap<AmazingPropertyType, Boolean> propertiesMap;

    public AmazingNumber(long number) {
        this.number = number;
        propertiesMap = new HashMap<>();
        propertiesMap.put(AmazingPropertyType.BUZZ, AmazingNumberUtils.isBuzzNumber(number));
        propertiesMap.put(AmazingPropertyType.DUCK, AmazingNumberUtils.isDuckNumber(number));
        propertiesMap.put(AmazingPropertyType.PALINDROMIC, AmazingNumberUtils.isPalindromicNumber(number));
        propertiesMap.put(AmazingPropertyType.GAPFUL, AmazingNumberUtils.isGapfulNumber(number));
        propertiesMap.put(AmazingPropertyType.SPY, AmazingNumberUtils.isSpyNumber(number));
        propertiesMap.put(AmazingPropertyType.SQUARE, AmazingNumberUtils.isSquareNumber(number));
        propertiesMap.put(AmazingPropertyType.SUNNY, AmazingNumberUtils.isSunnyNumber(number));
        propertiesMap.put(AmazingPropertyType.JUMPING, AmazingNumberUtils.isJumpingNumber(number));
        boolean isHappy = AmazingNumberUtils.isHappyNumber(number);
        propertiesMap.put(AmazingPropertyType.HAPPY, isHappy);
        propertiesMap.put(AmazingPropertyType.SAD, !isHappy);
        boolean isEven = AmazingNumberUtils.isEven(number);
        propertiesMap.put(AmazingPropertyType.EVEN, isEven);
        propertiesMap.put(AmazingPropertyType.ODD, !isEven);
    }

    public long getNumber() {
        return number;
    }

    public boolean isType(AmazingPropertyType type) {
        return propertiesMap.get(type);
    }

    public String getFullInfo() {
        var mappingList = Arrays.stream(AmazingPropertyType.values())
                .map(t -> String.format("%s: %b", t.name().toLowerCase(), propertiesMap.get(t)))
                .toList();
        return String.format("Properties of %,d\n%s", number, String.join("\n", mappingList));
    }

    public String getPropertiesInfo() {
        var properties = Arrays.stream(AmazingPropertyType.values())
                .filter(propertiesMap::get)
                .map(t -> t.name().toLowerCase())
                .toList();
        return String.format("%,d is %s", number, String.join(", ", properties));
    }

    public static AmazingNumber getNext(long inputNumber, ArrayList<AmazingPropertyType> ofTypes, ArrayList<AmazingPropertyType> ignoreTypes) {
        boolean found;
        do {
            found = true;
            for (AmazingPropertyType type : ofTypes) {
                if (!AmazingNumberUtils.isType(inputNumber, type)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                for (AmazingPropertyType type : ignoreTypes) {
                    if (AmazingNumberUtils.isType(inputNumber, type)) {
                        found = false;
                        break;
                    }
                }
            }
            if (!found) {
                inputNumber += 1;
            }
        } while (!found);
        return new AmazingNumber(inputNumber);
    }
}
