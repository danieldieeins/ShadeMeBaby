package live.nerotv.shademebaby.frame;

import live.nerotv.shademebaby.ShadeMeBaby;
import live.nerotv.shademebaby.util.ResourceUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class NFrame extends JFrame {

    public void setIconFromResources(String resourceIconPath) {
        try {
            Image icon = ImageIO.read(ResourceUtil.getFile(resourceIconPath)).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            setIconImage(icon);
        } catch (Exception e) {
            ShadeMeBaby.getLogger().debug("(NFrame) Error obtaining icon file \""+resourceIconPath+"\" from resources: " + e.getMessage());
        }
    }
}