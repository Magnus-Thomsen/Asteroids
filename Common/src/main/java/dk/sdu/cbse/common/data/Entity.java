package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;
import javafx.scene.paint.Color;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private Color color;
    private float health = 100;
    private float damage = 25;
    private boolean recentlyHit = false;
    private float hitTimer = 0f;
    private boolean invincible = false;
    private float invincibilityTimer = 0f;
    private String ownerID = null;

    public void setOwnerID(String id) {
        this.ownerID = id;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }


    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }


    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setDamage(float damage){
        this.damage = damage;
    }

    public float getDamage() {
        return this.damage;
    }


    public void setRecentlyHit(boolean hit) {
        this.recentlyHit = hit;
        this.hitTimer = 0.2f; // Flash for 0.2 seconds
    }

    public boolean isRecentlyHit() {
        return recentlyHit;
    }

    public void updateHitTimer(float delta) {
        if (recentlyHit) {
            hitTimer -= delta;
            if (hitTimer <= 0) {
                recentlyHit = false;
            }
        }
    }

    public float getHitTimer() {
        return hitTimer;
    }

    public void setHitTimer(float hitTimer) {
        this.hitTimer = hitTimer;
    }


    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(float duration) {
        this.invincible = true;
        this.invincibilityTimer = duration;
    }

    public void updateInvincibility(float delta) {
        if (invincible) {
            invincibilityTimer -= delta;
            if (invincibilityTimer <= 0) {
                invincible = false;
                invincibilityTimer = 0;
            }
        }
    }

}
