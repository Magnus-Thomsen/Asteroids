package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;
import javafx.scene.paint.Color;

public class Entity implements Serializable {

    /**
     * Creates a random UUID.
     */
    private final UUID id = UUID.randomUUID();

    /**
     * List of coordinates used for drawing a polygon.
     */
    private double[] polygonCoordinates;

    /**
     * x-position of entity.
     */
    private double x;

    /**
     * y-position of entity.
     */
    private double y;

    /**
     * rotation of entity.
     */
    private double rotation;

    /**
     * radius of entity.
     */
    private float radius;

    /**
     * Color of entity.
     */
    private Color color;

    /**
     * entity health, default 100.
     */
    private float health = 100;

    /**
     * entity health, default 25.
     */
    private float damage = 25;

    /**
     * status for invincibility.
     */
    private boolean invincible = false;

    /**
     * current timer for invincibility.
     */
    private float invincibilityTimer = 0f;

    /**
     * Defines the owner of the entity.
     */
    private String ownerID = null;

    public void setOwnerID(String id) {
        this.ownerID = id;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getId() {
        return id.toString();
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


    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(float duration) {
        this.invincible = true;
        this.invincibilityTimer = duration;
    }

    public float getInvincibilityTimer() {
        return invincibilityTimer;
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
