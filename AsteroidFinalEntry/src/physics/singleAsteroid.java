package physics;

/**
 * TODO Description of the class
 * TODO References: With whom you discussed this assignment and what resources you used.
 *
 * @author Kyle
 * @version Created: Apr 10, 2013, Modified: date - description
 */
public class singleAsteroid {
  private boolean asteroidCreated;
  private World  world;
  private Asteroid asteroid;
  /** The x axis position of the ball. */
  private int x;
  /** The y axis position of the ball. */
  private int y;
  /** The speed of the ball on the x axis. */
  private int xSpeed;
  /** The speed of the ball on the y axis. */
  private int ySpeed;
  
  private int power;
//  private Bullet bullet;
//  public boolean collision = false;
  private int i = 0;
  private int d;

  
  public singleAsteroid(int x, int y, int xspeed, int yspeed, int power, boolean asteroidCreated) {
    this.x = x;                 //sets ball in the center of the board
    this.y = y;
    this.xSpeed = xspeed;             //set initial speed to 0
    this.ySpeed = yspeed;
    this.power = power;
    this.asteroidCreated = asteroidCreated;
  }

  public singleAsteroid() {
    this.x = 0;                 
    this.y = 0;
    this.xSpeed = 0;             
    this.ySpeed = 0;
    this.power = 0;
    this.asteroidCreated = false;
  }
  
  public boolean getStatus()
  {
    return asteroidCreated;
  }

  public void setStatus(boolean status)
  {
    this.asteroidCreated= status ;
  }
  
  public void individualUpdate() {    //update the speed of the Ball
    x += xSpeed;
    y += ySpeed;
    if (y < 0){

    if (x > 375){
    d = x - 375;
    x = 375 - d;
    }
    else{
    d = 375 - x;
    x = 375 + d;
    }
    y = 750; 
    }
    if (y > 750){

    if (x > 375){
    d = x - 375;
    x = 375 - d;
    }
    else{
    d = 375 - x;
    x = 375 + d;
    }
    y = 0;
    }
    if (x < 0){

    if (y > 375){
    d = y - 375;
    y = 375 - d;
    }
    else{
    d = 375 - y;
    y = 375 + d;
    }
    x = 750;
    }
    if (x > 750){

    if (y > 375){
    d = y - 375;
    y = 375 - d;
    }
    else{
    d = 375 - y;
    y = 375 + d;
    }
    x = 0;
    }

    
  }
  public int getX() {       //set a method to give the bullets x position when called
    return x;
  }


  public int getY() {       //set a method to give the bullets y position when called
    return y;
  }
  
  public int getPower(){
    return power;
  }

}
