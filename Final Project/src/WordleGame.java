import java.util.ArrayList;
import java.util.Random;

public class WordleGame{
    private int currentScore = 0;
    private String highScore;
    private int highScoreNum = 0;
    private String gameWord;
    ArrayList<String> letterColors = new ArrayList<>();
    FileProcessor fileInformation;

    // Default constructor
    public WordleGame() {
        setGameWord();
        initializeHighScore();
        initializeHighScoreNumber();
    }

    // Set the high score using the input from the file
    public void initializeHighScore() {
        highScore = fileInformation.getHighScoreContents();
    }

    // Getter
    public String getHighScore() {
        return highScore;
    }

    // Set the high score NUMBER using the high score
    public void initializeHighScoreNumber() {
        highScoreNum = Integer.parseInt(highScore.substring(highScore.length() - 1));
    }

    // Setter
    public void setNewHighScore(String name) {
        fileInformation.setNewHighScore(name, currentScore);
        initializeHighScore();
    }

    // Add the user's score to the scores file if they win
    public void addUserScore(String name) {
        fileInformation.addScore(name, currentScore);
    }

    // Getter
    public int getHighScoreNumber() {
        return highScoreNum;
    }

    //Getter
    public String getGameWord() {
        return gameWord;
    }

    // Getter
    public int getCurrentScore() {
        return currentScore;
    }

    // Reset the score when the user starts a new game
    public void resetCurrentScore() {
        currentScore = 0;
    }

    /*
    Take the arrayList of possible words from the file processor class
    and use the Random module to get a random word from the arrayList
     */
    public void setGameWord() {
        Random random = new Random();
        fileInformation = new FileProcessor();
        ArrayList<String> wordList = fileInformation.getGameWords();
        int randIndex = random.nextInt(wordList.size());
        gameWord = wordList.get(randIndex);
        gameWord = gameWord.toLowerCase();
    }

    // Make the user's input readable and valid by cleaning it
    public String cleanUserInput(String userWord) {
        //Make sure the user's word has valid characters
        letterColors.clear();
        userWord = userWord.toLowerCase();
        String testCharacters = " .!,?";
        int i = 0;
        while (i < userWord.length()) {
            if(testCharacters.contains(userWord.substring(i, i + 1)))
                userWord = userWord.replace(userWord.substring(i, i + 1), "");
            else
                i++;
        }
        return userWord;
    }

    /*
    Take the validated word from the previous function and check whether the user's word
    and the game word match. Store the results to an arrayList showing the correct color
    corresponding to each letter
     */
    public void checkUserInput(String userWord) {

        for (int j = 0; j < gameWord.length(); j++) {
            if(gameWord.charAt(j) == userWord.charAt(j)) {
                //Make the background color green
                letterColors.add(j, "green");
            }
            else if(gameWord.contains((String.valueOf(userWord.charAt(j))))) {

                //Make the background color yellow
                letterColors.add(j, "yellow");
            }
            else {
                //Make the background color gray
                letterColors.add(j, "gray");
            }
        }
        currentScore++;
    }

    /*
    Check if the letters in the userWord are already contained in the wordsGuessed string,
    if not then add them to the string of wordsGuessed
     */
    public String checkIfLetterGuessed(String wordsGuessed, String userWord) {
        for (int i = 0; i < userWord.length(); i++) {
            if (!wordsGuessed.contains((String.valueOf(userWord.charAt(i))))) {
                wordsGuessed += userWord.charAt(i);
            }
        }
        return wordsGuessed;
    }
}
