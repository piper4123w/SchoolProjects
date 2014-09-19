package visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import physics.World;
import userInterface.Keyboard;

import physics.World;

/**
 * TODO Description of the class
 * TODO References: With whom you discussed this assignment and what resources you used.
 * 
 * @author Kyle
 * @version Created: Apr 26, 2013, Modified: date - description
 */
@SuppressWarnings("serial") public class AddStuff extends JPanel {

  World              world;
  GameFrame          frame;
  private int[]      shipArrayX;
  private int[]      shipArrayY;
  private int[]      alienShipArrayX;
  private int[]      alienShipArrayY;
  private int[]      livesArrayX;
  private int[]      livesArrayY;
  private int[]      livesArrayX2;
  private int[]      livesArrayY2;

  private JTextField nameField;

  private JPanel     panel;   //adds my inputs to the panes

  JCheckBoxMenuItem  chckbxmntmPanicMode = new JCheckBoxMenuItem("Hyper-Space Mode!");

  JCheckBoxMenuItem  chckbxmntmSpeed     = new JCheckBoxMenuItem("Increase Thrusters!!");

  public boolean     panicMode           = false;

  public boolean     start               = false;

  public AddStuff(World world) {
    this.world = world;
    shipArrayX = new int[3];
    shipArrayY = new int[3];
    alienShipArrayX = new int[6];
    alienShipArrayY = new int[6];
    livesArrayX = new int[3];
    livesArrayY = new int[3];
    livesArrayX2 = new int[3];
    livesArrayY2 = new int[3];

    chckbxmntmSpeed.setBackground(Color.green);
    chckbxmntmSpeed.setBounds(181, 0, 160, 24);
    add(chckbxmntmSpeed);

    chckbxmntmPanicMode.setBackground(Color.RED);
    chckbxmntmPanicMode.setBounds(0, 0, 119, 24);
    add(chckbxmntmPanicMode);
  }

  @Override public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (chckbxmntmPanicMode.isSelected()) {
      Random random = new Random();
      setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
    }
    else {
      setBackground(Color.black); // chance panel to this
    }
    if (chckbxmntmSpeed.isSelected()) {
      setDifficulty(6);
    }
    else {
      setDifficulty(1);
    }

    // setDifficulty(diffSlider.getValue());
    drawAsteroids(g);      // (x,y,xsize,ysize)
    drawShip(g);
    drawBullets(g);
    drawAlienBullets(g);
//    if (world.getAlienVisible() == true) {
      drawAlienShip(g);
//    }
    if (world.getPause() == true && world.checkLives() > 0) {
      displayPause(g);
    }
    // if (world.checkLives() < 0){
    // displayScores();
    // }
    displayLives(world.checkLives(), g);
    displayPoints(world.checkPoints(), g);
    newLevel(world.getLevelDisp(), g);
    // graphics.fillOval(20, 0, 20, 20);
    // frame.repaint();
  }

  public void displayScores(String scores, Graphics g) {
    g.setColor(Color.white);
    Font f = new Font("TimesRoman", Font.BOLD, 50);
    g.setFont(f);
    g.drawString("High Scores", 250, 150);
    Font l = new Font("TimesRoman", Font.PLAIN, 45);
    g.setFont(l);
    g.drawString(scores, 250, 60);
    // frame.repaint();
  }

  private void displayLives(int lives, Graphics g) {
    g.setColor(Color.white);
    livesArrayY[0] = 700;
    livesArrayX[0] = 715;
    livesArrayY[1] = 700;
    livesArrayX[1] = 695;
    livesArrayY[2] = 680;
    livesArrayX[2] = 705;

    livesArrayY2[0] = 700;
    livesArrayX2[0] = 695;
    livesArrayY2[1] = 700;
    livesArrayX2[1] = 675;
    livesArrayY2[2] = 680;
    livesArrayX2[2] = 685;
    if (lives == 3) {
      g.fillPolygon(livesArrayX, livesArrayY, 3);
      g.fillPolygon(livesArrayX2, livesArrayY2, 3);
    }
    if (lives == 2) {
      g.fillPolygon(livesArrayX2, livesArrayY2, 3);
    }
    if (lives == 0) {
      Font f = new Font("TimesRoman", Font.BOLD, 72);
      g.setFont(f);
      g.drawString("GAME OVER", 10, 100);
      Font d = new Font("Arial", Font.PLAIN, 40);
      g.setFont(d);
      g.drawString("Press 'R' to Restart", 10, 300);
      // displayScores();
    }
  }

  public void newLevel(boolean newLevel, Graphics g) {
    g.setColor(Color.white);
    if (newLevel) {
      Font f = new Font("Courier", Font.BOLD, 75);
      g.setFont(f);
      g.drawString("Level:" + world.getLevel(), 10, 100);
    }
  }

  private void displayPause(Graphics g) {
    g.setColor(Color.white);
    Font p = new Font("Arial", Font.PLAIN, 75);
    g.setFont(p);
    g.drawString("Pause", 275, 350);
  }

  private void displayPoints(int points, Graphics g) {
    g.setColor(Color.white);
    String stringPoints = Integer.toString(points);
    Font q = new Font("TimesRoman", Font.PLAIN, 25);
    g.setFont(q);
    g.drawString(stringPoints, 700, 25);
  }

  private void drawShip(Graphics g) {
    g.setColor(Color.blue);
    int shipX = world.getShipX();
    int shipY = world.getShipY();
    shipArrayY[0] = shipY - 10;
    shipArrayX[0] = shipX;
    shipArrayY[1] = shipY + 10;
    shipArrayX[1] = shipX - 10;
    shipArrayY[2] = shipY + 10;
    shipArrayX[2] = shipX + 10;
    g.fillPolygon(shipArrayX, shipArrayY, 3);
  }

  private void drawAlienShip(Graphics g) {
    g.setColor(Color.green);
    int shipX = world.getAlienShipX();
    int shipY = world.getAlienShipY();
    alienShipArrayY[0] = shipY + 12;
    alienShipArrayX[0] = shipX + 9;
    alienShipArrayY[1] = shipY;
    alienShipArrayX[1] = shipX + 15;
    alienShipArrayY[2] = shipY - 12;
    alienShipArrayX[2] = shipX + 9;
    alienShipArrayY[3] = shipY - 13;
    alienShipArrayX[3] = shipX - 9;
    alienShipArrayY[4] = shipY;
    alienShipArrayX[4] = shipX - 15;
    alienShipArrayY[5] = shipY + 12;
    alienShipArrayX[5] = shipX - 9;
    g.fillPolygon(alienShipArrayX, alienShipArrayY, 6);
  }

  private void drawBullets(Graphics g) {
    g.setColor(Color.yellow);
    int totalBullet = world.getBulletCount();
    int index = 0;
    while (index < totalBullet) {
      g.fillOval(world.getBulletX(index), world.getBulletY(index), 10, 10);
      index++;
    }
    index = 0;

  }
  private void drawAlienBullets(Graphics g) {
    g.setColor(Color.pink);
    int totalAlienBullet = world.getAlienBulletCount();
    int index = 0;
    while (index < totalAlienBullet) {
      g.fillOval(world.getAlienBulletX(index), world.getAlienBulletY(index), 10, 10);
      index++;
    }
    index = 0;

  }

  private void drawAsteroids(Graphics g) {
    for (int index = 0; index < world.getAsteroidCount(); index++) {
      g.setColor(Color.white);
      g.drawOval(world.getAsteroidX(index), world.getAsteroidY(index), world.getAsteroidPower(index) * 20,
          world.getAsteroidPower(index) * 20);

    }
  }

  private void setDifficulty(int diff) {
    world.setDifficulty(diff);
  }

}
