package live.nerotv.shademebaby.util;

import live.nerotv.shademebaby.ShadeMeBaby;

import java.io.File;

public class ResourceUtil {

    public static File getFile(String resourceString) {
        try {
            return new File(ResourceUtil.class.getClassLoader().getResource(resourceString).getFile());
        } catch (Exception e) {
            ShadeMeBaby.getLogger().debug("(ResourceUtil) Couldn't get resources file \""+resourceString+"\": "+e.getMessage());
            return null;
        }
    }
}