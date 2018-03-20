import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * CIS3360 - Spring 2018
 * Security in Computing - Assignment 1
 * Submitted by: <Christopher Narciso> <Omar Padilla>
 */

public class vigenere {

    public static void main(String[] args) {
        String keyFileName = args[0];
        String plaintextFileName = args[1];
        String key = null, plaintext = null;

        try {
            key = readFileAsString(keyFileName);
            plaintext = readFileAsString(plaintextFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        key = key.replaceAll("[^A-Za-z[0-9]]", "").toLowerCase();
        plaintext = plaintext.replaceAll("[^A-Za-z[0-9]]", "").toLowerCase();

        int keyLength = key.length(), plaintextLength = plaintext.length(), paddingCount;

        paddingCount = plaintextLength < 512 ? keyLength - (plaintextLength % keyLength) : 0;

        char[] plaintextAdjusted = getAdjustedArray(plaintextLength, plaintext, paddingCount != 0, paddingCount);
        char[] keyAdjusted = getAdjustedArray(keyLength, key, false, 0);
        char[] cipherAdjusted = encrypt(plaintextAdjusted, keyAdjusted);

        System.out.printf("\n\nVigenere Key:\n\n%s\n", formattedString(keyAdjusted));
        System.out.printf("\n\nPlaintext:\n\n%s\n", formattedString(plaintextAdjusted));
        System.out.printf("\n\nCiphertext:\n\n%s\n", formattedString(cipherAdjusted));
    }

    /**
     * @param plaintext - The plaintext
     * @param key       - The key
     * @return encryptedMessage using the Vigenere cipher (lowercase)
     * C[i] = (p[i] + k[i]) mod N
     * C = cipher,
     * k = key,
     * p = plaintext,
     * N = # of letters in alphabet (26)
     * offset = 97 to stay in the range of lowercase characters [97-122]
     */
    private static char[] encrypt(char[] plaintext, char[] key) {
        char[] temp = new char[plaintext.length];
        final int offset = 97;
        for (int i = 0; i < temp.length; i++) {
            if (plaintext[i] == ' ') break;
            int p = (int) plaintext[i] - offset;
            int k = (int) key[i % key.length] - offset;
            int c = (p + k) % 26;
            char encryptedCharacter = (char) (c + offset);
            temp[i] = encryptedCharacter;
        }
        return temp;
    }

    /**
     * @param length        - The length of char[] we are adjusting
     * @param input         - The String input we are putting into char[] temp
     * @param padding       - check if we need to pad or not based on the boolean value
     * @param paddingAmount - The 'x' padding amount needed if > 0 && padding
     * @return char array adjusted to meet the 512 requirements
     */
    private static char[] getAdjustedArray(int length, String input, boolean padding, int paddingAmount) {
        char[] temp = new char[length > 512 || padding ? 512 : length];
        int paddingStart = 0;
        for (int i = 0; i < (padding ? input.length() : temp.length); i++) {
            temp[i] = input.charAt(i);
            paddingStart++;
        }
        if (padding) {
            for (int i = paddingStart, j = 1; i < temp.length; i++, j++) {
                temp[i] = (j > paddingAmount ? ' ' : 'x');
            }
        }
        return temp;
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
    private static String formattedString(char[] output) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < output.length; i++) {
            if (output[i] == ' ' || output[i] == '\u0000') break;
            if (i > 0 && i % 80 == 0)
                res.append("\n");
            res.append(output[i]);
        }
        return res.toString().toLowerCase();
    }

}