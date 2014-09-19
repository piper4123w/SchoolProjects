package Score;

import java.util.*;
import java.io.*;

public class ScoreManager {
  private ArrayList<Score>    scores;

  private static final String HIGHSCORE_FILE = "scores.dat";

  ObjectOutputStream          outputStream   = null;
  ObjectInputStream           inputStream    = null;

  public ScoreManager() {
    scores = new ArrayList<Score>();
  }
  

  public ArrayList<Score> getScores() {   //array list of scores
    loadScoreFile();
    sortScores();
    return scores;
  }

  private void sortScores() {     //sorts scores
    ScoreComparator comparator = new ScoreComparator();
    Collections.sort(scores, comparator);
  }

  public void addScore(String name, int score) {      //Adds new scores
    loadScoreFile();
    scores.add(new Score(name, score));
    updateScoreFile();
  }

  public void loadScoreFile() {   //loads the score file
    try {
      inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
      scores = (ArrayList<Score>) inputStream.readObject();
    }
    catch (FileNotFoundException e) {
      System.out.println("File Not Found Error: " + e.getMessage());
    }
    catch (IOException e) {
      System.out.println("IO Error: " + e.getMessage());
    }
    catch (ClassNotFoundException e) {
      System.out.println("Class Not Found Error: " + e.getMessage());
    }
    finally {
      try {
        if (outputStream != null) {
          outputStream.flush();
          outputStream.close();
        }
      }
      catch (IOException e) {
        System.out.println("[Laad] IO Error: " + e.getMessage());
      }
    }
  }

  public void updateScoreFile() {   //updates score file
    try {
      outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
      outputStream.writeObject(scores);
    }
    catch (FileNotFoundException e) {
      System.out.println(e.getMessage() + "the program will make a file");    //makes a new file if none exists
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
    finally {
      try {
        if (outputStream != null) {
          outputStream.flush();
          outputStream.close();
        }
      }
      catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public String getHighscoreString() {      //returns top 10 scores
    String highscoreString = "";
    int max = 10;

    ArrayList<Score> scores;
    scores = getScores();

    int i = 0;
    int x = scores.size();
    if (x > max) {
      x = max;
    }
    while (i < x) {
      highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
      i++;
    }
    return highscoreString;
  }
}
