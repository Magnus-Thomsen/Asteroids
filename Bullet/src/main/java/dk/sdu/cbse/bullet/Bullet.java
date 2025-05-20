package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.entities.bullet.IBullet;
import dk.sdu.cbse.common.data.Entity;
import javafx.scene.paint.Color;

public class Bullet extends Entity implements IBullet {

    private float dx;
    private float dy;
    private float speed;
    private float lifeTime;

    public Bullet(){
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
