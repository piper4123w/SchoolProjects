package physics;

import control.Direction;
import java.util.Random;

/**
 * Creates and commands the individual asteroids in the array
 * References: EECE 1610 class and myself.
 * 
 * @author kyle
 * @version Created: Mar 25, 2013, Modified: date - description
 */
public class Asteroid { // extends singleAsteroid {
  private World           world;
  private Asteroid        asteroid;
  /** The x axis position of the ball. */
  private int             x;
  /** The y axis position of the ball. */
  private int             y;
  /** The speed of the ball on the x axis. */
  private int             xAsteroidSpeed;
  /** The speed of the ball on the y axis. */
  private int             yAsteroidSpeed;
  // public boolean collision = false;
  private int             i             = 0;
  public singleAsteroid[] singleAsteroidArray;

  // private final int totalAsteroid = 10;

  private int             asteroidIndex = 0;
  private int             asteroidSet   = 3;
  private int             q             = 0;
  private int             difficulty = 0;
  private boolean         levelStart    = true;
  Random                  random        = new Random();

  public Asteroid() {
    // singleAsteroid[] singleAsteroidArray;
    // setAsteroidArray(new singleAsteroid[asteroidSet]); // creates the array for single asteroids
    this.singleAsteroidArray = new singleAsteroid[100];

  }

  public void update() {
    int ai = 0;
    x += xAsteroidSpeed;
    if (x >= 750) {          // if the ball hits the wall then it passes on other side
      x = 1;
    }
    if (x <= 0) {
      x = 750;
    }
    y += yAsteroidSpeed;
    if (y >= 750) {
      y = 1;
    }
    if (y <= 0) {
      y = 750;
      ai++;
    }

    for (int i = 0; i < asteroidIndex; i++) {
      singleAsteroidArray[i].individualUpdate();
    }
  }

  public void createAsteroids() {
    asteroidIndex = 0;
    while (asteroidIndex < asteroidSet) {
      x = ((int) (Math.random() * 750));          // set random initial asteroid position
      y = ((int) (Math.random() * 750));
      int xAsteroidSpeed = (random.nextInt(3) - 1);     // set random asteroid speed (constant)
      int yAsteroidSpeed = (random.nextInt(3) - 1);
      while (xAsteroidSpeed == 0 && yAsteroidSpeed == 0) {
        xAsteroidSpeed = (random.nextInt(3) - 1);
        yAsteroidSpeed = (random.nextInt(3) - 1);
      }
      int power = (random.nextInt(5) + 1) + difficulty;
      boolean isCreated = true;
      singleAsteroidArray[asteroidIndex] = new singleAsteroid(x, y, xAsteroidSpeed, yAsteroidSpeed, power, isCreated);
      asteroidIndex++;
    }
  }

  public void newLevel(int startSet) {
    asteroidSet = startSet + asteroidSet;
    asteroidSet++;
    asteroidIndex = 0;
    createAsteroids();
  }

  public int getAsteroidCount() {
    int asteroidCount = getAsteroidIndex();
    return asteroidCount;
  }

  public int getAsteroidIndex() {
    return asteroidIndex;
  }

  public int getAsteroidX(int index) {
    if (index < asteroidSet)
      return singleAsteroidArray[index].getX();
    // return getAsteroidArray()[index].getX();

    return -1;
  }

  public int getAsteroidY(int index) {
    if (index < asteroidSet)
      return singleAsteroidArray[index].getY();

    return -1;
  }

  public int getAsteroidPower(int index) {
    if (index < asteroidSet)
      return singleAsteroidArray[index].getPower();
    return -1;
  }

  public singleAsteroid[] getAsteroidArray() {
    return singleAsteroidArray;
  }

  public void setAsteroidArray(singleAsteroid[] singleAsteroidArray) {
    this.singleAsteroidArray = singleAsteroidArray;
  }

  public boolean getStatus(int index) {
    return getAsteroidArray()[index].getStatus();
  }

  public int setAsteroidSet() {
    return asteroidSet;
  }

  public boolean asteroidShot(boolean shot, int index) {
    if (shot) {
      int power = getAsteroidPower(index) - 1;
      if (power > 0) {
        int xAsteroidSpeed = (random.nextInt(3) - 1);     // set random asteroid speed (constant)
        int yAsteroidSpeed = (random.nextInt(3) - 1);
        while (xAsteroidSpeed == 0 && yAsteroidSpeed == 0) {
          xAsteroidSpeed = (random.nextInt(3) - 1);
          yAsteroidSpeed = (random.nextInt(3) - 1);
        }
        getAsteroidArray()[index] = new singleAsteroid((getAsteroidX(index) + ((int) (Math.random() * power))),
            (getAsteroidY(index) + ((int) (Math.random() * power))), xAsteroidSpeed, yAsteroidSpeed, power, true);
        // asteroidSet++;
        // world.setAsteroidSet(asteroidSet);
        // int xAsteroidSpeed1 = (int) (((Math.random() * 4) - 2)); // set random asteroid speed (constant)
        // int yAsteroidSpeed1 = (int) (((Math.random() * 4) - 2));
        // while (xAsteroidSpeed1 == 0 && yAsteroidSpeed1 == 0) {
        // xAsteroidSpeed1 = (int) (((Math.random() * 4) - 2));
        // yAsteroidSpeed1 = (int) (((Math.random() * 4) - 2));
        // }
        // getAsteroidArray()[asteroidSet] = new singleAsteroid((getAsteroidX(index)+ ((int)(Math.random() * power))),
        // (getAsteroidY(index) + ((int)(Math.random() * power))), xAsteroidSpeed1, yAsteroidSpeed1, power, true);
      }
      else {
        getAsteroidArray()[index] = new singleAsteroid(0, 0, 0, 0, 0, false);
      }
    }
    return false;
  }

  public void setDifficulty(int gameDif) {
    difficulty = gameDif;

  }
}
