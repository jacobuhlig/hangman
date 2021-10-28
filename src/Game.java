import java.util.ArrayList;
import java.util.Random;

public class Game {
    static ArrayList<Character> revisedHiddenWordArray = new ArrayList<>();
    static ArrayList<Character> hiddenWordArray = new ArrayList<>();

    public static void fillRevisedHiddenWordArray(String wordToGuess) {
        int length = wordToGuess.length();

        for (int i = 0; i <= length - 1; i++) {
            revisedHiddenWordArray.add(i, '_');
        }
    }

    public static void fillHiddenWordArray(String wordToGuess) {
        int length = wordToGuess.length();

        for (int i = 0; i <= length - 1; i++) {
            hiddenWordArray.add(i, wordToGuess.charAt(i));
        }
    }

    //Generates a random int, which is then used to retrieve and return a String from the 'words' ArrayList
    public static String getRandomStringFromArray(ArrayList<String> words) {
        Random random = new Random();
        int randomInt = random.nextInt(words.size());
        return words.get(randomInt);
    }

    //method calls upon the getRandomStringFromArray
    public static String getStringBasedOnDifficulty(boolean difficulty) {
        if(difficulty) {
            return getRandomStringFromArray(Main.easyWords);
        } else {
            return getRandomStringFromArray(Main.hardWords);
        }
    }

    public static boolean isLetterInWord(String wordToGuess, String letter) {
        return wordToGuess.contains(letter);
    }

    public static ArrayList<Integer> getIndexOfLetterInWord(String wordToGuess, String letter) {
        ArrayList<Integer> indicesArray = new ArrayList<>();
        char letterAsChar = letter.charAt(0);

        for (int i = 0; i <= wordToGuess.length() - 1; i++) {
            char x = wordToGuess.charAt(i);
            if(x == letterAsChar) {
                indicesArray.add(i);
            }
        }
        return indicesArray;
    }

    public static ArrayList<Character> reviseHiddenWord(ArrayList<Integer> indicesOfGuessedLettersArray) {

        for (int i = 0; i <= indicesOfGuessedLettersArray.size() - 1; i++) {
            int indexOfGuessedLetter = Integer.parseInt(String.valueOf(indicesOfGuessedLettersArray.get(i)));
            revisedHiddenWordArray.set(indexOfGuessedLetter, hiddenWordArray.get(indexOfGuessedLetter));
        }
        return revisedHiddenWordArray;
    }
}
