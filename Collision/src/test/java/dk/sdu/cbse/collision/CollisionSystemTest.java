package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;
import dk.sdu.cbse.common.entities.bullet.IBullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockBullet extends Entity implements IBullet {}
class MockAsteroid extends Entity implements IAsteroid {}


class CollisionSystemTest {
    private GameData gameData;
    private World world;
    private CollisionSystem collisionSystem;

    private Entity bullet;
    private Entity asteroid;

    @BeforeEach
    void setUp(){
        gameData = new GameData();
        world = new World();
        collisionSystem = new CollisionSystem();

        bullet = new MockBullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(5);
        bullet.setDamage(10);
        bullet.setHealth(1);

        asteroid = new MockAsteroid();
        asteroid.setX(100); // Close enough to collide (5+5 radius)
        asteroid.setY(100);
        asteroid.setRadius(5);
        asteroid.setDamage(0);
        asteroid.setHealth(10);

        world.addEntity(bullet);
        world.addEntity(asteroid);
    }

    @Test
    void testBulletHitsAsteroid() {
        int initialScore = gameData.getScore().getScore();

        collisionSystem.process(gameData, world);

        // Verify score remains unchanged (not destroyed)
        assertEquals(initialScore, gameData.getScore().getScore());

        // Bullet should be removed after collision
        assertFalse(world.getEntities().contains(bullet));
    }

}