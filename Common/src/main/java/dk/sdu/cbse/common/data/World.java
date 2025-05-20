package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String id) {
        entityMap.remove(id);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity);
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }


    /**
     * Gets all entities of a specific type.
     * @param entityTypes
     * @return
     * @param <E>
     */
    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
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

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }
}
