package physics;

import java.util.Random;

// import userInterface.UI;
import control.Direction;

/**
 * Defines the physics of a ball in the game of pong.
 * References: EECE 1610 class and myself.
 * 
 * @author kyle
 * @version Created: Mar 25, 2013, Modified: date - description
 */
public class AlienShip extends Asteroid {
  private World          world;
  private Asteroid       asteroid;
  // private UI ui;
  /** The x axis position of the ship. */
  private int            x;
  /** The y axis position of the ship. */
  private int            y;
  /** The speed of the ship on the x axis. */
  private int            xSpeed;
  /** The speed of the ship on the y axis. */
  private int            ySpeed;
  private Bullet         bullet;
  // public boolean collision = false;
  private int            i           = 0;
  private singleAlienBullet[] alienBulletArray;

  private final int      totalAlienBullet = 10;

  private int            alienBulletIndex;

  private int            lives;
  private boolean deadAlien = false;
  private int            d;
  Random random = new Random();

  /**
   * 
   * Initializes the Ball to have position zero and velocity of 0;
   * 
   */
  public AlienShip() {
    x = 0;                 // sets ball in the center of the board
    y = 370;
    xSpeed = random.nextInt(3) - 1;            // set initial speed to 0
    ySpeed = random.nextInt(3) - 1;
    while (xSpeed == 0){
      xSpeed = random.nextInt(3) - 1;
    }
    setAlienBulletArray(new singleAlienBullet[totalAlienBullet]);
    setAlienBulletIndex(-1);
  }

  public void update() {    // update the speed of the Ball
    int chance = random.nextInt(100);
    if (chance == 1 && deadAlien == true){
      xSpeed = random.nextInt(3) - 1; 
      ySpeed = random.nextInt(3) - 1;
      world.setAlienVisible(true);
    }
    if (chance == 1){
      setAlienBulletIndex(getAlienBulletIndex() + 1);
      getalienBulletArray()[getAlienBulletIndex()] = new singleAlienBullet(x, y, 0, 3, true);
//      xSpeed = random.nextInt(5) - 2;            // set initial speed to 0
      ySpeed = random.nextInt(3) - 1;
    }
    x += xSpeed;
    y += ySpeed;
    if (y < 0) {

      if (x > 375) {
        d = x - 375;
        x = 375 - d;
      }
      else {
        d = 375 - x;
        x = 375 + d;
      }
      y = 750;
    }
    if (y > 750) {

      if (x > 375) {
        d = x - 375;
        x = 375 - d;
      }
      else {
        d = 375 - x;
        x = 375 + d;
      }
      y = 0;
    }
    if (x < 0) {

      if (y > 375) {
        d = y - 375;
        y = 375 - d;
      }
      else {
        d = 375 - y;
        y = 375 + d;
      }
      x = 750;
    }
    if (x > 750) {

      if (y > 375) {
        d = y - 375;
        y = 375 - d;
      }
      else {
        d = 375 - y;
        y = 375 + d;
      }
      x = 0;
//      alienShoot()
    }

    for (int bi = 0; bi <= getAlienBulletIndex(); bi++) {
      getalienBulletArray()[bi].update();

      if (!getalienBulletArray()[bi].getAbStatus()) {
        for (int bi1 = bi; bi1 < getAlienBulletIndex(); bi1++) {
          getalienBulletArray()[bi1] = getalienBulletArray()[bi1 + 1];
        }
        setAlienBulletIndex(getAlienBulletIndex() - 1);
        bi--;
      }

    }

  }

//  public boolean alienShipCollision(boolean collision) {
//    if (collision) {
//      killAlien(true);
//    }
//    return false;
//  }

  public void killAlien(boolean b) {
    System.out.println("dead Alien");
    deadAlien = b;
    // update();
  }

  public void updateDirection() {
    xSpeed = (int)(Math.random() * 2+1)*((int)Math.random()-1);
  }

  public int getAlienBulletCount() {
    return getAlienBulletIndex() + 1;
  }

  public int getAlienBulletX(int index) {
    if (index < totalAlienBullet)
      return getalienBulletArray()[index].getX();

    return -1;
  }

  public int getAlienBulletY(int index) {
    if (index < totalAlienBullet)
      return getalienBulletArray()[index].getY();

    return -1;
  }

  int[] xPosition;
  int[] yPosition;

  public int getX() {       // set a method to give the balls x position when called
    return x;
  }

  public int getY() {       // set a method to give the balls y position when called
    return y;
  }

  public singleAlienBullet[] getalienBulletArray() {
    return alienBulletArray;
  }

  public void setAlienBulletArray(singleAlienBullet[] alienBulletArray) {
    this.alienBulletArray = alienBulletArray;
  }

  public int getAlienBulletIndex() {
    return alienBulletIndex;
  }

  public void setAlienBulletIndex(int alienBulletIndex) {
    this.alienBulletIndex = alienBulletIndex;
  }

  /**
   * TODO Description of the method
   * 
   * @param b
   */

}
