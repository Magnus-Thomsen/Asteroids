package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entities.bullet.IBulletFactory;

public final class BulletFactory implements IBulletFactory {

    @Override
    public Entity createBullet(final Entity shooter, final GameData gameData) {
        Bullet bullet = new Bullet();

        double rad = Math.toRadians(shooter.getRotation());
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        bullet.setX(shooter.getX() + cos * 20);
        bullet.setY(shooter.getY() + sin * 20);
        bullet.setRotation(shooter.getRotation());
        bullet.setDx(cos * bullet.getSpeed());
        bullet.setDy(sin * bullet.getSpeed());
        bullet.setRadius(6);
        bullet.setPolygonCoordinates(-8, -1.5, -8, 1.5, 8, 1.5, 8, -1.5);
        bullet.setOwnerID(shooter.getId());

        return bullet;
    }
}