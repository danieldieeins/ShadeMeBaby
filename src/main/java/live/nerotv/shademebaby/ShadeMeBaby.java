package live.nerotv.shademebaby;

import live.nerotv.shademebaby.logger.Logger;

public class ShadeMeBaby {

    private static Logger logger = new Logger("SHMEBA");

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger newLogger) {
        logger = newLogger;
    }
}