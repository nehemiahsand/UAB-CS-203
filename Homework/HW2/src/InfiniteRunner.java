import bridges.games.NonBlockingGame;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;

import java.util.ArrayList;

public class InfiniteRunner extends NonBlockingGame {

  // / the game map.
  static int rows = 24;
  static int cols = 24;

  java.util.Random random = new java.util.Random(System.currentTimeMillis());

  ArrayList<Node> nodes;
  ArrayList<Node> freeQueue;

  // The vertical point used as the floor
  int floorPos = rows - 5;

  //TODO: Declare and initialize integers to store points and a timer
  // both should be set to 0

  //MY COMMENT: Create two variables named points and timer
  int points = 0, timer = 0;


  // TODO: Declare and initialize an integer for lives
  //  How many times the player get hit by a bomb - up to you

  //MY COMMENT: Create a variable named lives and set it equal to 4
  int lives = 4;


  NamedSymbol numberList[] = {NamedSymbol.zero, NamedSymbol.one, NamedSymbol.two, NamedSymbol.three, NamedSymbol.four, NamedSymbol.five, NamedSymbol.six, NamedSymbol.seven, NamedSymbol.eight, NamedSymbol.nine};

  //framecontrol
  int frame = 0;

  // Used for safely ending the game
  boolean queueGameOver = false;
  boolean gameOver = false;

  public static void main(String args[]) {
    InfiniteRunner ir = new InfiniteRunner(13, "ntsander83758", "254082827329", cols, rows);
  }

  public InfiniteRunner(int assid, String login, String apiKey, int c, int r) {
    super(assid, login, apiKey, c, r);
    setTitle("Infinite Runner");
    setDescription("Press Q or q to restart, Up arrow to jump. Jump to avoid the bombs.");
    start();
  }

  public void gameLoop() {
	//TODO: Increment frame by one
    //MY COMMENT: Add one to the frame variable
    frame++;
	//if frame is divisible by four, invoke the process method
    //MY COMMENT: Use a for loop to check if frame is divisible by 4 using modulus
    if(frame % 4 == 0)
      process();
  }

  /**
   * @param pos
   * @return The nodes at pos
   */
  public ArrayList<Node> nodesAt(Vector2 pos) {
    ArrayList<Node> na = new ArrayList<Node>();
    for (Node n : nodes) {
      if (n.position.equals(pos)) {
        na.add(n);
      }
    }
    return na;
  }

  @Override
  public void initialize() {
    //TODO: make sure the number of points is set to 0
    //MY COMMENT: Reset the points variable
    points = 0;
    gameOver = false;
    nodes = new ArrayList<Node>();
    freeQueue = new ArrayList<Node>();

    nodes.add(new Player(new Vector2(3, rows / 2)));

    for (int x = 0; x < cols; x++) {
      for (int y = floorPos; y < rows; y++) {
        nodes.add(new Floor(new Vector2(x, y)));
      }
    }
  }

  private void draw() {
    // Clear the screen
	//TODO: Loop through the columns and the rows (hint: nested loop)
	//TODO: Inside the loop,
    //MY COMMENT: Loop through the length of the columns using a for loop
    for (int x = 0; x < cols; x++) {
      //MY COMMENT: Loop through the length of the rows using a for loop
      for (int y = 0; y < rows; y++) {
        //set a Vector2 object to a new Vector2 object (call it nv) passing the constructor the current column and row based on the loop
        //MY COMMENT: Create a new object from the vector2 class named nv
        Vector2 nv = new Vector2(x, y);
        //set the background color of nv.y, nv.x to a color (start with cadetblue)
        //MY COMMENT: Set the background color of the object's position to sky blue
        setBGColor(nv.y, nv.x, NamedColor.skyblue);
        //draw a symbol at nv.y, nv.x, no symbol, color set to white
        //MY COMMENT: Draw the symbol at the object's position and set it to white
        drawSymbol(nv.y, nv.x, NamedSymbol.none , NamedColor.white);
      }
    }


    // Draw the nodes based on their position and symbol/color
    // TODO: Loop through the ArrayList nodes
    //MY COMMENT: Use a for each loop to loop through the arrayList
    for (Node n : nodes) {
      //if the color of the current node is not null, set the background color
      //of the current node's position.y and the current node's position.x to the current node's color
      //MY COMMENT: If the node's color is not null then set the background color to that color
      if (n.color != null) {
        setBGColor(n.position.y, n.position.x, n.color);
      }
      //if the symbol of the current node is not null
      /* MY COMMENT: If the node's symbol and symbol color do not contain null then draw a symbol of the color in the node,
      else draw a symbol that has the color white */
      if (n.symbol != null) {
        //if the node's symbolColor is not null, draw a symbol at the node's position.y, position.x, set to the node's symbolColor
        if (n.symbolColor != null) {
          drawSymbol(n.position.y, n.position.x, n.symbol, n.symbolColor);
        }
        // else draw a symbol at node's position.y, position.x, the node's symbol, and a color, start with white
        else {
          drawSymbol(n.position.y, n.position.x , n.symbol, NamedColor.white);
        }
      }
    }






    Vector2 pointTopRight1 = new Vector2(cols - 1, 0);
    Vector2 pointTopRight2 = new Vector2(cols - 2, 0);
    Vector2 pointTopRight3 = new Vector2(cols - 3, 0);
    
    // Draw points in top right
    setBGColor(pointTopRight1.y, pointTopRight1.x, NamedColor.white);
    setBGColor(pointTopRight2.y, pointTopRight2.x, NamedColor.white);
    setBGColor(pointTopRight3.y, pointTopRight3.x, NamedColor.white);

    // Draw the numbers
    drawSymbol(pointTopRight1.y, pointTopRight1.x, numberList[(points % 10)], NamedColor.black);
    drawSymbol(pointTopRight2.y, pointTopRight2.x, numberList[(points % 100) / 10], NamedColor.black);
    drawSymbol(pointTopRight3.y, pointTopRight3.x, numberList[(points % 1000) / 100], NamedColor.black);

    // Draw the player's lives as hearts
    //TODO: Loop through the number of lives you gave the player at the beginning
    //MY COMMENT: Use a for loop to loop through the length of lives
    for (int i = 0; i < lives; i++) {
      //MY COMMENT: Create a row of objects that are drawn as hearts
      Vector2 symbol = new Vector2(i+1, 1);
      drawSymbol(1, i+1, NamedSymbol.heart, NamedColor.red);
    }
    //Create a new Vector2 object passing the position you want to display the hearts
    //for example, the first heart would be drawn at (1,1), the second would be drawn at (2,1)
    //draw the symbol at your Vector2 object's y, x, the symbol you want and the color you want
    //NamedSymbol.heart and NamedColor.darkred are suggestions

  }

  // Returns state of the up key to the player class
  public boolean checkKeyUp() {
    return keyUp();
  }

  public void process() {
    // If the game is queued to end display the game over screen and end the game
    if (queueGameOver) {
    //TODO: Loop through the columns and the rows
      //MY COMMENT: Loop through using two for loops that are nested
      for (int x = 0; x < cols; x++) {
        for (int y = 0; y < rows; y++) {
          Vector2 object1 = new Vector2(x, y);
          setBGColor(y, x, NamedColor.black);
          drawSymbol(y, x, NamedSymbol.none, NamedColor.white);
        }
      }
    //Set a new Vector2 object passing it the current column and row
    //Set the background color of the Vector2 object's y, x to black
    //Draw a symbol at the Vector2 object's y, x, NamedSymbol.none, NamedColor.white


      String go = "Game Over";
      int y = rows / 2;
      int x_offset = cols / 2 - go.length() / 2;
      for (int x = 0; x < go.length(); x++) {
        Vector2 newVector = new Vector2(x_offset + x, y);
        switch (x) {
          case 0:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.G, NamedColor.white);
            break;
          case 1:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.a, NamedColor.white);
            break;
          case 2:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.m, NamedColor.white);
            break;
          case 3:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.e, NamedColor.white);
            break;
          case 5:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.O, NamedColor.white);
            break;
          case 6:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.v, NamedColor.white);
            break;
          case 7:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.e, NamedColor.white);
            break;
          case 8:
            drawSymbol(newVector.y, newVector.x, NamedSymbol.r, NamedColor.white);
            break;
        }
      }

      gameOver = true;
      queueGameOver = false;
    }

    // If its over restart if P is pressed otherwise just return
    if (gameOver) {
      if (keyQ()) {
        start();
        quit();
      }
      return;
    }

    // Every 20 processes spawn the next wave
    //TODO: Increment your timer variable by 1
    //MY COMMENT: Add one to the timer variable
    timer += 1;
    //TODO: if your timer is greater than 20
    //MY COMMENT: Use an if statement (obviously)
    if (timer > 20) {
      // Spawn fruit and bombs
      if (random.nextFloat() < 0.5) {
        if (random.nextFloat() < 0.5) {
          nodes.add(new Bomb(new Vector2(cols, floorPos - 1)));
        }
        else {
          nodes.add(new Bomb(new Vector2(cols, floorPos - 2)));
          nodes.add(new Apple(new Vector2(cols, floorPos - 4)));
        }
      } // Spawn bomb
      else {
        nodes.add(new Apple(new Vector2(cols, floorPos - (1 + random.nextInt(2)))));
      }
      //TODO: Reset the timer variable to 0
      //MY COMMENT: Set the timer variable back to 0
      timer = 0;
    }

    // Call update on all nodes
  	//TODO: Loop through the arraylist called nodes
    //MY COMMENT: Use a for each loop to get each element
    for (Node n : nodes)
      //MY COMMENT: Manipulate each node in the arraylist
      //Call update(this) on each node in the arraylist
      n.update(this);

    // Delete nodes queued for deletion
    for (Node n : freeQueue) {
      nodes.remove(nodes.indexOf(n));
    }
    freeQueue.clear();

    // Check for game over
    if (lives <= 0) {
      queueGameOver = true;
    }

    //TODO: Invoke the draw method
    //MY COMMENT: Call the draw method
    draw();
  }
}

// Represents a base object on the grid
abstract class Node {

  public Vector2 position;
  public NamedColor color;
  public NamedSymbol symbol;
  public NamedColor symbolColor;

  public Node(Vector2 position) {
    this.position = position;
  }

  abstract public void update(InfiniteRunner ss);
}

class Player extends Node {

  java.util.Random random = new java.util.Random(System.currentTimeMillis());

  int gravity = 1;
  Vector2 velocity = new Vector2(0, 0);

  public Player(Vector2 position) {
    super(position);
    symbol = random.nextFloat() < 0.5 ? NamedSymbol.giraffe : NamedSymbol.elephant;
    symbolColor = NamedColor.black;
  }

  public boolean onFloor(InfiniteRunner ss) {
    ArrayList<Node> below = ss.nodesAt(Vector2.add(position, new Vector2(0, 1)));
    for (Node n : below) {
      if (n instanceof Floor) {
        return true;
      }
    }
    // Add tolerance for when player is very close to the floor
    return position.y >= ss.floorPos - 1;
  }

  @Override
  public void update(InfiniteRunner ss) {
    if (velocity.y < 1) {
      velocity.y += gravity;
    }

    if (onFloor(ss) && ss.checkKeyUp()) {
      velocity = new Vector2(0, -3);
    }

    Vector2 newPos = Vector2.add(position, velocity.sign());
    ArrayList<Node> na = ss.nodesAt(newPos);

    boolean collision = false;
    for (Node n : na) {
      if (n instanceof Floor) {
        collision = true;
        break;
      }
    }

    if (!collision) {
      position = newPos;
    }
  }
}

class Floor extends Node {

  public Floor(Vector2 position) {
    super(position);
    //TODO: set color to your color of choice, ex: NamedColor.darkgreen
    //MY COMMENT: Set the color of the ground to green
    color = NamedColor.saddlebrown;
  }

  @Override
  public void update(InfiniteRunner ss) {
  }
}

class Bomb extends Node {

  public Bomb(Vector2 position) {
    super(position);
    symbol = NamedSymbol.bomb;
    symbolColor = NamedColor.black;
  }

  @Override
  public void update(InfiniteRunner ss) {
    if (position.x > 0) {
      position.x -= 1;
    }
    else {
      ss.freeQueue.add(this);
    }

    ArrayList<Node> na = ss.nodesAt(position);
    boolean collision = false;
    for (Node n : na) {
      if (n instanceof Player) {
        collision = true;
        break;
      }
    }

    //TODO: Reduce lives
    //If there is a collision, reduce ss's lives by one
    //MY COMMENT: Don't need curly brackets because it is only one line of code
    if(collision)
      ss.lives--;
  }
}

class Apple extends Node {

  public Apple(Vector2 position) {
    super(position);
    //TODO: Set symbol to the symbol of your choice, ex: NamedSymbol.apple
    //MY COMMENT: Set the variable symbol to a bug
    symbol = NamedSymbol.bug1;
    //TODO: Set the symbolColor to the color of your coice: ex: darkred
    //MY COMMENT: Set the symbol color variable to green
    symbolColor = NamedColor.green;
  }

  @Override
  public void update(InfiniteRunner ss) {
	//TODO: move the object
    //MY COMMENT: Use an if statement to check if the x value of position is greater than 0
    if (position.x > 0) {
      position.x--;
    }
	//If the position.x is more than 0, decrement the value by 1

    else {
      ss.freeQueue.add(this);
    }

    ArrayList<Node> na = ss.nodesAt(position);
    boolean collision = false;
    for (Node n : na) {
      if (n instanceof Player) {
        collision = true;
        break;
      }
    }

    //TODO: Increment points if there is a collision
    //If collision is true, increase ss's points by 10
    //MY COMMENT: I don't know how else to comment this, it is very self-explanatory by the given comments
    if (collision) {
      ss.points += 10;
      //add(this) to ss's freeQueue
      ss.freeQueue.add(this);
    }
  }
}
