package numbers;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        write your code here
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
        Scanner scanner = new Scanner(System.in);
        long startNumber = -1;
        int consecutiveCount = -1;
        ArrayList<AmazingPropertyType> propertyModes;
        ArrayList<AmazingPropertyType> ignoreTypeList;
        while (true) {
            System.out.println();
            System.out.print("Enter a request: ");
            String[] inputs = scanner.nextLine().split(" ");
            System.out.println();
            try {
                startNumber = Long.parseLong(inputs[0]);
                if (startNumber == 0) {
                    System.out.print("Goodbye!");
                    break;
                }
                if (startNumber < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (inputs.length > 1) {
                try {
                    consecutiveCount = Integer.parseInt(inputs[1]);
                    if (consecutiveCount <= 0) {
                        System.out.println("The second parameter should be a natural number.\n");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("The second parameter should be a natural number.\n");
                    continue;
                }
            }
            propertyModes = new ArrayList<>();
            ignoreTypeList = new ArrayList<>();
            if (inputs.length > 2) {
                String propertyMode = "";
                try {
                    for (int i = 2; i < inputs.length; i++) {
                        propertyMode = inputs[i].toUpperCase();
                        if (propertyMode.startsWith("-")) {
                            ignoreTypeList.add(AmazingPropertyType.valueOf(propertyMode.substring(1)));
                        } else {
                            propertyModes.add(AmazingPropertyType.valueOf(propertyMode));
                        }
                    }
                    String errorMessage = AmazingNumberUtils.getPropertiesConflictMessage(propertyModes, ignoreTypeList);
                    if (!errorMessage.isEmpty()) {
                        System.out.println(errorMessage);
                        continue;
                    }
                } catch (Exception e) {
                    String errorMessageFormat = "The property [%s] is wrong.\n" +
                            "Available properties: [%s]";
                    String errorMessage = String.format(errorMessageFormat,
                            propertyMode,
                            String.join(", ", AmazingNumberUtils.getSupportTypes()));
                    System.out.println(errorMessage);
                    continue;
                }
            }
            AmazingNumber number;
            if (consecutiveCount < 0) {
                number = new AmazingNumber(startNumber);
                System.out.println(number.getFullInfo());
                continue;
            }
            do {
                if (propertyModes.isEmpty() && ignoreTypeList.isEmpty()) {
                    number = new AmazingNumber(startNumber);
                    startNumber = startNumber + 1;
                } else {
                    number = AmazingNumber.getNext(startNumber, propertyModes, ignoreTypeList);
                    startNumber = number.getNumber() + 1;
                }
                System.out.println(number.getPropertiesInfo());
                consecutiveCount -= 1;
            } while (consecutiveCount > 0);
        }
    }
}
