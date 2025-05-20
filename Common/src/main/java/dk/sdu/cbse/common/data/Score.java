package dk.sdu.cbse.common.data;

public class Score {

    private int score = 0;

    public void add(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }

}
