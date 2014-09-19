package physics;

/**
 * creates and maintains alien bullets
 * 
 * @author Kyle
 * @version Created: Apr 9, 2013, Modified: date - description
 */
public class singleAlienBullet {   
  private boolean isCreated;
  private World  world;
  private Ship ship;
  /** The x axis position of the ball. */
  private int x;
  /** The y axis position of the ball. */
  private int y;
  /** The speed of the ball on the x axis. */
  private int xSpeed;
  /** The speed of the ball on the y axis. */
  private int ySpeed;
//  private Bullet bullet;
//  public boolean collision = false;
  private int i = 0;
  private int lifetime = 250;
  
  private int d;

  
  public singleAlienBullet(int x, int y, int xspeed, int yspeed, boolean status) {
    this.x = x;                 //sets bullets to shot
    this.y = y;
    this.xSpeed = xspeed;             //set initial speed to 0
    this.ySpeed = yspeed;
    this.isCreated = status;
  }

  public singleAlienBullet() {    //if no bullet exists no values
    this.x = 0;                 
    this.y = 0;
    this.xSpeed = 0;             
    this.ySpeed = 0;
    this.isCreated = false;
  }
  
  public boolean getAbStatus()
  {
    return isCreated;
  }

  public void setAbStatus(boolean status)
  {
    this.isCreated= status ;
  }
  
  public void update() {    //update the speed of the Bullet with kilne bottle
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
    lifetime--;
    if (lifetime == 0)
    {
      isCreated = false;
    }
    
  }
  public int getX() {       //set a method to give the bullets x position when called
    return x;
  }


  public int getY() {       //set a method to give the bullets y position when called
    return y;
  }

}
