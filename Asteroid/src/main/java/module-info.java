import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Asteroid {
    requires Common;
    requires javafx.graphics;
    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides IPostEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidSplitSystem;
    provides IEntityProcessingService
            with dk.sdu.cbse.asteroid.AsteroidControlSystem,
            dk.sdu.cbse.asteroid.AsteroidSpawnerSystem;
}