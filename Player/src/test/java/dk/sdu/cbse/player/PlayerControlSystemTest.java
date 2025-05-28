package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {
    private GameData gameData;
    private World world;
    private PlayerControlSystem playerControlSystem;
    private Entity player;

    @BeforeEach
    void setup(){
        gameData = new GameData();
        world = new World();
        playerControlSystem = new PlayerControlSystem();

        // Setup a player
        player = new Player();
        player.setX(100);
        player.setY(100);
        player.setRotation(0);

        world.addEntity(player);
        gameData.getKeys().setKey(GameKeys.LEFT, true);
    }

    @Test
    void testPlayerRotationLeft(){
        double initialRotation = player.getRotation();
        playerControlSystem.process(gameData, world);
        double newRotation = player.getRotation();
        assertTrue(newRotation < initialRotation, "Player should rotate left");
    }

}