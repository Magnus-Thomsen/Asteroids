package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public final class AsteroidPlugin implements IGamePluginService {

    /**
     * Random library allows for random numbers for rotation and shape.
     */
    private static final Random RANDOM = new Random();

    @Override
    public void start(final GameData gameData, final World world) {
        int initialAsteroidsSpawn = 10;
        for (int i = 0; i < initialAsteroidsSpawn; i++) {
            Asteroid asteroid = new Asteroid(RANDOM.nextInt(3 - 1 + 1) + 1);
            asteroid.setX(RANDOM.nextDouble() * gameData.getDisplayWidth());
            asteroid.setY(RANDOM.nextDouble() * gameData.getDisplayHeight());
            //asteroid.setPolygonCoordinates(-10, -10, 10, 0, -10, 10);
            asteroid.setRotation(RANDOM.nextDouble() * 360);
            asteroid.setColor(Color.GRAY);
            asteroid.setPolygonCoordinates(
                    generateRandomPolygon(asteroid.getRadius()));
            world.addEntity(asteroid);
        }
    }

    /**
     * Method for generating a random set of polygon coordinates.
     * @param baseRadius radius of the asteroid.
     * @return a list of coordinates, used for creating a polygon.
     */
    public static double[] generateRandomPolygon(final float baseRadius) {
        int points = 8 + RANDOM.nextInt(4); // 8–11 points
        double[] coords = new double[points * 2];

        for (int i = 0; i < points; i++) {
            double angle = Math.toRadians((360.0 / points) * i);
            double variance = 0.7 + RANDOM.nextDouble(0.4); // ~0.7–1.1
            double r = baseRadius * variance;

            coords[i * 2] = Math.cos(angle) * r;
            coords[i * 2 + 1] = Math.sin(angle) * r;
        }

        return coords;
    }


    @Override
    public void stop(final GameData gameData, final World world) {
        world.getEntities().removeIf(e -> e instanceof Asteroid);
    }
}
