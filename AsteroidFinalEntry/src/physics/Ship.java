package physics;

// import userInterface.UI;
import control.Direction;

/**
 * Defines the physics of a ball in the game of pong.
 * References: EECE 1610 class and myself.
 * 
 * @author kyle
 * @version Created: Mar 25, 2013, Modified: date - description
 */
public class Ship extends Asteroid {
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
  private int            i            = 0;
  private singleBullet[] bulletArray;

  private final int      totalBullet  = 10;

  private int            bulletIndex;
  private int            acceleration = 1;

  private int            lives;
  private int            d;

  /**
   * 
   * Initializes the Ball to have position zero and velocity of 0;
   * 
   */
  public Ship() {
    x = 375;                 // sets ball in the center of the board
    y = 375;
    xSpeed = 0;             // set initial speed to 0
    ySpeed = 0;
    setBulletArray(new singleBullet[totalBullet]);
    setBulletIndex(-1);
  }

  public void update() {    // update the speed of the Ball
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
    }

    for (int bi = 0; bi <= getBulletIndex(); bi++) {
      getBulletArray()[bi].update();

      if (!getBulletArray()[bi].getStatus()) {
        for (int bi1 = bi; bi1 < getBulletIndex(); bi1++) {
          getBulletArray()[bi1] = getBulletArray()[bi1 + 1];
        }
        setBulletIndex(getBulletIndex() - 1);
        bi--;
      }

    }

  }

  public boolean shipCollision(boolean collision) {
    if (collision) {
      resetShip();
    }
    return false;
  }

  // public boolean collisionCheck(int xAsteroid, int yAsteroid) {
  // int index = 0;
  // boolean collision = false;
  // while (index >= getAsteroidCount()) {
  // if (getAsteroidX(index) == x && getAsteroidY(index) == y) {
  // collision = true;
  // resetBall();
  // }
  // index++;
  // }
  // return collision;
  // }

  public void resetShip() {
    x = 375;                 // sets ball in the center of the board
    y = 375;
    xSpeed = 0;             // set initial speed to 0
    ySpeed = 0;
    // update();
  }

  public void updateDirection(Direction direction) {
    switch (direction) {      // updates the speed of the ball when a key is hit
      case UP:
        ySpeed += 1*acceleration;
        if (ySpeed > 3) {      // if the speed is greater than 6 return to 6
          ySpeed = 3;
        }
        break;
      case DOWN:
        ySpeed -= 1*acceleration;
        if (ySpeed < -3) {
          ySpeed = -3;
        }
        break;
      case RIGHT:
        xSpeed += 1*acceleration;
        if (xSpeed > 3) {
          xSpeed = 3;
        }
        break;
      case LEFT:
        xSpeed -= 1*acceleration;
        if (xSpeed < -3) {
          xSpeed = -3;
        }
        break;
      default:
        break;
    }
  }

  public void shoot(Direction direction) {
    switch (direction) {
      case shootUP:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, 0, -3, true);
        }
        break;
      case shootDOWN:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, 0, 3, true);
        }
        break;
      case shootRIGHT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, 3, 0, true);
        }
        break;
      case shootLEFT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, -3, 0, true);
        }
        break;
      case shootUPRIGHT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, 1, -1, true);
        }
        break;
      case shootUPLEFT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, -1, -1, true);
        }
        break;
      case shootDOWNRIGHT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, 1, 1, true);
        }
        break;
      case shootDOWNLEFT:
        if (getBulletIndex() < totalBullet - 1) {
          setBulletIndex(getBulletIndex() + 1);
          getBulletArray()[getBulletIndex()] = new singleBullet(x, y, -1, 1, true);
        }
        break;
      default:
        bullet.BulletInfo(0, 0, 0, 0);
        break;
    }
  }

  public int getBulletCount() {
    return getBulletIndex() + 1;
  }

  public int getBulletX(int index) {
    if (index < totalBullet)
      return getBulletArray()[index].getX();

    return -1;
  }

  public int getBulletY(int index) {
    if (index < totalBullet)
      return getBulletArray()[index].getY();

    return -1;
  }

  int[] xPosition;
  int[] yPosition;

  // public Bullet shoot(){
  // if (updateDirection (Direction shootUP))
  // return
  // }

  /**
   * TODO Description of the method
   * 
   * @return
   */
  public int getX() {       // set a method to give the balls x position when called
    return x;
  }

  public int getY() {       // set a method to give the balls y position when called
    return y;
  }

  public singleBullet[] getBulletArray() {
    return bulletArray;
  }

  public void setBulletArray(singleBullet[] bulletArray) {
    this.bulletArray = bulletArray;
  }

  public int getBulletIndex() {
    return bulletIndex;
  }

  public void setBulletIndex(int bulletIndex) {
    this.bulletIndex = bulletIndex;
  }

  public void setShipAcceleration(int accel) {
    acceleration = accel;
  }

}
