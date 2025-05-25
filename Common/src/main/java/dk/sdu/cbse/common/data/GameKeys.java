package dk.sdu.cbse.common.data;

public final class GameKeys {

    /**
     * List of keys pressed.
     */
    private static boolean[] keys;

    /**
     * Used to detect when a key was just pressed,
     * rather than whether it is currently being held down.
     */
    private static boolean[] previousKeys;

    /**
     * Number of total possible keys.
     */
    private static final int NUM_KEYS = 4;

    /**
     * UP key int constant.
     */
    public static final int UP = 0;

    /**
     * LEFT key int constant.
     */
    public static final int LEFT = 1;

    /**
     * RIGHT key int constant.
     */
    public static final int RIGHT = 2;

    /**
     * SPACE key int constant.
     */
    public static final int SPACE = 3;

    /**
     * GameKeys contructor.
     */
    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        previousKeys = new boolean[NUM_KEYS];
    }

    /**
     * Updates the previouskeys to include the current keys pressed.
     */

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            previousKeys[i] = keys[i];
        }
    }

    /**
     * Used to change the status of a key.
     * @param k Key that is updated.
     * @param b Status of the key. True if pressed, False if not.
     */
    public void setKey(final int k, final boolean b) {
        keys[k] = b;
    }

    /**
     * Checks if a key is held down.
     * @param k Key to check.
     * @return Satus of key.
     */
    public boolean isDown(final int k) {
        return keys[k];
    }

    /**
     * Checks if a key was just pressed.
     * @param k Key to check.
     * @return Satus of key.
     */
    public boolean isPressed(final int k) {
        return keys[k] && !previousKeys[k];
    }

}
