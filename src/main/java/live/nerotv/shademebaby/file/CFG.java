package live.nerotv.shademebaby.file;

import com.google.gson.Gson;

public interface CFG {

    default Gson getGson() {
        return null;
    }

    default String getString(String path) {
        return null;
    }

    default int getInt(String path) {
        return getInteger(path);
    }

    default Integer getInteger(String path) {
        return null;
    }

    default double getDoub(String path) {
        return getDouble(path);
    }

    default Double getDouble(String path) {
        return null;
    }

    default boolean getBool(String path) {
        return getBoolean(path);
    }

    default Boolean getBoolean(String path) {
        return null;
    }

    default boolean checkEntry(String path, Object value) {
        return getBoolean(path);
    }

    default Object get(String path) {
        return null;
    }
}