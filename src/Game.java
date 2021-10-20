import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static Scanner scan = new Scanner(System.in);

    public static String getRandomStringFromArray(ArrayList<String> words) {
        Random random = new Random();
        int randomInt = random.nextInt(words.size());
        return words.get(randomInt);
    }

    public static String getStringBasedOnDifficulty(boolean difficulty) {
        if(difficulty) {
            return getRandomStringFromArray(Main.easyWords);
        } else {
            return getRandomStringFromArray(Main.hardWords);
        }
    }

    public static void gameMode(boolean difficulty){
        String wordToGuess = getStringBasedOnDifficulty(difficulty);
        int length = wordToGuess.length();

        AsciiArt.progressionInHanging(1);

        for (int i = 0; i < length; i++) {
            System.out.print("_ ");
        }

        String letterGuess = scan.nextLine();


    }
}
