public class ClassicalCiphers {
    char[][] matrix = new char[5][5];

    /**
     * Находит позицию символа в матрице 5x5.
     * @param c Символ, позицию которого нужно найти.
     * @return Массив из двух целых чисел, представляющий строку и столбец символа.
     */
    private int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j}; // Возвращаем координаты символа
                }
            }
        }
        return new int[]{-1, -1}; // Возвращает -1, -1, если символ не найден
    }

    /**
     * Определяет позиции пробелов в исходном слове.
     * @param word Исходное слово с пробелами.
     * @return Массив позиций пробелов.
     */
    private int[] getSpacePositions(String word) {
        int spaceCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        int[] spacePositions = new int[spaceCount];
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                spacePositions[index] = i; // Запоминаем позиции пробелов
                index++;
            }
        }
        return spacePositions;
    }

    /**
     * Вставляет пробелы в строку на указанные позиции.
     * @param word Строка без пробелов.
     * @param spacePositions Позиции, на которые нужно вставить пробелы.
     * @return Строка с восстановленными пробелами.
     */
    private String insertSpaces(String word, int[] spacePositions) {
        char[] wordArray = word.toCharArray();
        char[] resultArray = new char[wordArray.length + spacePositions.length];
        int wordIndex = 0;
        int spaceIndex = 0;

        // Вставляем пробелы в исходную строку
        for (int i = 0; i < resultArray.length; i++) {
            if (spaceIndex < spacePositions.length && i == spacePositions[spaceIndex]) {
                resultArray[i] = ' ';
                spaceIndex++;
            } else {
                resultArray[i] = wordArray[wordIndex];
                wordIndex++;
            }
        }
        return new String(resultArray);
    }

    /**
     * Выполняет шифрование текста с использованием шифра Плейфейра.
     * @param word Исходный текст для шифрования.
     * @param deleteLetter Буква, которую нужно исключить из матрицы (обычно J).
     * @param letterReplacement Буква, используемая в качестве замены при удвоении символов или нечётной длине.
     * @return Зашифрованный текст.
     */
    public String PlayfairBigramCipher(String word, String deleteLetter, String letterReplacement) {
        String result = "";
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // Алфавит без буквы J
        // Убираем пробелы и переводим в верхний регистр
        String wordBig = word.toUpperCase().replaceAll(" ", "");
        String deleteLetterBig = deleteLetter.toUpperCase();
        String modifiedAlphabet = alphabet.replace(deleteLetterBig, "");
        int index = 0;

        // Заполняем матрицу алфавитом без удаленной буквы
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = modifiedAlphabet.charAt(index++);
            }
        }

        // Обрабатываем повторяющиеся буквы
        StringBuilder processedWord = new StringBuilder();
        for (int i = 0; i < wordBig.length(); i++) {
            char currentChar = wordBig.charAt(i);
            processedWord.append(currentChar);
            // Если следующая буква такая же, добавляем букву-заполнитель
            if (i < wordBig.length() - 1 && currentChar == wordBig.charAt(i + 1)) {
                processedWord.append(letterReplacement);
            }
        }

        wordBig = processedWord.toString();

        // Если длина слова нечетная, добавляем букву-заполнитель
        if (wordBig.length() % 2 != 0) {
            wordBig += letterReplacement;
        }

        // Обрабатываем биграммы (пары символов)
        for (int i = 0; i < wordBig.length(); i += 2) {
            char firstChar = wordBig.charAt(i);
            char secondChar = wordBig.charAt(i + 1);

            int[] firstPos = findPosition(firstChar);
            int[] secondPos = findPosition(secondChar);

            // Если символы находятся в одной строке
            if (firstPos[0] == secondPos[0]) {
                result += matrix[firstPos[0]][(firstPos[1] + 1) % 5];
                result += matrix[secondPos[0]][(secondPos[1] + 1) % 5];
            }
            // Если символы в одном столбце
            else if (firstPos[1] == secondPos[1]) {
                result += matrix[(firstPos[0] + 1) % 5][firstPos[1]];
                result += matrix[(secondPos[0] + 1) % 5][secondPos[1]];
            }
            // Если символы образуют прямоугольник
            else {
                result += matrix[firstPos[0]][secondPos[1]];
                result += matrix[secondPos[0]][firstPos[1]];
            }
        }

        return result;
    }

    /**
     * Выполняет расшифровку текста, зашифрованного с использованием шифра Плейфейра.
     * @param word Зашифрованный текст.
     * @param deleteLetter Буква, исключённая из матрицы (обычно J).
     * @param letterReplacement Буква, использованная в качестве замены.
     * @return Расшифрованный текст.
     */
    public String PlayfairBigramDecipher(String word, String deleteLetter, String letterReplacement) {
        String result = "";
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        String wordBig = word.toUpperCase().replaceAll(" ", "");
        String deleteLetterBig = deleteLetter.toUpperCase();
        String modifiedAlphabet = alphabet.replace(deleteLetterBig, "");
        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = modifiedAlphabet.charAt(index++);
            }
        }

        if (wordBig.length() % 2 != 0) {
            wordBig += letterReplacement;
        }

        for (int i = 0; i < wordBig.length(); i += 2) {
            char firstChar = wordBig.charAt(i);
            char secondChar = wordBig.charAt(i + 1);

            int[] firstPos = findPosition(firstChar);
            int[] secondPos = findPosition(secondChar);

            if (firstPos[0] == secondPos[0]) { // Одна строка
                result += matrix[firstPos[0]][(firstPos[1] - 1 + 5) % 5];
                result += matrix[secondPos[0]][(secondPos[1] - 1 + 5) % 5];
            } else if (firstPos[1] == secondPos[1]) { // Один столбец
                result += matrix[(firstPos[0] - 1 + 5) % 5][firstPos[1]];
                result += matrix[(secondPos[0] - 1 + 5) % 5][secondPos[1]];
            } else { // Прямоугольник
                result += matrix[firstPos[0]][secondPos[1]];
                result += matrix[secondPos[0]][firstPos[1]];
            }
        }

        result = result.replace(letterReplacement, "");
        int[] spacePositions = getSpacePositions(word);
        result = insertSpaces(result, spacePositions);
        return result;
    }
}
