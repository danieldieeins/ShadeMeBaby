package live.nerotv;

import live.nerotv.logger.Logger;

public class ShadeMeBaby {

    private static Logger logger = new Logger("ShadeMeBaby");

    /*public static void main(String[] testArgs) throws IOException {
        logger = new Logger("ShadeMeBaby");
        logger.setDebugEnabled(true);
        NFrame frame = new NFrame();
        frame.setIconFromResources("icosn.png");
        frame.setSize(new Dimension(50,50));
        frame.setLocationRelativeTo(null); frame.setVisible(true);
    }*/

    public static Logger getLogger() {
        return logger;
    }
}