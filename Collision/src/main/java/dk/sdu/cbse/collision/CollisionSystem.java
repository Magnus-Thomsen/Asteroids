package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;
import dk.sdu.cbse.common.entities.bullet.IBullet;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity a : world.getEntities()) {
            for (Entity b : world.getEntities()) {
                if (a.getClass()==b.getClass()) {
                    continue;
                }

                float dx = (float) (a.getX() - b.getX());
                float dy = (float) (a.getY() - b.getY());
                float distance = (float) Math.sqrt(dx * dx + dy * dy); //Based on pythagoras

                if (distance < a.getRadius() + b.getRadius()) {
                    if (!a.isInvincible() && !b.isInvincible()) {
                        a.setHealth(a.getHealth() - b.getDamage());
                        b.setHealth(b.getHealth() - a.getDamage());

                        if (a instanceof IBullet) world.removeEntity(a.getID());
                        if (b instanceof IBullet) world.removeEntity(b.getID());

                        awardScore(gameData, world, a, b);

                        awardScore(gameData, world, b, a);


                        // Visual feedback
                        a.setRecentlyHit(true);
                        b.setRecentlyHit(true);

                        // Invincibility: 0.5 seconds of i-frames
                        a.setInvincible(0.5f);
                        b.setInvincible(0.5f);
                    }
                }

            }
        }
    }

    private void awardScore(GameData gameData, World world, Entity a, Entity b) {
        if (a.getHealth() <= 0) {
            if (a instanceof IAsteroid && b instanceof IBullet && isPlayerBullet(b, world)) {
                gameData.getScore().add(10);
            } else if (!(a instanceof IBullet) && b instanceof IBullet && isPlayerBullet(b, world)) {
                gameData.getScore().add(25);
                world.removeEntity(a.getID());
            }
        }
    }

    private boolean isPlayerBullet(Entity bullet, World world) {
        if (bullet.getOwnerID() == null) return false;

        return world.getEntities().stream()
                .anyMatch(e -> e.getID().equals(bullet.getOwnerID()) && e.getClass().getSimpleName().equals("Player"));
    }

}
