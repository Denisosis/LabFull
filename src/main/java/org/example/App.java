package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App {
    private static final Logger log = LogManager.getLogger(App.class);
    public static void main( String[] args ) {
        String properties = "key";
        try {
            properties = ConfigurationUtil.getConfigurationEntry("myKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(properties);
    }
}
