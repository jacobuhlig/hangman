import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> easyWords = new ArrayList<String>();
    static ArrayList<String> hardWords = new ArrayList<String>();


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

    public static void main(String[] args) {
        fillWordbankArrays();


    }
}
