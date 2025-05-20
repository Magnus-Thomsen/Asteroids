package dk.sdu.cbse.common.data;

public class GameData {

    private int displayWidth = 1280;
    private int displayHeight = 720;
    private final GameKeys keys = new GameKeys();
    private float delta;
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
