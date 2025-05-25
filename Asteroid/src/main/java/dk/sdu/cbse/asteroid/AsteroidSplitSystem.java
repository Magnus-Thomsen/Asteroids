package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.scene.paint.Color;

import java.util.Random;

public final class AsteroidSplitSystem implements IPostEntityProcessingService {

    /**
     * Random library allows for random rotation and position of asteroids.
     */
    private final Random random = new Random();

    @Override
    public void process(final GameData gameData, final World world) {
        for (Entity e : world.getEntities()) {
            if (e instanceof IAsteroid && e.getHealth() <= 0) {
                Asteroid original = (Asteroid) e;

                if (original.getSize() > 1) {
                    for (int i = 0; i < 2; i++) {
                        Asteroid child = new Asteroid(original.getSize() - 1);
                        child.setX(original.getX() + random.nextInt(10) - 5);
                        child.setY(original.getY() + random.nextInt(10) - 5);
                        child.setRotation(random.nextDouble() * 360);
                        child.setColor(Color.GREY);
                        child.setPolygonCoordinates(
                                AsteroidPlugin.generateRandomPolygon(
                                        child.getRadius()));
                        world.addEntity(child);
                    }
                }

                world.removeEntity(original.getId());
            }
        }
    }
}
