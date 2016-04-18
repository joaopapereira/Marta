package uk.co.jpereira.Marta;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {

    private static final String CONTROLLERS_PACKAGE_PREFIX = ".api";

    public ApplicationConfig() {
        // Add a package used to scan for components.
        packages(this.getClass().getPackage().getName() + CONTROLLERS_PACKAGE_PREFIX);
    }

}