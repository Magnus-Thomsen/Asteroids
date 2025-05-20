package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.entities.player.IPlayer;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import javafx.scene.paint.Color;

public class EnemySpawnerSystem implements IEntityProcessingService {

    private float spawnTimer = 10f;   // first extra enemy after 15 s
    private float interval = 10f;

    @Override
    public void process(GameData gameData, World world) {

        /* If the player is gone, stop spawning */
        boolean playerAlive = world.getEntities().stream()
                .anyMatch(e -> e instanceof IPlayer);
        if (!playerAlive) return;

        spawnTimer -= gameData.getDelta();
        if (spawnTimer > 0) return;

        /* spawn one enemy */
        Enemy e = new Enemy();
        e.setPolygonCoordinates(-10, -10, 10, 0, -10, 10);
        e.setX(Math.random() * gameData.getDisplayWidth());
        e.setY(Math.random() * gameData.getDisplayHeight());
        e.setRotation(Math.random() * 360);
        e.setRadius(8);

        world.addEntity(e);

        // don’t go faster than this
        float minInt = 1.5f;
        interval = Math.max(minInt, interval - 0.2f);
        spawnTimer = interval; // fixed interval – can also shrink over time
    }
}
