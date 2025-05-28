package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.bullet.IBulletFactory;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.util.ServiceLocator;

import java.util.List;

public final class EnemyControlSystem implements IEntityProcessingService {

    private final List<IBulletFactory> bulletFactory =
            ServiceLocator.INSTANCE.locateAll(IBulletFactory.class);

    /**
     * Cooldown after enemy shoots.
     */
    private float shootCooldown = 0;

    @Override
    public void process(final GameData gameData, final World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            // Randomly change direction a bit
            e.setRotation(e.getRotation() + (Math.random() - 0.5) * 10);

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

            // Shoot bullets every X seconds
            shootCooldown -= gameData.getDelta();
            if (shootCooldown <= 0) {
                if (!bulletFactory.isEmpty()){
                    Entity bullet = bulletFactory.getFirst().createBullet(e, gameData);
                    world.addEntity(bullet);
                }
                // shoot every 1.5–2.5 seconds
                shootCooldown = 1.5f + (float) Math.random();
            }


        }

    }
}
