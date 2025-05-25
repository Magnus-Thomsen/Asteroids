package dk.sdu.cbse.common.data;

public final class GameData {

    /**
     * Width of the gamescreen.
     */
    private int displayWidth = 1280;

    /**
     * Height of the gamescreen.
     */
    private int displayHeight = 720;

    /**
     * GameKeys initialization for possible gamekeys.
     */
    private final GameKeys keys = new GameKeys();

    /**
     * Used for updating deltatime.
     */
    private float delta;

    /**
     * Create instance of score. Used to keep score.
     */
    private final Score score = new Score();

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public Score getScore() {
        return score;
    }
}
