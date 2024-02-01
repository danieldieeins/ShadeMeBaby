package live.nerotv.shademebaby;

import live.nerotv.shademebaby.logger.Logger;

public class ShadeMeBaby {

    private static Logger logger;

    public static Logger getLogger() {
        if(logger==null) {
            logger = new Logger("ShadeMeBaby");
        }
        return logger;
    }
}