package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import javafx.scene.paint.Color;

import java.util.Random;

public final class AsteroidSpawnerSystem implements IEntityProcessingService {

    /**
     * Random library allows for sized asteroids.
     */
    private final Random random = new Random();

    /**
     * When first asteroid should spawn.
     */
    private float spawnTimer = 5f;

    /**
     * Current interval between asteroid spawns.
     */
    private float interval = 5f;

    /**
     * The minimal interval between asteroid spawns.
     */
    private final float minInterval = 1.5f;

    @Override
    public void process(final GameData gameData, final World world) {

        spawnTimer -= gameData.getDelta();
        if (spawnTimer > 0) {
            return;
        }


        int size = 1 + random.nextInt(3); // 1..3
        Asteroid asteroid = new Asteroid(size);

        asteroid.setX(random.nextDouble() * gameData.getDisplayWidth());
        asteroid.setY(random.nextDouble() * gameData.getDisplayHeight());
        asteroid.setRotation(random.nextDouble() * 360);
        asteroid.setColor(Color.GREY);


        float radius = size * 3;
        asteroid.setRadius(radius);
        asteroid.setPolygonCoordinates(
                AsteroidPlugin.generateRandomPolygon(radius));

        world.addEntity(asteroid);

        // —— make next interval a bit shorter (difficulty ramp)
        interval = Math.max(minInterval, interval - 0.2f);
        spawnTimer = interval;
    }
}
