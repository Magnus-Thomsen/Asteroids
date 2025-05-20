package dk.sdu.cbse.player;

//import dk.sdu.cbse.common.bullet.Bullet;
//import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

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
                Entity bullet = createBullet(player, gameData);
                world.addEntity(bullet);
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

    private Entity createBullet(Entity player, GameData gameData) {
        Bullet bullet = new Bullet();

        double radians = Math.toRadians(player.getRotation()); // <-- convert here
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        bullet.setX(player.getX() + cos * 20);     // nose-tip offset
        bullet.setY(player.getY() + sin * 20);
        bullet.setRotation(player.getRotation());   // keep rotation in degrees
        bullet.setDx(cos * bullet.getSpeed());
        bullet.setDy(sin * bullet.getSpeed());
        bullet.setRadius(6);
        bullet.setPolygonCoordinates(-8, -1.5, -8, 1.5, 8, 1.5, 8, -1.5);
        bullet.setOwnerID(player.getID());

        return bullet;
    }


}
