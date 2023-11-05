package view;

import java.io.Serializable;

public class Score implements Serializable {
    private static final long serialVersionUID = -267512568560444990L;
    private String dateTime;
    private String playerName; 
    private int score;         
    // Constructor
    public Score(String dateTime, String playerName, int score) {
        this.dateTime = dateTime;
        this.playerName = playerName;
        this.score = score;
    }
    
    public Score() {
       
    }


   

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
