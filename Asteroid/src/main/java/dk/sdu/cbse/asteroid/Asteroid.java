package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;

/**
 * Asteroid class that extends the Entity class.
 * Expanded to allow different size asteroids.
 */
public final class Asteroid extends Entity implements IAsteroid {

    /**
     * Defines the size of the asteroid. 3 = large, 2 = medium, 1 = small
     */
    private int size;

    /**
     * Constructor for Asteroid.
     * @param asteroidSize size of the asteroid.3 = large, 2 = medium, 1 = small
     */
    public Asteroid(final int asteroidSize) {
        this.size = asteroidSize;
        setRadius(10 * asteroidSize);
        setHealth(50 * asteroidSize);
        setDamage(10);
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int asteroidSize) {
        this.size = asteroidSize;
    }
}
