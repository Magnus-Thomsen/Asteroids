package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.entities.bullet.IBullet;
import dk.sdu.cbse.common.data.Entity;
import javafx.scene.paint.Color;

public final class Bullet extends Entity implements IBullet {

    /**
     * The rate of change in the x-position.
     */
    private float dx;

    /**
     * The rate of change in the y-position.
     */
    private float dy;

    /**
     * The speed of the bullet.
     */
    private float speed;

    /**
     * How long the bullet should be present before it is removed.
     */
    private float lifeTime;

    /**
     * How long the bullet should be present before it is removed.
     */
    private String ownerID = null;

    /**
     * Bullet constructor.
     * Sets the speed, lifetime and color of the bullet.
     */
    public Bullet() {
        this.speed = 200f;      // default speed in pixels per second
        this.lifeTime = 4f;   // 1 second lifetime
        this.setColor(Color.RED);
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void decreaseLifeTime(float delta) {
        this.lifeTime -= delta;
    }
}
