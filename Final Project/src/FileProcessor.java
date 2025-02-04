import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
    private String wordsFile = "Final Project/src/dictionary.txt";
    private String scoresFile = "Final Project/src/scores.txt";
    private String highScoreFile = "Final Project/src/highScore.txt";

    // Default constructor
    public FileProcessor() {}

    /*
    Scan in the all the words from the dictionary file but only select the
    words that have five letters and store the words in an arraylist
     */
    public ArrayList<String> getGameWords() {
        ArrayList<String> possibleWords = new ArrayList<>();

        try (Scanner wordScanner = new Scanner(Paths.get(wordsFile))) {
            while (wordScanner.hasNextLine()) {
                String line = wordScanner.nextLine();
                //Make sure that each word length is valid for wordle
                if(line.length() == 5) {
                    //If the word is valid add it to the possible words to select
                    possibleWords.add(line);
                }
            }
            return possibleWords;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return possibleWords;
        }
    }

    // Scan in the all the scores from the scores file and store the words in an arraylist
    public ArrayList<String> getScores() {
        ArrayList<String> listOfScores = new ArrayList<>();

        try (Scanner scoreScanner = new Scanner(Paths.get(scoresFile))) {
            while (scoreScanner.hasNextLine()) {
                String line = scoreScanner.nextLine();
                listOfScores.add(line);
            }
            return listOfScores;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return listOfScores;
        }
    }

    // Write a players score to the scores file
    public void addScore(String name, int score) {
        try (PrintWriter output = new PrintWriter(new FileWriter(scoresFile, true))) {
            output.println(name + " - " + score);
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Get the high score from the highscore file and return it to the user
    public String getHighScoreContents() {
        String highScore = "";
        try (Scanner scoreScanner = new Scanner(Paths.get(highScoreFile))) {
            highScore = scoreScanner.nextLine();
            return highScore;
        }

        catch (Exception e) {
            System.out.println("Error: " + e);
            return highScore;
        }
    }

    // Set a new high score by writing it to the highscore file
    public void setNewHighScore(String name, int score) {
        try (PrintWriter output = new PrintWriter(highScoreFile)) {
            output.println(name + " - " + score);
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}