import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> easyWords = new ArrayList<>();
    static ArrayList<String> hardWords = new ArrayList<>();
    static ArrayList<String> wronglyGuessedLetters = new ArrayList<>();

    static Scanner scan = new Scanner(System.in);

    //this method utilizes the scanner to read from the hangman-wordbank.csv file
    //by the use of split we differentiate between different information on each line
    //in said file.
    //a while loop is run n number of times (equivalent to the number of lines in the file)
    //during each run the correct words are added to their respective ArrayLists for later use

    //a try/catch is also used, in case the pathname is incorrect, or other errors should appear
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

    //method used for making a choice between to options (returns boolean)
    public static boolean eitherOr() {
        while(true) {
        String oneOrTwo = scan.nextLine();
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
        fillWordbankArrays(); //fills the 'easy' and 'hard' ArrayLists with the word from the wordbank

        //welcome message with subsequent prompt for choice of difficulty
        System.out.println("Hello and welcome to hangman");
        System.out.println("Would you rather play on easy or hard?");
        System.out.println("Easy: press 1");
        System.out.println("Hard: press 2");

        //eitherOr method called (returns boolean, true=easy or false=hard)
        //saved as a boolean 'choiceOfDifficulty'
        boolean choiceOfDifficulty = eitherOr();

        //calls method from the Game class, which returns a String from either the easy or the hard ArrayList
        String wordToGuess = Game.getStringBasedOnDifficulty(choiceOfDifficulty);

        //should be deleted upon being turned in
        //System.out.println("Word to guess is: " + wordToGuess);

        Game.fillRevisedHiddenWordArray(wordToGuess); //adds n number of '_' to the revisedHiddenWordArray, based on the number of letters in the String 'wordToGuess'
        Game.fillHiddenWordArray(wordToGuess); //adds the individual letters of the String 'wordToGuess' to hiddenWordArray

        int count = 1;
        int winningIndicator = 0;

        AsciiArt.progressionInHanging(1); //prints the first progression of the hangman evolution

        //while loop, which runs, until either of the two conditionals are no longer true
        //if count exceeds 7, the player loses
        //if winningIndicator exceeds 1, the player wins
        while(count <= 7 && winningIndicator < 2) {

            System.out.println(Game.revisedHiddenWordArray); //displays the wordToGuess with '_' n number of times, based on length of String
            System.out.println("Please guess a letter");
            String letter = scan.nextLine();


            boolean wordToGuessContainsLetter = Game.isLetterInWord(wordToGuess, letter); //boolean which returns true if wordToGuess contains letter (false if not)
            //if wordToGuessContainsLetter turns out to be false, this if statement commences
            if (!wordToGuessContainsLetter) {
                count += 1; //count becomes one larger, every time a wrong letter is guessed
                wronglyGuessedLetters.add(letter); //the wrongly guessed letter is added to an ArrayList containing them
                AsciiArt.progressionInHanging(count); //the method of the asciiArt class progresses by one evolution, due to the variable count increasing
                System.out.println("Wrongly guessed letters: " + wronglyGuessedLetters); //wrongly guessed letters are displayed to remind the player of their mistakes so far

            } else {
                //if this else statement is reached it is implicit that the letter guessed is contained within the wordToGuess
                //Therefore all methods utilized within this bracket of the code, are written with that in mind

                //in case the letter appears several times throughout the word, an ArrayList of Integers is returned,
                //so as to represent the letter's position
                ArrayList<Integer> indicesOfGuessedLettersArray = Game.getIndexOfLetterInWord(wordToGuess, letter);

                //the method called in function 'reveals' the correct letters of the revisedHiddenWordArray
                //(replacing the '_' with the rightly guessed letter at the place/places it is repres®ented)
                ArrayList<Character> revisedHiddenWordArray = Game.reviseHiddenWord(indicesOfGuessedLettersArray);
                AsciiArt.progressionInHanging(count); //asciiArt is printed as usual

                //wrongly guessed letters are displayed if the number of wrongly guessed letters exceeds 0
                if(wronglyGuessedLetters.size() >= 1) {
                    System.out.println("Wrongly guessed letters: " + wronglyGuessedLetters);
                }
                //if at any point the hiddenWordArray equals the revisedHiddenWordArray, the if-statement is entered
                //and the variable winningIndicator is changed to 3, therein exiting the while-loop
                if(revisedHiddenWordArray.equals(Game.hiddenWordArray)) {
                    winningIndicator = 3;
                }
            }
        }
        //if count equals or exceeds 8, the player has lost
        if(count >= 8) {
            System.out.println("Sorry, you lost");
            System.out.println("The word was " + wordToGuess); //the right word is displayed
        }
        //if count equals or is less than 7, the player has rightly guessed the word, and wins the game
        if(count <= 7) {
            System.out.println("Congratulations, you win!");
        }
    }
}
