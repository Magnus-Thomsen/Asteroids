module Common {
    requires javafx.graphics;
    requires spring.web;
    requires spring.beans;
    requires spring.core;
    exports dk.sdu.cbse.common.services;
    exports dk.sdu.cbse.common.data;
    exports dk.sdu.cbse.common.util;
    exports dk.sdu.cbse.common.entities.bullet;
    exports dk.sdu.cbse.common.entities.enemy;
    exports dk.sdu.cbse.common.entities.player;
    exports dk.sdu.cbse.common.entities.asteroid;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.common.entities.bullet.IBulletFactory;
}