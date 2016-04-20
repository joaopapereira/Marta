package uk.co.jpereira.Marta;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joao on 4/18/16.
 */
public class ServerMartaConfiguration extends MartaConfiguration {

    public static String[] configurationPaths = {
            "/etc/marta/config.yml"
    };

    @JsonProperty
    private String databaseURI;

    @Override
    public String getDatabaseURI() {
        return databaseURI;
    }

    public void setDatabaseURI(String databaseURI) {
        this.databaseURI = databaseURI;
    }
}
