import dk.sdu.cbse.common.services.IEntityProcessingService;

module Bullet {
    exports dk.sdu.cbse.bullet;
    requires Common;
    requires javafx.graphics;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
}