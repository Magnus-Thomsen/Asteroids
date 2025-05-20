package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entities.player.IPlayer;
import javafx.scene.paint.Color;

public class Player extends Entity implements IPlayer{
    public Player() {
        this.setColor(Color.BLUEVIOLET);
        this.setHealth(100);
    }
}
