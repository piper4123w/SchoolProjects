package control;

import java.util.Scanner;


import physics.World;
import visuals.GameFrame;

/**
 * Test driver for class Ball.
 * References: Everybody.
 * 
 * @author richard
 * @version Created: Mar 25, 2013, Modified: date - description
 */
public class Control {
  /** Physics world for pong game */
  private World     world;
  private GameFrame gameFrame;
  
  public String name;

  // public Control control = new Control();

  public Control() {
    world = new World();                  // creates new level
    gameFrame = new GameFrame(world);

  }

  public static void main(String[] args) {     // main, starts the game
    Control control = new Control();
    control.run();
  }

  public void run() {
    (new Thread(world)).start();
    gameFrame.setVisible(true);
    (new Thread(gameFrame)).start();
  }

}
