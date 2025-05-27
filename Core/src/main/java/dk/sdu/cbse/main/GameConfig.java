package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.util.ServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GameConfig {

    /**
     * Creates an instance of Game with all located services.
     * @return Game instance.
     */
    @Bean
    public Game game(){
        return new Game(gamePluginServices(), entityProcessingServices(), postEntityProcessingServices());
    }


    /**
     * Locates all instances of IGamePluginServices.
     * @return List of IGamePluginService implementations.
     */
    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLocator.INSTANCE.locateAll(IGamePluginService.class)
                .stream().toList();
    }

    /**
     * Locates all instances of entityProcessingServices.
     * @return List of entityProcessingServices implementations.
     */
    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(IEntityProcessingService.class)
                .stream().toList();
    }

    /**
     * Locates all instances of postEntityProcessingServices.
     * @return List of postEntityProcessingServices implementations.
     */
    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(IPostEntityProcessingService.class)
                .stream().toList();
    }
}
