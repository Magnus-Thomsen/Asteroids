package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private static final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            Asteroid asteroid = new Asteroid(random.nextInt(3-1+1)+1);
            asteroid.setX(random.nextDouble() * gameData.getDisplayWidth());
            asteroid.setY(random.nextDouble() * gameData.getDisplayHeight());
            //asteroid.setPolygonCoordinates(-10, -10, 10, 0, -10, 10);
            asteroid.setRotation(random.nextDouble() * 360);
            asteroid.setColor(Color.GRAY);
            asteroid.setPolygonCoordinates(generateRandomPolygon(asteroid.getRadius()));
            world.addEntity(asteroid);
        }
    }

    public static double[] generateRandomPolygon(float baseRadius) {
        int points = 8 + random.nextInt(4); // 8–11 points
        double[] coords = new double[points * 2];

        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians((360.0 / points) * i);
            double variance = 0.7 + random.nextDouble(0.4); // ~0.7–1.1
            double r = baseRadius * variance;

            coords[i * 2] = Math.cos(angle) * r;
            coords[i * 2 + 1] = Math.sin(angle) * r;
        }

        return coords;
    }



    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities().removeIf(e -> e instanceof Asteroid);
    }
}
