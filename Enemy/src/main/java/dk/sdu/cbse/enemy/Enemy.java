package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entities.enemy.IEnemy;
import javafx.scene.paint.Color;

public class Enemy extends Entity implements IEnemy {

    /**
     * Enemy constructor responsible for setting color and health.
     */
    public Enemy() {
        this.setColor(Color.YELLOWGREEN);
        this.setHealth(100);
    }

}
