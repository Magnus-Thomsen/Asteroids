package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Interface for game plugin services.
 * Implementing classes are responsible for initializing and cleaning up game entities or systems
 * when the game starts or stops.
 */
public interface IGamePluginService {

    /**
     * Called when the game or plugin is started.
     * This method should be used to create and add entities or initialize any necessary data.
     *
     * @param gameData The game data, which contains global game settings and state.
     * @param world The world object that holds all entities in the current game session.
     */
    void start(GameData gameData, World world);

    /**
     * Called when the game or plugin is stopped.
     * This method should be used to remove or clean up any entities or data created during start().
     *
     * @param gameData The game data, which contains global game settings and state.
     * @param world The world object that holds all entities in the current game session.
     */
    void stop(GameData gameData, World world);
}
