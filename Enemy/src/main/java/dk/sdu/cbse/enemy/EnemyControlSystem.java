package dk.sdu.cbse.enemy;

import dk.sdu.cbse.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    private float shootCooldown = 0;

    @Override
    public void process(GameData gameData, World world) {
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
                world.addEntity(createBullet(e, gameData));
                shootCooldown = 1.5f + (float) Math.random(); // shoot every 1.5–2.5 seconds
            }


        }

    }

    private Entity createBullet(Entity enemy, GameData gameData) {
        Bullet bullet = new Bullet();

        double radians = Math.toRadians(enemy.getRotation()); // <-- convert here
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        bullet.setX(enemy.getX() + cos * 20);     // nose-tip offset
        bullet.setY(enemy.getY() + sin * 20);
        bullet.setRotation(enemy.getRotation());   // keep rotation in degrees
        bullet.setDx(cos * bullet.getSpeed());
        bullet.setDy(sin * bullet.getSpeed());
        bullet.setRadius(6);
        bullet.setPolygonCoordinates(-8, -1.5, -8, 1.5, 8, 1.5, 8, -1.5);

        return bullet;
    }
}
