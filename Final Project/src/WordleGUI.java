import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WordleGUI extends JFrame {
    private final JPanel mainPanel;
    private JTextField userNameField;
    private JTextField userGuessField;
    private JLabel hsLabel;
    private JLabel lettersGuessedVisualized;
    private JPanel gameOverPanel;
    private JButton guess;
    private static final int FRAME_WIDTH = 720;
    private static final int FRAME_HEIGHT = 800;
    private int startIndexTextBox = 0;
    ArrayList<JFormattedTextField> wordleGuesses;
    String lettersGuessed = "";
    WordleGame gameProcessor = new WordleGame();

    // Default constructor
    public WordleGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        createWelcomePanel();
        createHighScorePanel();
        createUserNamePanel();
        createUserGuessPanel();
        createLettersGuessedPanel();
        createGuessesPanel();
        createButtonsPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        add(mainPanel);
    }

    // Create the first panel to add to the main panel
    private void createWelcomePanel() {
        JLabel welcomeLabel = new JLabel("Welcome to Wordle");
        welcomeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        JLabel instructionsLabel = new JLabel("Guess the 5 letter word in as few attempts as possible (max: 6 attempts)");
        instructionsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        JPanel welcomePanel = new JPanel();
        JPanel instructionsPanel = new JPanel();
        welcomePanel.add(welcomeLabel);
        instructionsPanel.add(instructionsLabel);
        mainPanel.add(welcomePanel);
        mainPanel.add(instructionsPanel);
    }

    // Create the second panel to add to the main panel
    private void createHighScorePanel() {
        JPanel highScorePanel = new JPanel();
        hsLabel = new JLabel("HIGH SCORE:  " + gameProcessor.getHighScore() + " tries");
        highScorePanel.add(hsLabel);
        mainPanel.add(highScorePanel);
    }

    // Create the third panel to add to the main panel
    private void createUserNamePanel() {
        JPanel userNamePanel = new JPanel();
        JLabel newNameLabel = new JLabel("Enter your name: ");
        newNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        userNameField = new JTextField(7);
        userNamePanel.add(newNameLabel);
        userNamePanel.add(userNameField);
        mainPanel.add(userNamePanel);
    }

    // Create the fourth panel to add to the main panel
    private void createUserGuessPanel() {
        JPanel userGuessPanel = new JPanel();
        JLabel newGuessLabel = new JLabel("Enter a Guess: ");
        newGuessLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        final int FIELD_WIDTH = 5;
        userGuessField = new JTextField(FIELD_WIDTH);
        userGuessPanel.add(newGuessLabel);
        userGuessPanel.add(userGuessField);
        mainPanel.add(userGuessPanel);
    }

    // Create the fifth panel to add to the main panel
    private void createLettersGuessedPanel() {
        JPanel lettersGuessedPanel = new JPanel();
        JLabel lettersGuessedLabel = new JLabel("Letters Guessed: ");
        lettersGuessedVisualized = new JLabel();
        lettersGuessedVisualized.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        lettersGuessedPanel.add(lettersGuessedLabel);
        lettersGuessedPanel.add(lettersGuessedVisualized);
        mainPanel.add(lettersGuessedPanel);
    }

    // Create the sixth panel to add to the main panel
    private void createGuessesPanel() {

        // Initialize the arraylist to add to the grid
        wordleGuesses = new ArrayList<>();

        JPanel guessesPanel = new JPanel();
        // Make a grid layout to make it look like wordle
        guessesPanel.setLayout(new GridLayout(0, 5));
        // Set the maximum size so it doesn't become bigger or smaller
        guessesPanel.setMaximumSize(new Dimension(250, 0));

        // Fill the grid layout with text fields
        for(int i = 0; i < 30; i++) {
            guessesPanel.add(new JFormattedTextField());
        }

        // Loop through each text field in the panel
        for(Component c: guessesPanel.getComponents()) {
            // Set the font of each text field
            c.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
            // Cast the component to type JFormattedTextField to edit them
            JFormattedTextField newC = (JFormattedTextField) c;
            // Make the text fields not editable
            newC.setEditable(false);
            // Add the updated components to the arraylist of objects
            wordleGuesses.add(newC);
        }
        mainPanel.add(guessesPanel);
    }

    // Create the seventh panel to add to the main panel
    public void createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        // Create a button to create a new game
        JButton newGame = new JButton("New Game");
        // Add the action listener from the inner class
        newGame.addActionListener(new myButtonListener("New Game"));
        // Initialize the button to submit a guess
        guess = new JButton("Submit");
        // Add the action listener from the inner class
        guess.addActionListener(new myButtonListener("Submit"));

        // Set the layout of the buttons in the panel
        buttonsPanel.add(newGame, BorderLayout.WEST);
        buttonsPanel.add(guess, BorderLayout.EAST);

        mainPanel.add(buttonsPanel);
    }

    //Create the action listener for my buttons in panel seven
    class myButtonListener implements ActionListener {
        private String buttonName;

        // Constructor for action listener
        public myButtonListener(String s) {
            buttonName = s;
        }

        public void actionPerformed(ActionEvent e) {
            // If the Submit button is pressed
            if(buttonName.equals("Submit")) {
                // Clean the user's word using the function in the Game class
                String userCleanedInput = gameProcessor.cleanUserInput(userGuessField.getText());
                // Create the arrayList of colors corresponding to the correctness of each letter
                gameProcessor.checkUserInput(userCleanedInput);
                // Loop through each color in the color arrayList
                for (int i = 0; i < gameProcessor.letterColors.size(); i++) {
                    // If the color in the arraylist is green
                    if(gameProcessor.letterColors.get(i).equals("green")) {
                        // Using the arraylist of OBJECTS set the ith element's color to green
                        wordleGuesses.get(startIndexTextBox + i).setBackground(Color.GREEN);
                        // Set the ith element's text to the ith character in the user's inputted word
                        wordleGuesses.get(startIndexTextBox + i).setText(String.valueOf(userCleanedInput.charAt(i)));
                    }
                    // If the color in the arraylist is yellow
                    else if(gameProcessor.letterColors.get(i).equals("yellow")) {
                        // Using the arraylist of OBJECTS set the ith element's color to yellow
                        wordleGuesses.get(startIndexTextBox + i).setBackground(Color.yellow);
                        // Set the ith element's text to the ith character in the user's inputted word
                        wordleGuesses.get(startIndexTextBox + i).setText(String.valueOf(userCleanedInput.charAt(i)));
                    }
                    // If the color in the arraylist is gray
                    else if(gameProcessor.letterColors.get(i).equals("gray")) {
                        // Using the arraylist of OBJECTS set the ith element's color to gray
                        wordleGuesses.get(startIndexTextBox + i).setBackground(Color.gray);
                        // Set the ith element's text to the ith character in the user's inputted word
                        wordleGuesses.get(startIndexTextBox + i).setText(String.valueOf(userCleanedInput.charAt(i)));
                    }
                    // Kick the cursor down to the next line in the guesses grid
                    if(i == 4)
                        startIndexTextBox += 5;
                }

                // Check how many green letters are in the user's word
                int numGreenLetters = 0;
                for (String color: gameProcessor.letterColors) {
                    if (color.equals("green")){
                        numGreenLetters++;
                    }
                }

                // If the game hasn't ended and the user guessed the correct word
                if (gameProcessor.getCurrentScore() <= 6 && numGreenLetters == 5) {
                    // Initialize the popup game over panel
                    gameOverPanel = new JPanel();
                    // Congratulate the user on guessing the correct word
                    JLabel gameOverText = new JLabel("Congrats " + userNameField.getText() +
                            " on guessing the correct word in " + gameProcessor.getCurrentScore() + " guesses!");
                    gameOverPanel.add(gameOverText);
                    mainPanel.add(gameOverPanel);
                    // Remove the guess button so the user can't continue playing
                    guess.setVisible(false);

                    // If the user's score is better than or equal to the high score
                    if(gameProcessor.getCurrentScore() <= gameProcessor.getHighScoreNumber()) {
                        // Update the high score
                        gameProcessor.setNewHighScore(userNameField.getText());
                        hsLabel.setText("HIGH SCORE:  " + gameProcessor.getHighScore() + " tries");
                        // Add the user's score to the scores file
                        gameProcessor.addUserScore(userNameField.getText());
                    }
                    else {
                        // If the user didn't have the high score but won, add their score to the scores file
                        gameProcessor.addUserScore(userNameField.getText());
                    }
                    // Refresh the GUI
                    mainPanel.updateUI();

                    // If the user lost the game
                } else if (gameProcessor.getCurrentScore() == 6 && numGreenLetters != 5) {
                    // Initialize the popup game over panel
                    gameOverPanel = new JPanel();
                    // Tell the user good luck next time
                    JLabel gameOverText = new JLabel("Sorry " + userNameField.getText() +
                            ", you failed to guess the word '" + gameProcessor.getGameWord() + "' in time. Better luck next time!");
                    gameOverPanel.add(gameOverText);
                    mainPanel.add(gameOverPanel);
                    // Remove the guess button so the user can't continue playing
                    guess.setVisible(false);
                    // Refresh the GUI
                    mainPanel.updateUI();
                }
                // Update the letters guessed to include the letters that the user just inputted
                lettersGuessed = gameProcessor.checkIfLetterGuessed(lettersGuessed, userGuessField.getText());
                // Add the letters guessed to the text box to display it
                lettersGuessedVisualized.setText(lettersGuessed);
            }
            // If the New Game button is pressed
            else if(buttonName.equals("New Game")) {
                // Set a new game word
                gameProcessor.setGameWord();
                // Reset the user text input box
                userGuessField.setText("");
                // Reset all the text boxes in the grid
                for(JFormattedTextField c: wordleGuesses) {
                    c.setText("");
                    c.setBackground(Color.white);
                }
                // Reset the index for the guesses text boxes
                startIndexTextBox = 0;
                // Reset the letters guessed
                lettersGuessed = "";
                lettersGuessedVisualized.setText("");
                // Reset the score
                gameProcessor.resetCurrentScore();
                // Remove the popup panel
                mainPanel.remove(gameOverPanel);
                // Re-add the guess button
                guess.setVisible(true);
                // Refresh the GUI
                mainPanel.updateUI();
            }
        }
    }
}