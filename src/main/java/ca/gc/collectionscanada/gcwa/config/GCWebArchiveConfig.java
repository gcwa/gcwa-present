package ca.gc.collectionscanada.gcwa.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides configuration for GCWebArchive web application
 * This class is initialize during server startup and all the properties is being assigned
 * by gcwebarchive.properties file available in the resources directory of src
 *
 */
@Configuration
public class GCWebArchiveConfig
{

	/**
	 * properties refers to instance of java.util.Properties
	 */
    private Properties properties;
    
	/**
	 * Default constructor
	 */
	@Autowired
    public GCWebArchiveConfig()
    {
    }

    /**
     * Getter for properties
     * @return instance of java.util.Properties
     */
    public Properties getProperties()
    {
        return properties;
    }

    /**
     * Setter for properties, however it is never used so far in this workspace
     * if properties changes then one should use this to set the Properties instance
     * @param properties
     */
    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }

}
