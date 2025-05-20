package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.asteroid.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Asteroid.class)) {

            // Move forward
            double changeX = Math.cos(Math.toRadians(e.getRotation()));
            double changeY = Math.sin(Math.toRadians(e.getRotation()));
            e.setX(e.getX() + changeX * 0.5);
            e.setY(e.getY() + changeY * 0.5);

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
