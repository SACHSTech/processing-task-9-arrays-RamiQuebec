import processing.core.PApplet;

public class Sketch extends PApplet {

  // Variables
  float[] circleY = new float[25];
  float[] circleX = new float[25];
  boolean[] ballHideStatus = new boolean[25];
  float speed = 1.0f;

  float playerX, playerY;
  int lives = 3;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // Loop setting up intial positions of snowflakes, ballHideStatus boolean to mark snowflakes as intitally visible
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }

    // Player Position
    playerX = 200;
    playerY = 380;
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Draw over the other objects
    background(255);

    // Draw the player lives on the top right of the screen
    playerLives();

    // Move snowflakes and check for collision with player, also checks ballHideStatus to make sure it doesnt draw hidden balls
    for (int i = 0; i < circleY.length; i++) {
      if (!ballHideStatus[i]) {
        ellipse(circleX[i], circleY[i], 30, 30);

        circleY[i] += speed;

        if (circleY[i] > height) {
          circleY[i] = 0;
          circleX[i] = random(width);
        }

        // Check collision with player and minus the lives variable to remove a life from the player
        if (dist(circleX[i], circleY[i], playerX, playerY) < 30) {
          lives--;
          circleY[i] = 0;
          circleX[i] = random(width);
        }
      }
    }

    // Draw and move player
    drawPlayer();
    
    // Check for game over
    if (lives <= 0) {
      background(255); // Clear the screen to white
    }
  }

  /**
   * keyPressed method containing if statements to check whether user presses WASD and boundaries
   * and then set the playerX and playerY variables to themselves + 20 to change the position of player
   */
  public void keyPressed() {
    if ((key == 'a' || key == 'A') && playerX > 0) {
      playerX -= 20;
    } else if ((key == 'd' || key == 'D') && playerX < width) {
      playerX += 20;
    } else if ((key == 'w' || key == 'W') && playerY > 0) {
      playerY -= 20;
    } else if ((key == 's' || key == 'S') && playerY < height) {
      playerY += 20;
    }
  }

  /**
   * mousePressed method, used for the if statement to check if the snowflakes have been pressed 
   * to set the ballHideStatus boolean to true to hide them
   */
  public void mousePressed() {
    // Check if mouse clicks on snowflakes to hide them
    for (int i = 0; i < circleY.length; i++) {
      if (!ballHideStatus[i] && dist(circleX[i], circleY[i], mouseX, mouseY) < 15) {
        ballHideStatus[i] = true;
      }
    }
  }

  /**
   * drawPlayer method, used to set player color and draw the circle player
   */
  void drawPlayer() {
    fill(0, 0, 0);
    ellipse(playerX, playerY, 30, 30);
  }

  /**
   * playerLives method, used to make lives red and position them at the top right of the screen 
   * ends with setting the fill to 255 to make the snowflakes white
   */
  void playerLives() {
    fill(255,0,0);
    for (int i = 0; i < lives; i++) {
      rect(width - 40, 20 + i * 30, 20, 20);
    }
    fill(255,255,255);
  }
}
