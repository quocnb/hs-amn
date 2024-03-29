class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here
        StringBuilder builder = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            builder.append(c);
            builder.append(" ");
        }
        // Delete last white space
        builder.deleteCharAt(builder.length() - 1);
        return builder;
    }
}