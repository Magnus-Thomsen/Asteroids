package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public final class EnemySpawnerSystem implements IEntityProcessingService {

    /**
     * How long delay till the first enemies are spawned.
     */
    private float spawnTimer = 10f;   // first extra enemy after 10s

    /**
     * Delay for spawning additional enemies.
     */
    private float interval = 10f;

    @Override
    public void process(final GameData gameData, final World world) {

        spawnTimer -= gameData.getDelta();
        if (spawnTimer > 0) {
            return;
        }

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
