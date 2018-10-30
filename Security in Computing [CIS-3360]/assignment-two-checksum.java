import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/*
 * CIS3360 - Spring 2018
 * Security in Computing - Assignment 2
 * Submitted by: <Christopher Narciso> <Omar Padilla>
 * Description: Program that calculates a checksum based on bytes.
 * Every 1 byte for 8 bit, every two for 16 bit, and every 4 for 32 bit.
 */
public class Checksum {

    private static final int[] VALID_BIT_SIZES = {8, 16, 32};

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("\nPlease provide the proper parameters.\n" +
                    "First Parameter is the input file name, second is the size of the checksum.\n");
            System.exit(1);
        }

        Path filePath = Paths.get(args[0]);

        if (!Files.exists(filePath)) {
            System.err.print("\nFile " + args[0] + " does not exist");
            System.exit(1);
        }

        int checkSumSize;
        try {
            checkSumSize = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.print("\nValid checksum sizes are 8, 16, or 32\n");
            System.exit(1);
            return;
        }

        if (Arrays.stream(VALID_BIT_SIZES).noneMatch(validBitSize -> validBitSize == checkSumSize)) {
            System.err.print("\nValid checksum sizes are 8, 16, or 32\n");
            System.exit(1);
        }

        String input = null;
        try {
            input = new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        byte[] adjustedBytes = getAdjustedByteArray(input, checkSumSize);
        int checksum = checksum(adjustedBytes, checkSumSize);
        System.out.printf("\n%s\n%2d bit checksum is %8x for all %4d chars\n",
                formattedStringOutput(getAdjustedString(input, checkSumSize)), checkSumSize, checksum, adjustedBytes.length);

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

    private static int checksum(byte[] bytes, int numBits) {
        switch (numBits) {
            case 8:
                return checksum8(bytes);
            case 16:
                return checksum16(bytes);
            case 32:
                return checksum32(bytes);
            default:
                throw new InvalidStateException("Valid checksum sizes are 8, 16, or 32\n");
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

        for (byte b : data) {
            check += b;
        }

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
     * @param output - message to format and output
     * @return formatted message output by adding a new line every 80 characters.
     * Citation: https://stackoverflow.com/questions/10530102/java-parse-string-and-add-line-break-every-100-characters
     */
    private static String formattedStringOutput(String output) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < output.length(); i++) {
            if (i > 0 && i % 80 == 0) {
                res.append("\n");
            }
            res.append(output.charAt(i));
        }
        return res.toString();
    }

    /**
     * @param in  - input string to be adjusted
     * @param bit - bit size parameter to adjust text with padding character 'X'
     * @return formatted output
     */
    private static String getAdjustedString(String in, int bit) {
        int originalSize = in.getBytes().length, padding = getPadding(originalSize, bit), newSize;
        StringBuilder builder = new StringBuilder();
        newSize = originalSize + padding;

        for (int i = 0; i < originalSize; i++) {
            builder.append(in.charAt(i));
        }

        if (padding > 0) {
            for (int j = originalSize; j < newSize; j++) {
                builder.append("X");
            }
        }

        return builder.toString();
    }


    /**
     * @param lengthOriginal - length of the input file non-padded
     * @param bit            - bit size == 32 then we mod 4 else mod 2. (4 for every 4 bytes : 2 for every 2 bytes)
     * @return amount of padding we need to add to the original length (input length) if bit > 8
     */
    private static int getPadding(int lengthOriginal, int bit) {
        int length = lengthOriginal;
        int modulus = bit == 32 ? 4 : 2;
        int result = 0;
        while (length % modulus != 0) {
            length = length + 1;
            result++;
        }
        return bit > 8 ? result : 0;
    }
}