import bridges.games.NonBlockingGame;

import java.util.ArrayList;

import bridges.base.Grid;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;

public class MineSweeper extends NonBlockingGame {

    // set up default colors, symbols, positions, and sizes for the game grid
	//TODO: set the board size to your liking, 10 rows and 10 columns is a recommendation
    static int numRows = 10;
    static int numCols = 10;
    
    //Tracks the previous color of a block
    NamedColor oldColor;
    NamedColor color = NamedColor.grey;
    NamedSymbol mine = NamedSymbol.bomb;
    NamedSymbol flag = NamedSymbol.flag;
    int[] currPos = {numRows / 2, numCols / 2};

    //Maintains the positions of the mines
    Grid<Integer> mines;
    int numMines;

    //Keep sentinel variables for continuing, restarting, or quitting
    boolean gameOver;
    boolean quit;

    //Keep a grid of integers for the state of each cell (flagged, numbered, opened, etc).
    Grid<Integer> state;
    //How many cells have been clicked
    int openCells = 0;

    public static void main(String args[]) {

        // Initialize our blocking game
    	//TODO: Fill in your information
		MineSweeper bg = new MineSweeper(8, "ntsander83758", "254082827329", numRows, numCols);
    }

    public MineSweeper(int assid, String login, String apiKey, int row, int col) {
        super(assid, login, apiKey, row, col);

        start();
    }

    public void initialize() {
        // Title and description of game
        setTitle("Minesweeper");
        setDescription("Controls:\nClick: Space\nFlag: 's'\nRestart: 'w'");
       

        // Initialize variables and paint the first game grid
        setupGameGrid();

        // Render initial game
        restart();
    }

    public void gameLoop(){
        // Handle each keypress
        handleKeypress();
    }

    // handle keypress events from the player
    public void handleKeypress() {
        if(keyQ()){
        	//TODO: call the quit method
            quit();
        }
        if(keyW()){
            //TODO: call the restart method
            restart();
        }
        if(keyS()){
            //TODO: call the flag method
            flag();
        }
        if(keySpace()){
            //TODO: call the click cell method
            clickCell();
        }
        //If the user didn't press the above keys, the user pressed an arrow and needs to move the cursor 
        moveSelection();
    }

    // Handle 'clicking' on a particular cell
    public void clickCell() {
        int i = currPos[0]; // row
        int j = currPos[1]; // col

        // can't click flagged cells!
        //TODO: test if the position i, j in the state grid equals two (this is a flagged state)
        if (state.get(i, j) == 2) {
            return;
        }

        // if a mine was clicked, it's game over
        // otherwise, visit the cell (recursively)
        //TODO: test if the position i, j in the mines grid equals one (this represent that a mine is present)
        if (mines.get(i, j) == 1) {
            highlightMines();
            //TODO: set the gameover boolean to true
            gameOver = true;
        } else {
            visitCell(i, j);
        }
    }

    // add or remove a flag from the current cell selection
    public void flag() {
        int i = currPos[0]; // row
        int j = currPos[1]; // col

        // add or remove flag, works as a toggle
        //TODO: test if the position i, j in the state grid is equal to 0
        if (state.get(i, j) == 0) {
            drawSymbol(j, i, flag, NamedColor.green);
            //TODO: change the state grid in position i, j to 2
            state.set(i, j, 2);
            //Note: the grid has a method called set that takes three parameters, row, column, and the value you want to set it to
            checkVictory();
        } else { //there is already a flag in this spot, remove the flag
            drawSymbol(j, i, NamedSymbol.none, NamedColor.gold);
        	//TODO: change the state grid in position i, j to 0 indicating there should not be a flag
            state.set(i, j, 0);
        }
    }

    // Recursively visit a cell. If it has no adjacent mines, visit its neighbors.
	// Think about a cell, it has eight neighbors...above, below, left, right, and then the four diagonal corners
    public void visitCell(int i, int j) {
    	//If the state for position i, j is greater than 0, we have already visited the cell
        if (state.get(i, j) > 0) {
            return;
        }
        //Set the state of position i, j to 1 indicating we have visited the cell
        state.set(i, j, 1);
        //TODO: Increase the number of cells that have been clicked by one
        openCells += 1;
    
        //Has the player freed all of the cells without mines? 
        if (openCells >= numRows * numCols - numMines) {
            victory();
        }

        //Need to calculate the number of adjacent mines. That means the number of mines in cells touching the selected one
        int adjacentMines = 0;

        // compute adjacent mines
        if (j > 0) {
            if (mines.get(i, j - 1) == 1)
                adjacentMines++; // left
            if (i > 0 && mines.get(i - 1, j - 1) == 1)
                adjacentMines++; // top left
            if (i < numRows - 1 && mines.get(i + 1, j - 1) == 1)
                adjacentMines++; // bottom left
        }
        if (j < numCols - 1) {
            if (mines.get(i, j + 1) == 1)
                adjacentMines++; // right
            if (i > 0 && mines.get(i - 1, j + 1) == 1)
                adjacentMines++; // top right
            if (i < numRows - 1 && mines.get(i + 1, j + 1) == 1)
                adjacentMines++; // bottom right
        }
        if (i > 0) {
            if (mines.get(i - 1, j) == 1)
                adjacentMines++; // top
        }
        if (i < numRows - 1) {
            if (mines.get(i + 1, j) == 1)
                adjacentMines++; // bottom
        }

        //Create an ArrayList of symbols holding the images of the numbers 1, 2, 3, 4, 5, 6, 7, 8
        ArrayList<NamedSymbol> symbols = new ArrayList<NamedSymbol>();
        symbols.add(NamedSymbol.one);
        symbols.add(NamedSymbol.two);
        symbols.add(NamedSymbol.three);
        symbols.add(NamedSymbol.four);
        symbols.add(NamedSymbol.five);
        symbols.add(NamedSymbol.six);
        symbols.add(NamedSymbol.seven);
        symbols.add(NamedSymbol.eight);
        // draw the current cell
        if (adjacentMines > 0) {
            drawSymbol(j, i, symbols.get(adjacentMines-1), NamedColor.red);
            setBGColor(j, i, NamedColor.lightgrey);
            state.set(i, j, 3);
        }
        // recursively visit adjacent cells if no adjacent mines
        else {
            setBGColor(j, i, NamedColor.lightgrey);
            state.set(i,j,3); //color should be lightgrey indicating the cell has been checked and does not have a mine
            //TODO: write the algorithm to call visitCell on the adjacent cells...

            //you have to identify the row and col needed as the parameters to pass visitCell
            //left, top left, bottom left, right, top right, bottom right, top, and bottom

            // Top
            if (i > 0)
                visitCell(i - 1, j);
            // Top left
            if (i > 0 && j > 0)
                visitCell(i - 1, j - 1);
            // Left
            if (j > 0)
                visitCell(i, j - 1);
            // Bottom left
            if (i < numRows - 1 && j > 0)
                visitCell(i + 1, j - 1);
            // Bottom
            if (i < numRows - 1)
                visitCell(i + 1, j);
            // Bottom right
            if (i < numRows - 1 && j < numCols - 1)
                visitCell(i + 1, j + 1);
            // Right
            if (j < numCols - 1)
                visitCell(i, j + 1);
            // Top right
            if (i > 0 && j < numCols - 1)
                visitCell(i - 1, j + 1);
        }
    }

    // after losing, show all the mine positions
    public void highlightMines() {
        //TODO: loop through the rows (i)
        for (int i = 0; i < numRows; i++) {
            //TODO: loop through the cols (j)
            for (int j = 0; j < numCols; j++) {
                if (mines.get(i, j) == 1) {
                    setBGColor(j, i, NamedColor.red);
                    drawSymbol(j, i, mine, NamedColor.black);
                }
            }
        }
    }

    // Move the current cell selection
    public void moveSelection() {

        int[] oldPos = {currPos[0], currPos[1]};
        if(keyDownJustPressed()){
           //TODO: change the currPos[1] to the correct value...make sure it is within the grid
        	//hint: may need to test against the size of the board
            if (currPos[1] < numCols - 1)
                currPos[1] += 1;
        }
        if(keyUpJustPressed()){
        	//TODO: change the currPos[1] to the correct value...make sure it is within the grid
        	//hint: may need to test against the size of the board
            if (currPos[1] > 0)
                currPos[1] -= 1;
        }
        if(keyLeftJustPressed()){
        	//TODO: change the currPos[0] to the correct value...make sure it is within the grid
        	//hint: may need to test against the size of the board
            if (currPos[0] > 0)
                currPos[0] -= 1;
        }
        if(keyRightJustPressed()){
        	//TODO: change the currPos[0] to the correct value...make sure it is within the grid
        	//hint: may need to test against the size of the board
            if (currPos[0] < numRows - 1)
                currPos[0] += 1;
        }

        // update the old color (previously selected cell)
        setBGColor(oldPos[1], oldPos[0], oldColor);
        if(state.get(currPos[0], currPos[1]) > 0)
            oldColor = NamedColor.lightgrey;
        else 
            oldColor = NamedColor.grey;
        
        // highlight the current selected cell
        setBGColor(currPos[1], currPos[0], NamedColor.gold);

    }

    // reinitialize everything and start over
    public void restart() {
    	//TODO: set gameover to false
        gameOver = false;
    	//TODO: call the function to set up the game again
        setupGameGrid();
    }

    // see if all the mines have been flagged
    public void checkVictory() {
        int flaggedMines = 0;
        for (int i = 0; i < numRows; i++) { // rows
            for (int j = 0; j < numCols; j++) { // cols
                if (mines.get(i, j) == 1 && state.get(i, j) == 2)
                    flaggedMines++;
            }
        }
      //TODO: test that all mines have been identified
        if (flaggedMines == numMines)
            victory();
    }

    // you won! Show all the mines you found.
    public void victory() {
        for (int i = 0; i < numRows; i++) { // rows
            for (int j = 0; j < numCols; j++) { // cols
                if (mines.get(i, j) == 1) {
                    setBGColor(j, i, NamedColor.black);
                    //TODO: feel free to change the symbol and color
                    drawSymbol(j, i, NamedSymbol.duck, NamedColor.gold);
                }
            }
        }
        //TODO: set gameover to true
        gameOver = true;
    }

    // Initialize positions, counts, and board states
    public void setupGameGrid() {
        openCells = 0;
        setupMines();
        initializeState();
        currPos = new int[]{numRows / 2, numCols / 2};

        // draw minesweeper gg
        for (int i = 0; i < numRows; i++) { // rows
            for (int j = 0; j < numCols; j++) { // cols
                setBGColor(j, i, NamedColor.grey);
                drawSymbol(j, i, NamedSymbol.none, NamedColor.white);
            }
        }

        // draw current position
        oldColor = NamedColor.grey;
        setBGColor(currPos[1], currPos[0], NamedColor.gold);
    }

    // Initialize the state representation
    public void initializeState() {
        state = new Grid<Integer>(numRows, numCols);
        for (int i = 0; i < numRows; i++) { // rows
            for (int j = 0; j < numCols; j++) { // cols
                state.set(j, i, 0);
            }
        }
    }

    // Initialize a new set of mines
    // 0 = empty, 1 = mine
    public void setupMines() {
        mines = new Grid<Integer>(numRows, numCols);
        // TODO: loop through rows
        for (int i = 0; i < numRows; i++) {
            // TODO: loop through cols
            for (int j = 0; j < numCols; j++) {
                //TODO: set the all entries (position i, j) in mines to 0 by default
                mines.set(i, j, 0);
            }
        }

        //TODO: determine the number of mines you want to use
        numMines = 10;

        int ri, rj;
        ri = (int) Math.floor(Math.random() * numRows);
        rj = (int) Math.floor(Math.random() * numCols);
        System.out.println("i (row) :" + ri + " , j (col): " + rj);

        for (int i = 0; i < numMines; i++) {
            // loop until finding a non-mine cell
            while (mines.get(ri, rj) == 1) {
                ri = (int) Math.floor(Math.random() * numRows);
                rj = (int) Math.floor(Math.random() * numCols);
            }

            // add a mine!
            mines.set(ri, rj, 1);
        }
    }
}

