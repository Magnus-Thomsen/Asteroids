package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.entities.bullet.IBulletFactory;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.util.ServiceLocator;

import java.util.List;


public final class PlayerControlSystem implements IEntityProcessingService {


    private final List<IBulletFactory> bulletFactory =
            ServiceLocator.INSTANCE.locateAll(IBulletFactory.class);

    @Override
    public void process(final GameData gameData, final World world) {

        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 2);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 2);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                if (!bulletFactory.isEmpty()){
                    Entity bullet = bulletFactory.getFirst().createBullet(player, gameData);
                    world.addEntity(bullet);
                }
            }

            if (player.getX() < 0) {
                player.setX(gameData.getDisplayWidth());
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(0);
            }

            if (player.getY() < 0) {
                player.setY(gameData.getDisplayHeight());
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(0);
            }


        }
    }


}
