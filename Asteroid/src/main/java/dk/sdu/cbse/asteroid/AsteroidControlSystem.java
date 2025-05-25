package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

/**
 * AsteroidControlSystem is responsible for moving the asteroids.
 * Making them float around.
 */
public final class AsteroidControlSystem implements IEntityProcessingService {

    /**
     * Affects the speed of the asteroids.
     */
    private final double velocity = 0.5;

    @Override
    public void process(final GameData gameData, final World world) {
        for (Entity e : world.getEntities(Asteroid.class)) {



            // Move forward
            double changeX = Math.cos(Math.toRadians(e.getRotation()));
            double changeY = Math.sin(Math.toRadians(e.getRotation()));
            e.setX(e.getX() + changeX * velocity);
            e.setY(e.getY() + changeY * velocity);

            // Wrap around screen
            if (e.getX() < 0) {
                e.setX(gameData.getDisplayWidth());
            }

            if (e.getX() > gameData.getDisplayWidth()) {
                e.setX(0);
            }

            if (e.getY() < 0) {
                e.setY(gameData.getDisplayHeight());
            }

            if (e.getY() > gameData.getDisplayHeight()) {
                e.setY(0);
            }



        }

    }
}
