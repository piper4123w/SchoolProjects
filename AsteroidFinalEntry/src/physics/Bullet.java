package physics;

import userInterface.Keyboard;
import control.Direction;

/**
 * TODO Description of the class
 * TODO References: With whom you discussed this assignment and what resources you used.
 * 
 * @author Kyle
 * @version Created: Apr 2, 2013, Modified: date - description
 */
public class Bullet {

  public int xBullet;
  /** The y axis position of the ball. */
  public int yBullet;
  /** The speed of the ball on the x axis. */
  public  int xBulletSpeed;
  /** The speed of the ball on the y axis. */
  public  int yBulletSpeed;
  public  int lifeSpan = 8;
  public  Ship ball;
  public int[] bulletxPosition = new int [50];
  public int[] bulletyPosition = new int [13];
  public int[][] bulletfinalPosition = new int [13][50];
  public int[] bulletxSpeed = new int [0];
  public int[] bulletySpeed = new int [0];
  public int i = 0;
  
  public void BulletInfo(int a, int b, int x, int y){
    xBulletSpeed = a;
    yBulletSpeed = b;
    xBullet = x;
    yBullet = y;
    bulletxPosition[i] = xBullet;
    bulletyPosition[i] = yBullet;
    bulletxSpeed[i] = xBulletSpeed;
    bulletySpeed[i] = yBulletSpeed;
    if (i > 9){
      i = 0;
    }
    else{
    i++;  
    }
    
//    updateBullets();
  }
  
  public void updateBullets(){
    int xspeed;
    int yspeed;
    int j = 0;
    while (j>=bulletxPosition.length){
      bulletxPosition[j] = bulletxPosition[j] + bulletxSpeed[j];
      bulletyPosition[j] = bulletyPosition[j] + bulletySpeed[j];
      j++;    
  }
    j = 0;
  }
  
  

    
  
  public void processCOllision() {
    
  }
  
  public int[][] BulletPosition(){
    return bulletfinalPosition;
  }
  
//  public void shootUp(int xspeed, int yspeed, int x, int y) {
//    xspeed = xBulletSpeed;
//    yspeed = yBulletSpeed;
//    x = xBullet;
//    y = yBullet;
    

  
//  private Ball ball;
//  private Keyboard  keyboard;
//  public char[][]  board;
//
//  /**
//   * 
//   * Initializes the Ball to have position zero and velocity of 0;
//   * 3456
//   */
//
//  
//  
//  public void fireBullet(Direction direction) {
//   int bulletx, bullety;
//    switch (direction) {
//      case shootUP:
//        new Bullet(0,9);
//        break;
//      case shootDOWN:
//        new Bullet(0,-9);
//        break;
//      case shootRIGHT:
//        new Bullet(9,0);
//        break;
//      case shootLEFT:
//        new Bullet(-9,0);
//        break;
//      default:
//        break;
//        
//        
//    }
//  }
////  public Bullet() {
////    xBullet = ball.getX();        //set random initial asteroid position
////    yBullet = ball.getY();
////    xBulletSpeed = 0;
////    yBulletSpeed = 0;
////  }
//
//  public Bullet(int k, int l){
//    xBullet = ball.getX();        //set random initial asteroid position
//    yBullet = ball.getY();
//    xBulletSpeed = k;
//    yBulletSpeed = l;
//  }
//
//  }
//  //
//  // /**
//  // *
//  // * Updates the physics of the ball.
//  // *
//  // */
////  public void update() {
////    xBullet += xBulletSpeed;    //update the Asteroid position
////    if (xBullet >= 50){
////      xBullet = 1;
////  }
////    if (xBullet <= 0){            //if the asteroid hits the left or right wall
////      xBullet = 49;               //return on the opposite side
////    }
////  
////    xBullet += yBulletSpeed;
////    if (xBullet >= 13){
////      xBullet = 1;
////  }
////    if (xBullet <= 0){            //if the asteroid hits the top or bottom wall
////      xBullet = 12;               //return on the opposite side
////  }
//// }
////
////  public int getAsteroidX() {       //method to give the x position for asteroid when called
////    int x = xBullet;
////    return x;
////  }
////
////  public int getAsteroidY() {       //method to give the y position for asteroid when called
////    int y = xBullet;
////    return y;
////  }
//}
}