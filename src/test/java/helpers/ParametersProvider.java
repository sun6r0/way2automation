package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Class provides configuration parameters from files set by system
 * properties.
 */
public final class ParametersProvider {
    /**
     * List of parameters holders.
     */
    private ArrayList<Properties> propertiesList = new ArrayList<>();

    /**
     * Gets configuration file names from system properties.
     *
     * @return list of configuration file names
     */
    private static ArrayList<String> getConfigFileNames() {
        ArrayList<String> configFileNames = new ArrayList<>();
        for (String key : System.getProperties().stringPropertyNames()) {
            if (key.startsWith("config.location")) {
                String[] fileNames = System.getProperties().getProperty(key)
                        .split(";");
                configFileNames.addAll(Arrays.asList(fileNames));
            }
        }
        return configFileNames;
    }

    /**
     * ParametersProvider constructor.
     */
    private ParametersProvider() {
        ArrayList<String> configFileNames = getConfigFileNames();
        for (String fileName : configFileNames) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            propertiesList.add(properties);
        }
    }

    /**
     * ParametersProvider instance holder.
     */
    private static ParametersProvider instance;

    /**
     * ParametersProvider instance accessor.
     *
     * @return ParametersProvider instance
     */
    private static ParametersProvider getInstance() {
        if (instance == null) {
            instance = new ParametersProvider();
        }
        return instance;
    }

    /**
     * Gets parameter by its key from list of parameters holder.
     *
     * @param key of parameter to find in configuration
     * @return parameter or empty string, if it is not found
     */
    public static String getProperty(final String key) {
        for (Properties properties : getInstance().propertiesList) {
            String result = properties.getProperty(key, null);
            if (result != null) {
                return result;
            }
        }
        return "";
    }
}
