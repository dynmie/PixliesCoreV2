package net.pixlies.utils;

import java.util.HashMap;
import java.util.Map;

public class SimpleSave {

    private final Map<String, Map<Object, Object>> sections = new HashMap<>();

    public Map<Object, Object> getSection(String section) {
        return sections.get(section);
    }

    public void addToSection(String section, Object key, Object value) {
        if (!sections.containsKey(section)) return;
        sections.get(section).put(key, value);
    }

    public void registerSection(String section) {
        sections.put(section, new HashMap<>());
    }

    public <T> T getFromSection(String section, String key, T clazz) {
        return (T) getSection(section).get(key);
    }

    public String getFromSectionAsString(String section, String key) {
        return getSection(section).get(key).toString();
    }

    public Integer getFromSectionAsInt(String section, String key) {
        return (Integer) getSection(section).get(key);
    }

}
