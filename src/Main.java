import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> easyWords = new ArrayList<String>();
    static ArrayList<String> hardWords = new ArrayList<String>();

    static Scanner scan = new Scanner(System.in);

    public static void fillWordbankArrays() {
        File hangmanWordbank = new File("resources/hangman-wordbank.csv");
        try {
            Scanner scanWordbank = new Scanner(hangmanWordbank);
            while(scanWordbank.hasNextLine()) {
                String line = scanWordbank.nextLine();
                String[] stringArray = line.split(";");
                easyWords.add(stringArray[0]);
                hardWords.add(stringArray[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            e.printStackTrace();
        }
    }

    public static void welcomeMessage() {
        System.out.println("Would you rather play on easy or hard?");
        System.out.println("Easy: press 1");
        System.out.println("Hard: press 2");
    }

    public static boolean eitherOr() {
        String oneOrTwo = scan.nextLine();
        while(true) {
            if(oneOrTwo.equals("1")) {
                return true;
            } else if(oneOrTwo.equals("2")) {
                return false;
            } else {
                System.out.println("Please choose 1 or 2");
            }
        }
    }

    public static void main(String[] args) {
        fillWordbankArrays();

        System.out.println("Hello and welcome to hangman");

        welcomeMessage();

        boolean choiceOfDifficulty = eitherOr();
        Game.gameMode(choiceOfDifficulty);



    }
}
