package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;
import dk.sdu.cbse.common.entities.bullet.IBullet;
import dk.sdu.cbse.common.entities.player.IPlayer;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public final class CollisionSystem implements IPostEntityProcessingService {
    @Override
    public void process(final GameData gameData, final World world) {
        for (Entity a : world.getEntities()) {
            for (Entity b : world.getEntities()) {
                if (a.getClass() == b.getClass()) {
                    continue;
                }

                float dx = (float) (a.getX() - b.getX());
                float dy = (float) (a.getY() - b.getY());
                //Distance calculation based on pythagoras
                float distance = (float) Math.sqrt(dx * dx + dy * dy);

                if (distance < a.getRadius() + b.getRadius()) {
                    if (!a.isInvincible() && !b.isInvincible()) {
                        a.setHealth(a.getHealth() - b.getDamage());
                        b.setHealth(b.getHealth() - a.getDamage());

                        if (a instanceof IBullet) {
                            world.removeEntity(a.getId());
                        }
                        if (b instanceof IBullet) {
                            world.removeEntity(b.getId());
                        }

                        awardScore(gameData, world, a, b);

                        awardScore(gameData, world, b, a);

                        // Invincibility: 0.5 seconds of i-frames
                        a.setInvincible(0.5f);
                        b.setInvincible(0.5f);
                    }
                }

            }
        }
    }

    private void awardScore(final GameData gameData,
                            final World world,
                            final Entity a,
                            final Entity b) {
        if (a.getHealth() <= 0) {
            if (a instanceof IAsteroid
                    && b instanceof IBullet
                    && isPlayerBullet(b, world)) {
                gameData.getScore().add(10);
            } else if (!(a instanceof IBullet)
                    && b instanceof IBullet
                    && isPlayerBullet(b, world)) {
                gameData.getScore().add(25);
                world.removeEntity(a.getId());
            } else if (a instanceof IPlayer) {
                world.removeEntity(a.getId());
            }
        }
    }

    private boolean isPlayerBullet(final Entity bullet, final World world) {
        if (bullet.getOwnerID() == null) {
            return false;
        }

        // Check if the entity matches the bullet owner id
        // and if the entity is the player.
        return world.getEntities().stream()
                .anyMatch(e -> e.getId().equals(bullet.getOwnerID())
                        && e.getClass().getSimpleName().equals("Player"));
    }

}
