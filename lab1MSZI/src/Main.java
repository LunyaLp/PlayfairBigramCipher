public class Main {
    public static void main(String[] args) {
        ClassicalCiphers cipher = new ClassicalCiphers();

        String wordPlayfair = "Nizhny Novgorod State Technical University";
        String deleteLetterPlayfair = "J";
        String letterReplacementPlayfair = "X";
        String encryptedPlayfair = cipher.PlayfairBigramCipher(wordPlayfair, deleteLetterPlayfair, letterReplacementPlayfair);
        System.out.println("Зашифрованное слово (Playfair): " + encryptedPlayfair);

        String decryptedPlayfair = cipher.PlayfairBigramDecipher(encryptedPlayfair, deleteLetterPlayfair, letterReplacementPlayfair);
        System.out.println("Расшифрованное слово (Playfair): " + decryptedPlayfair);

        System.out.println();

        String wordPlayfair1 = "acddeg";
        String deleteLetterPlayfair1 = "J";
        String letterReplacementPlayfair1 = "X";
        String encryptedPlayfair1 = cipher.PlayfairBigramCipher(wordPlayfair1, deleteLetterPlayfair1, letterReplacementPlayfair1);
        System.out.println("Зашифрованное слово (Playfair): " + encryptedPlayfair1);

        String decryptedPlayfair1 = cipher.PlayfairBigramDecipher(encryptedPlayfair1, deleteLetterPlayfair1, letterReplacementPlayfair1);
        System.out.println("Расшифрованное слово (Playfair): " + decryptedPlayfair1);
    }
}