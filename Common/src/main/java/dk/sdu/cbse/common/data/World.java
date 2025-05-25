package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class World {

    /**
     * Map of all entities.
     */
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();


    /**
     * Adds an entity.
     * @param entity Entity to add to the world.
     * @return The id of the added entity.
     */
    public String addEntity(final Entity entity) {
        entityMap.put(entity.getId(), entity);
        return entity.getId();
    }

    /**
     * remove an entity based on entityId.
     * @param id ID of the entity to remove.
     */
    public void removeEntity(final String id) {
        entityMap.remove(id);
    }

    /**
     * remove an entity.
     * @param entity Entity to remove.
     */
    public void removeEntity(final Entity entity) {
        entityMap.remove(entity);
    }
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }


    /**
     * Gets all entities of the specified types.
     *
     * @param entityTypes The types of entities to return.
     *                    You can pass one or more classes.
     * @param <E> The type parameter extending Entity.
     * @return A list of entities that are instances of the specified types.
     */

    public <E extends Entity> List<Entity> getEntities(
            final Class<E>... entityTypes) {
        List<Entity> entityList = new ArrayList<>();
        for (Entity entity : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(entity.getClass())) {
                    entityList.add(entity);
                }
            }
        }
        return entityList;
    }

    public Entity getEntity(final String id) {
        return entityMap.get(id);
    }
}
