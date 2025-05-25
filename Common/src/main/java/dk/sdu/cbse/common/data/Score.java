package dk.sdu.cbse.common.data;

public final class Score {

    /**
     * Current score.
     */
    private int score = 0;

    /**
     * Add score.
     * @param amount Score amount to add.
     */
    public void add(final int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    /**
     * Resets the score.
     */
    public void reset() {
        score = 0;
    }

}
