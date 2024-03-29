package numbers;

public enum AmazingPropertyType {
    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD;

    public AmazingPropertyType conflictType() {
        return switch (this) {
            case BUZZ, PALINDROMIC, GAPFUL, JUMPING -> null;
            case DUCK -> SPY;
            case SPY -> DUCK;
            case SQUARE -> SUNNY;
            case SUNNY -> SQUARE;
            case HAPPY -> SAD;
            case SAD -> HAPPY;
            case EVEN -> ODD;
            case ODD -> EVEN;
        };
    }
}
