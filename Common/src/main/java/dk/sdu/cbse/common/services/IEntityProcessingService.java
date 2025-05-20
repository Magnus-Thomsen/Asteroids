package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Interface for entity processing services.
 * Implementing classes should define how game entities are updated each frame,
 * such as movement, logic, or behavior updates.
 */
public interface IEntityProcessingService {

    /**
     * Called once per game frame to process all relevant entities in the world.
     * This is typically used for movement, input handling, and basic game logic.
     *
     * @param gameData The current game data, containing input states and frame timing.
     * @param world The current world, containing all active game entities.
     */
    void process(GameData gameData, World world);
}
