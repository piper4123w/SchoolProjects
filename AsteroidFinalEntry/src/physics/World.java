package physics;

import java.util.Scanner;

import visuals.AddStuff;

import Score.ScoreManager;

import control.Direction;

public class World implements Runnable {
  public boolean     collision    = false;
  private Ship       ship;
  private Asteroid   asteroid;
  private AlienShip  alienship;
  private AddStuff   addStuff;
  public int         asteroidSet  = 3;
  public boolean     asteroidShot = false;
  public boolean     start        = true;
  private int        lives        = 3;
  private static int points       = 0;
  private int        level        = 1;
  private boolean    levelDisp;
  private int        leftAlive    = 0;
  private int        timer        = 0;
  private boolean    pause        = true;
  private int        difficulty   = 1;
  private boolean alienVisible = true;
  private int        gameDif;
  private String     name;
  private String     highScoreString;

  public World() {    //creates a new world
    ship = new Ship();
    asteroid = new Asteroid();
    asteroid.createAsteroids();
    alienship = new AlienShip();
    // AddStuff addstuff;
  }

  public void updateDirection(Direction direction) {
    ship.updateDirection(direction);
  }

  public void shoot(Direction direction) {
    ship.shoot(direction);
  }

  public void run() {     //runs the world
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Acceleration Level: ");   //sets acceleration level to imput number
    int acceleration = scanner.nextInt();
    setAcceleration(acceleration);
    
    Scanner scanner3 = new Scanner(System.in);        //allows difficulty set
    System.out.println("Enter Difficulty: ");
    difficulty = scanner3.nextInt();
    
    Scanner scanner2 = new Scanner(System.in);
    System.out.println("Enter Starting Level: ");       //allows a starting level
    int StartLevel = scanner2.nextInt();
    level = StartLevel;
    asteroid.newLevel(level);

    while (true) {
     if (levelDisp) {       //endless update loop
        timer--;
      }
      if (timer <= 0) {
        timer = 1000;           //timer for level number display
        levelDisp = false;
      }
      ship.update();              // sets the ball
      asteroid.update();
      alienship.update();
      for (int index = 0; index <= getAsteroidCount(); index++) {
        if (getShipX() >= getAsteroidX(index) && getShipX() <= getAsteroidX(index) + (getAsteroidPower(index) * 20)
            && getShipY() >= getAsteroidY(index) && getShipY() <= getAsteroidY(index) + (getAsteroidPower(index) * 20)) {     //updeates for ship collision
          ship.shipCollision(true);   //checks if asteroid and ship collide
          lives--;
        }
      }
      
      for (int bulletIndex = 0; bulletIndex < getBulletCount(); bulletIndex++) {
        for (int asteroidIndex = 0; asteroidIndex < getAsteroidCount(); asteroidIndex++) {
          if (getBulletX(bulletIndex) >= getAsteroidX(asteroidIndex)
              && getBulletX(bulletIndex) <= getAsteroidX(asteroidIndex) + (getAsteroidPower(asteroidIndex) * 20)
              && getBulletY(bulletIndex) >= getAsteroidY(asteroidIndex)
              && getBulletY(bulletIndex) <= getAsteroidY(asteroidIndex) + (getAsteroidPower(asteroidIndex) * 20)) {
            asteroid.asteroidShot(true, asteroidIndex);
            points++;   //checks if bullets and asteroids collide
          }
        }
      }
      
      for (int bulletIndex = 0; bulletIndex < getBulletCount(); bulletIndex++){
        if (getBulletX(bulletIndex) >= getAlienShipX()-15 && getBulletX(bulletIndex) <= getAlienShipX() + 15 && getBulletY(bulletIndex) >= getAlienShipY()-15 && getBulletY(bulletIndex) <= getAlienShipY() + 15 ){
          System.out.println("alien recieves shot");    //checks if bullets and alien ship collide
//          alienship.killAlien(true);
        }
      }
      asteroid.setAsteroidSet();

      if (lives <= 0) {   //when all lives are lost
        setPause();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter Your Name:");
        String name = scanner1.nextLine();
        // String player = visuals.AddStuff.name.getText();
        ScoreManager sm = new ScoreManager();
        sm.addScore(name, checkPoints());   //adds scores list
        setHighScores(sm.getHighscoreString());
        System.out.println(sm.getHighscoreString());
        // graphics.displayScores(sm.getHighscoreString());
      }
      pause = getPause();   //pause button
      while (pause) {
        if (!getPause()) {
          pause = false;
        }
      }
      // pause = checkPauseSelection();

      leftAlive = 0;
      for (int index = 0; index < asteroid.getAsteroidIndex(); index++) {
        leftAlive = leftAlive + getAsteroidPower(index);
      }   //checks to see if any asteroids are left to change the level
      if (leftAlive == 0) {
        level++;
        asteroid.newLevel(level);
        levelDisp = true;
      }
      try {
        Thread.sleep(8 - difficulty);
      }

      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  private void setAcceleration(int acceleration) {
    ship.setShipAcceleration(acceleration);

  }

  public boolean reset() {    //resets ship
    ship.resetShip();
    lives = 3;
    points = 0;
    level = 1;
    asteroidSet = 3;
    setPause();
    return false;
  }

  public void setHighScores(String scores) {
    highScoreString = scores;
  }

  public String getHighScores() {
    return highScoreString;
  }

  public void pauseGo() {
    pause = getPause();
    while (pause = true) {
      pause = getPause();
    }
  }

  public void setPause() {
    if (pause == true) {
      pause = false;
    }
    else {
      pause = true;
    }
  }

  public boolean getPause() {
    return pause;
  }

  public void setReset() {
    reset();
  }

  public void setDifficulty(int diff) {
    difficulty = diff;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public int getShipX() {
    return ship.getX();
  }

  public int getShipY() {
    return ship.getY();
  }

  public int getAlienShipX() {
    return alienship.getX();
  }

  public int getAlienShipY() {
    return alienship.getY();
  }

  public int getAsteroidCount() {
    return asteroid.getAsteroidCount();
  }

  public int getAsteroidX(int index) {
    return asteroid.getAsteroidX(index);
  }

  public int getAsteroidY(int index) {
    return asteroid.getAsteroidY(index);
  }

  public int getAsteroidPower(int index) {
    return asteroid.getAsteroidPower(index);
  }

  public int getBulletCount() {
    return ship.getBulletCount();
  }

  public int getBulletIndex() {
    return ship.getBulletIndex();
  }

  public int getBulletX(int index) {
    return ship.getBulletX(index);
  }

  public int getBulletY(int index) {
    return ship.getBulletY(index);
  }
  
  public int getAlienBulletCount() {
    return alienship.getAlienBulletCount();
  }

  public int getAlienBulletIndex() {
    return alienship.getAlienBulletIndex();
  }

  public int getAlienBulletX(int index) {
    return alienship.getAlienBulletX(index);
  }

  public int getAlienBulletY(int index) {
    return alienship.getAlienBulletY(index);
  }

  public boolean getLevelDisp() {
    return levelDisp;
  }

  // public int getAsteroidX(int asteroidIndex) {
  // return asteroid.getxAsteroid(asteroidIndex);
  // }
  //
  // public int getAsteroidY(int asteroidIndex) {
  // return asteroid.getyAsteroid(asteroidIndex);
  // }
  //
  public boolean collision() {
    for (int index = 0; index < getAsteroidCount(); index++) {
      if (getShipX() >= getAsteroidX(index) && getShipX() <= getAsteroidX(index) + (getAsteroidPower(index) * 20)
          && getShipY() >= getAsteroidY(index) && getShipY() <= getAsteroidY(index) + (getAsteroidPower(index) * 20)) {
        collision = true;
      }
    }
    return collision;
  }

  public void resetShip() {
    ship.resetShip();
  }

  public int findAsteroidCount() {
    return getAsteroidCount();
  }

  public int checkLives() {
    return lives;
  }

  public int checkPoints() {
    return points;
  }

  public String getLevel() {
    String stringLevel = Integer.toString(level);
    return stringLevel;
  }

  public int getScore() {
    return points;
  }

  public void setName(String nam) {
    name = nam;
  }


  public void setAlienVisible(boolean b) {
    alienVisible = b;
    
  }

//  public boolean getAlienVisible() {
//    return alienVisible;
//  }

}
