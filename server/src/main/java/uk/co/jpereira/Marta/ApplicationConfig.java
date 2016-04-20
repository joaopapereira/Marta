package uk.co.jpereira.Marta;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {

    private static final String CONTROLLERS_PACKAGE_PREFIX = ".api";

    public ApplicationConfig() {
        // Add a package used to scan for components.
        packages(this.getClass().getPackage().getName() + CONTROLLERS_PACKAGE_PREFIX);
        this.getConfiguration().getProperty("configuration_path");

        Injector injector = Guice.createInjector(new ServerModule());
        ServerMartaConfiguration config = (ServerMartaConfiguration)injector.getInstance(MartaConfiguration.class);
        ServerMartaConfiguration loadedConfig = (ServerMartaConfiguration) MartaConfiguration.loadConfigurationFile(ServerMartaConfiguration.configurationPaths[0], ServerMartaConfiguration.class);
        config.add(loadedConfig);
    }

}