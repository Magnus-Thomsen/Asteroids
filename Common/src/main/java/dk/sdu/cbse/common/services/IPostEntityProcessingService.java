package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Interface for post-entity processing services.
 * This interface is intended for logic that should be applied after all entities
 * have been updated in a frame, such as collision detection or health management.
 */
public interface IPostEntityProcessingService {

    /**
     * Called once per game frame after the main entity processing.
     * Typically used for applying global effects like collision resolution, cleanup,
     * or applying damage effects across entities.
     *
     * @param gameData The current game state, including timing and input data.
     * @param world The world containing all active game entities.
     */
    void process(GameData gameData, World world);
}
