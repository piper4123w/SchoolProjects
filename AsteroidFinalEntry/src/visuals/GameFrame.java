package visuals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import physics.World;
import userInterface.Keyboard;

/**
 * TODO Description of the class
 * TODO References: With whom you discussed this assignment and what resources you used.
 * 
 * @author richard
 * @version Created: Apr 26, 2013, Modified: date - description
 */
public class GameFrame extends JFrame implements Runnable {
  

  private JPanel contentPane;
  private World  world;

  /**
   * Launch the application.
   */
//  public static void main(String[] args) {
//    World world = new World();
//    (new Thread(world)).start();
//    GameFrame frame = new GameFrame(world);
//    EventQueue.invokeLater(frame);
//  }

  public void run() {
//    setVisible(true);
    // while (true) {
    // System.out.println("tried");
    // Thread.sleep(1000);
    // repaint();
    // }

    while (true) {
//      System.out.println("tried");
      try {
        Thread.sleep(50);
      }
      catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      repaint();
    }
  }

  /**
   * Create the frame.
   */
  public GameFrame(World world) {
    this.world = world;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800, 800);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    Keyboard keyboard = new Keyboard(world);
    addKeyListener(keyboard);
    

    AddStuff panel = new AddStuff(world);

    // TestPanel panel = new TestPanel();
    contentPane.add(panel, BorderLayout.CENTER);
  }
  

}
