package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputProcessor {

    public static void setupInputHandling(Scene scene, GameData gameData) {
        scene.setOnKeyPressed(event -> handleKey(event, gameData, true));
        scene.setOnKeyReleased(event -> handleKey(event, gameData, false));
    }

    private static void handleKey(KeyEvent event, GameData gameData, boolean isPressed) {
        KeyCode code = event.getCode();

        if (code == KeyCode.UP) {
            gameData.getKeys().setKey(GameKeys.UP, isPressed);
        } else if (code == KeyCode.LEFT) {
            gameData.getKeys().setKey(GameKeys.LEFT, isPressed);
        } else if (code == KeyCode.RIGHT) {
            gameData.getKeys().setKey(GameKeys.RIGHT, isPressed);
        } else if (code == KeyCode.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, isPressed);
        }
    }
}
