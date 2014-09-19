package Score;

import java.io.Serializable;

public class Score  implements Serializable {   //creates new score objects that can be accessed from other classes
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String naam, int score) {
        this.score = score;
        this.name = naam;
    }
}