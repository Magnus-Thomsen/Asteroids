package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entities.asteroid.IAsteroid;


public class Asteroid extends Entity implements IAsteroid{
    private int size; // 3 = large, 2 = medium, 1 = small

    public Asteroid(int size) {
        this.size = size;
        setRadius(10 * size);
        setHealth(50 * size);
        setDamage(10);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
