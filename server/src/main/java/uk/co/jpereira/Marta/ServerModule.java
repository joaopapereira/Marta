package uk.co.jpereira.Marta;

import com.google.inject.AbstractModule;

/**
 * Created by joao on 4/18/16.
 */
public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Configuration.class).to(ServerConfiguration.class);
    }
}
