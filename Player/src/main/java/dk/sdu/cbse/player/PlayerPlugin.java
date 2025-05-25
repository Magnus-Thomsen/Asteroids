package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public final class PlayerPlugin implements IGamePluginService {

    /**
     * Player to be spawned.
     */
    private Entity player;

    /**
     * PlayerPlugin constructor.
     */
    public PlayerPlugin() {
    }

    @Override
    public void start(final GameData gameData, final World world) {

        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(final GameData gameData) {
        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(
                -8, -6,   // back left
                15, 0,    // nose
                -8, 6,    // back right
                -4, 0     // tail point
        );
        // Creates the player in the middle of the screen.
        playerShip.setX(gameData.getDisplayHeight() / 2);
        playerShip.setY(gameData.getDisplayWidth() / 2);
        playerShip.setRadius(8);
        return playerShip;
    }

    @Override
    public void stop(final GameData gameData, final World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
