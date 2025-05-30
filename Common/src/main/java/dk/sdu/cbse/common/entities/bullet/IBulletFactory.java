package dk.sdu.cbse.common.entities.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

/**
 * Factory service for creating bullets.
 */
public interface IBulletFactory {

    /**
     * @param shooter entity that owns the bullet
     * @param gameData for display sizes etc.
     * @return a fully initialised bullet entity
     */
    Entity createBullet(Entity shooter, GameData gameData);
}