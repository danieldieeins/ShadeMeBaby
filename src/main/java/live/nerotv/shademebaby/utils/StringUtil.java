package live.nerotv.shademebaby.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.Random;

public class StringUtil {

    public static String getURLFromFile(String path) {
        return "file://"+path;
    }

    public static String getUrlFromFile(Path path) {
        return getURLFromFile(path.toString());
    }

    public static String getURLFromFile(File file) {
        return getURLFromFile(file.getAbsolutePath());
    }

    public static String addHyphensToUUID(String uuidString) {
        StringBuilder sb = new StringBuilder(uuidString);
        sb.insert(8, "-");
        sb.insert(13, "-");
        sb.insert(18, "-");
        sb.insert(23, "-");
        return sb.toString();
    }

    public static String generateAlphanumericString(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
