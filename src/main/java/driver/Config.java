package driver;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    String env = System.getProperty("env","docker");
    private final Logger LOGGER = Logger.getLogger(String.valueOf(Config.class));

    public void initProperties(){
        Properties properties = System.getProperties();

        try {
            InputStream propertiesFile = Config.class.getClassLoader()
                    .getResourceAsStream( env + ".properties");
            properties.load(propertiesFile);

        } catch (IOException e) {
            LOGGER.error("Reading config file is failed", e);
        }
    }
}
