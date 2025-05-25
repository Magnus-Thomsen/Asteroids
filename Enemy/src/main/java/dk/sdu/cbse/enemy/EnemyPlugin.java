package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public final class EnemyPlugin implements IGamePluginService {

    /**
     * Initial enemy to spawn.
     */
    private Entity enemy;

    /**
     * EnemyPlugin constructor.
     */
    public EnemyPlugin() {

    }

    @Override
    public void start(final GameData gameData, final World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(final GameData gameData) {
        Entity enemy = new Enemy();
        // triangular shape
        enemy.setPolygonCoordinates(-10, -10, 10, 0, -10, 10);
        // Creates the enemy at a random point.
        enemy.setX(Math.random() * gameData.getDisplayWidth());
        enemy.setY(Math.random() * gameData.getDisplayHeight());
        enemy.setRadius(8);
        enemy.setRotation(Math.random() * 360);
        return enemy;
    }

    @Override
    public void stop(final GameData gameData, final World world) {
        world.removeEntity(enemy);
    }
}
