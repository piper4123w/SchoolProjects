package userInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import physics.Bullet;
import physics.World;
import control.Direction;

/**
 * TODO Description of the class
 * TODO References: With whom you discussed this assignment and what resources you used.
 * 
 * @author richard
 * @version Created: Mar 27, 2013, Modified: date - description
 */
public class Keyboard implements KeyListener {

  private World world;
//  private Bullet bullet;

  public Keyboard(World world) {
    this.world = world;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  @Override public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyChar()) {      //activates the updateDirection method with key hit
      case 'w':
        world.updateDirection(Direction.DOWN);
        break;
      case 's':
        world.updateDirection(Direction.UP);
        break;
      case 'a':
        world.updateDirection(Direction.LEFT);
        break;
      case 'd':
        world.updateDirection(Direction.RIGHT);
        break;
      case 'r':
        world.setReset();
        break;
      case 'p':
        world.setPause();
        break;
      case '8':
        world.shoot(Direction.shootUP);
        break;
      case '2':
        world.shoot(Direction.shootDOWN);
        break;
      case '4':
        world.shoot(Direction.shootLEFT);
        break;
      case '6':
        world.shoot(Direction.shootRIGHT);
        break;
      case '9':
        world.shoot(Direction.shootUPRIGHT);
        break;
      case '7':
        world.shoot(Direction.shootUPLEFT);
        break;
      case '3':
        world.shoot(Direction.shootDOWNRIGHT);
        break;
      case '1':
        world.shoot(Direction.shootDOWNLEFT);
        break;
      default:
        break;
    }
  }
  

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  @Override public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  @Override public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

}
