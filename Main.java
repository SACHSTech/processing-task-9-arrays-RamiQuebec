import processing.core.PApplet;

/**
 * Main class to execute sketch
 * @author Rami Kabak
 * Array project to draw snowflakes on screen allowing user to press them to make them disappear
 * also adds movable player using keyPressed method and lives to make a collision game
 */
class Main {
  public static void main(String[] args) {
    
    String[] processingArgs = {"MySketch"};
	  Sketch mySketch = new Sketch();
	  PApplet.runSketch(processingArgs, mySketch);
  }
  
}