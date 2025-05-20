package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidSpawnerSystem implements IEntityProcessingService {

    private final Random random = new Random();
    private float spawnTimer   = 5f;   // first asteroid after 5 s
    private float interval     = 5f;   // current interval
    private final float minInt = 1.5f; // don’t go faster than this

    @Override
    public void process(GameData gameData, World world) {

        spawnTimer -= gameData.getDelta();
        if (spawnTimer > 0) return;


        int size = 1 + random.nextInt(3);// 1..3
        System.out.println("Asteroid spawned. Size: " + size + ". " +"interval: " + interval);
        Asteroid asteroid = new Asteroid(size);

        asteroid.setX(random.nextDouble() * gameData.getDisplayWidth());
        asteroid.setY(random.nextDouble() * gameData.getDisplayHeight());
        asteroid.setRotation(random.nextDouble() * 360);
        asteroid.setColor(Color.GREY);


        float radius = size * 3;
        asteroid.setRadius(radius);
        asteroid.setPolygonCoordinates(AsteroidPlugin.generateRandomPolygon(radius));

        world.addEntity(asteroid);

        // —— make next interval a bit shorter (difficulty ramp)
        interval = Math.max(minInt, interval - 0.2f);
        spawnTimer = interval;
    }
}
