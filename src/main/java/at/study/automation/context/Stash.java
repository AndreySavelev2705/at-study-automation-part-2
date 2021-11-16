package at.study.automation.context;

import java.util.HashMap;
import java.util.Map;

public class Stash {

    private Map<String, Object> entities = new HashMap<>();

    public void put(String stahsId, Object entity) {
        entities.put(stahsId, entity);
    }

    public Object get(String stashId) {
        return entities.get(stashId);
    }

    public <T> T get(String stashId, Class<T> clazz) {
        return (T) get(stashId);
    }
}
