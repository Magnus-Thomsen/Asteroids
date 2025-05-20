package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin(){

    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-10, -10, 10, 0, -10, 10); // triangular shape
        // Creates the enemy at a random point.
        enemy.setX(Math.random() * gameData.getDisplayWidth());
        enemy.setY(Math.random() * gameData.getDisplayHeight());
        enemy.setRadius(8);
        enemy.setRotation(Math.random() * 360);
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
