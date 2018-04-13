import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/*
 * CIS3360 - Spring 2018
 * Security in Computing - Assignment 2
 * Submitted by: <Christopher Narciso> <Omar Padilla>
 * Description: Program that calculates a checksum based on bytes.
 * Every 1 byte for 8 bit, every two for 16 bit, and every 4 for 32 bit.
 */

public class checksum {

    public static void main(String[] args) {
        int checkSumSize, characterCount, checkSumResult;
        String input = null;
        byte[] fileBytes;

        if (args.length < 2 || !Character.isDigit(args[1].charAt(0)) || !validBitSize(Integer.parseInt(args[1]))) {
            if (!Character.isDigit(args[1].charAt(0)) || !validBitSize(Integer.parseInt(args[1])))
                System.err.print("\nValid checksum sizes are 8, 16, or 32\n");
            else
                System.err.println("\nPlease provide the proper parameters.\n" +
                        "First Parameter is the input file name, second is the size of the checksum.\n");
            System.exit(1);
        }

        try {
            input = readFileAsString(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (input != null){
            switch (Integer.parseInt(args[1])) {
                case 8:
                    checkSumSize = 8;
                    fileBytes = getAdjustedByteArray(input, checkSumSize);
                    characterCount = fileBytes.length;
                    checkSumResult = checksum8(fileBytes);
                    System.out.println("\n" + formattedStringOutput(getAdjustedString(input, checkSumSize)) +
                            "\n" + checkSumSize + " bit checksum is " + Integer.toHexString(checkSumResult) + " for all " + characterCount + " chars\n");
                    break;
                case 16:
                    checkSumSize = 16;
                    fileBytes = getAdjustedByteArray(input, checkSumSize);
                    characterCount = fileBytes.length;
                    checkSumResult = checksum16(fileBytes);
                    System.out.println("\n" + formattedStringOutput(getAdjustedString(input, checkSumSize)) +
                            "\n" + checkSumSize + " bit checksum is " + Integer.toHexString(checkSumResult) + " for all " + characterCount + " chars\n");
                    break;
                case 32:
                    checkSumSize = 32;
                    fileBytes = getAdjustedByteArray(input, checkSumSize);
                    characterCount = fileBytes.length;
                    checkSumResult = checksum32(fileBytes);
                    System.out.println("\n" + formattedStringOutput(getAdjustedString(input, checkSumSize)) +
                            "\n" + checkSumSize + " bit checksum is " + Integer.toHexString(checkSumResult) + " for all " + characterCount + " chars\n");
                    break;
                default:
                    System.err.print("Valid checksum sizes are 8, 16, or 32\n");
                    System.exit(1);
                    break;
            }
        }
    }

    /**
     * Checksum 8-Bit
     *
     * @param data - the byte[] converted from our inputFile
     * @return calculated checksum 8-bit by summing each data byte and masking two hex characters.
     */
    private static int checksum8(byte[] data) {
        int check = 0;

        for (byte b : data)
            check += b;

        return check & 0xFF;
    }

    /**
     * Checksum 16-bit
     *
     * @param data - the byte[] converted from our inputFile
     * @return calculated checksum 16-bit by summing each two combined data bytes and masking four hex characters.
     */
    private static int checksum16(byte[] data) {
        int check = 0;

        for (int i = 0; i <= data.length - 2; i += 2) {
            check += ((data[i] << 8) | (data[i + 1] & 0xFF));
        }

        return check & 0xFFFF;
    }

    /**
     * Checksum 32-bit
     *
     * @param data - the byte[] converted from our inputFile
     * @return calculated checksum 32-bit by summing each four combined data bytes and masking eight hex characters.
     */
    private static int checksum32(byte[] data) {
        int check = 0;

        for (int i = 0; i < data.length; i += 4) {
            check += ((data[i] << 24) | (data[i + 1] << 16) | (data[i + 2] << 8) | (data[i + 3])) & 0xffffffffL;
        }

        return check;
    }

    /**
     * @param fileName - file to search
     * @return reads everything from the file and returns data in String format.
     * @throws Exception Citation: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     */
    private static String readFileAsString(String fileName) throws Exception {
        String data;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    /**
     * @param output - message to format and output
     * @return formatted message output by adding a new line every 80 characters.
     * Citation: https://stackoverflow.com/questions/10530102/java-parse-string-and-add-line-break-every-100-characters
     */
    private static String formattedStringOutput(String output) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < output.length(); i++) {
            if (i > 0 && i % 80 == 0)
                res.append("\n");
            res.append(output.charAt(i));
        }
        return res.toString();
    }

    /**
     * @param in  - input text to grab characters from to convert to byte.
     * @param bit - bit size we are using. bit > 8 ? sum every 2 chars : sum each chars
     * @return byte[] with appropriate padding if applicable. Padding with X (88 ASCII)
     */
    private static byte[] getAdjustedByteArray(String in, int bit) {
        int originalSize = in.getBytes().length, newSize;

        newSize = originalSize + getPadding(originalSize, bit);
        byte[] temp = new byte[newSize];

        for (int i = 0; i < originalSize; i++) {
            temp[i] = (byte) in.charAt(i);
        }

        if (getPadding(originalSize, bit) > 0) {
            for (int j = originalSize; j < newSize; j++) {
                temp[j] = 88;
            }
        }
        return temp;
    }

    /**
     * @param in  - input string to be adjusted
     * @param bit - bit size parameter to adjust text with padding character 'X'
     * @return formatted output
     */
    private static String getAdjustedString(String in, int bit) {
        int originalSize = in.getBytes().length, newSize;
        StringBuilder builder = new StringBuilder();
        newSize = originalSize + getPadding(originalSize, bit);

        for (int i = 0; i < originalSize; i++) {
            builder.append(in.charAt(i));
        }

        if (getPadding(originalSize, bit) > 0) {
            for (int j = originalSize; j < newSize; j++) {
                builder.append("X");
            }
        }

        return builder.toString();
    }

    /**
     * @param bit - bit size parameter to check against the specified bit sizes that are compatible
     * @return boolean result if any match preset sizes
     */
    private static boolean validBitSize(int bit) {
        int[] validBits = {8, 16, 32};
        return Arrays.stream(validBits).anyMatch(i -> i == bit);
    }


    /**
     * @param lengthOriginal - length of the input file non-padded
     * @param bit            - bit size == 32 then we mod 4 else mod 2. (4 for every 4 bytes : 2 for every 2 bytes)
     *                       a - length
     *                       b - modulus
     *                       c - padding result
     * @return amount of padding we need to add to the original length (input length) if bit > 8
     */
    private static int getPadding(int lengthOriginal, int bit) {
        int a = lengthOriginal;
        int b = bit == 32 ? 4 : 2;
        int c = 0;
        while (a % b != 0) {
            a = a + 1;
            c++;
        }
        return bit > 8 ? c : 0;
    }

}