package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.data.Entity;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        double dt = gameData.getDelta();                 // seconds since last frame

        for (Entity e : world.getEntities(Bullet.class)) {
            Bullet b = (Bullet) e;

            b.setX(b.getX() + b.getDx() * dt);
            b.setY(b.getY() + b.getDy() * dt);

            if (b.getX() < 0) {
                b.setX(gameData.getDisplayWidth());
            }
            else if (b.getX() > gameData.getDisplayWidth()) {
                b.setX(0);
            }

            if (b.getY() < 0){
                b.setY(gameData.getDisplayHeight());
            }
            else if (b.getY() > gameData.getDisplayHeight()) {
                b.setY(0);
            }

            b.decreaseLifeTime((float) dt);
            if (b.getLifeTime() <= 0) {
                world.removeEntity(b.getId());
            }
        }
    }
}
