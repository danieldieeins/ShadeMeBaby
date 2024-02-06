package live.nerotv.shademebaby.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    private final String name;
    private boolean debug;

    public Logger(String loggerName) {
        name = loggerName;
        debug = false;
    }

    public void setDebugEnabled(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    private String getPrefix() {
        return "["+new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(Calendar.getInstance().getTime())+"|"+name+"]";
    }

    public void log(String message) {
        System.out.println(getPrefix().replace(name + "]", name + "|") + " OUT ] " + message);
    }

    public void error(String message) {
        System.out.println(getPrefix().replace(name + "]", name + "|") + "ERROR] " + message);
    }

    public void debug(String message) {
        if(debug) {
            System.out.println(getPrefix().replace(name+"]",name+"|")+"DEBUG] "+message);
        }
    }
}