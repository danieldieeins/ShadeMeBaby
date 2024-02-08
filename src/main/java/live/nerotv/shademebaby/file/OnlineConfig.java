package live.nerotv.shademebaby.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OnlineConfig implements CFG {
    private Gson gson;
    private JsonElement json;
    private String url;

    public OnlineConfig(String url) {
        this.url = url;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            json = gson.fromJson(getFromURL(url), JsonElement.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Gson getGson() {
        return gson;
    }

    public JsonElement getJson() {
        return json;
    }

    @Override
    public String getString(String path) {
        if (get(path) != null) {
            return get(path).toString();
        }
        return null;
    }

    @Override
    public int getInt(String path) {
        return getInteger(path);
    }

    @Override
    public Integer getInteger(String path) {
        if (get(path) != null) {
            try {
                return (int)(double)getDouble(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public double getDoub(String path) {
        return getDouble(path);
    }

    @Override
    public Double getDouble(String path) {
        if (get(path) != null) {
            try {
                return (Double) get(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public boolean getBool(String path) {
        return getBoolean(path);
    }

    @Override
    public Boolean getBoolean(String path) {
        if (get(path) != null) {
            try {
                return (Boolean) get(path);
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    @Override
    public boolean checkEntry(String path, Object value) {
        return get(path) != null;
    }

    @Override
    public Object get(String path) {
        JsonObject rootNode = json.getAsJsonObject();
        String[] parts = path.split("\\.");
        JsonElement currentNode = rootNode;
        for (String part : parts) {
            if (!currentNode.isJsonObject() || !currentNode.getAsJsonObject().has(part)) {
                return null;
            }
            currentNode = currentNode.getAsJsonObject().get(part);
        }
        return gson.fromJson(currentNode, Object.class);
    }

    @SuppressWarnings("all")
    private String getFromURL(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }
}