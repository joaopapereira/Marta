package uk.co.jpereira.Marta;

import com.google.inject.AbstractModule;

/**
 * Created by joao on 4/18/16.
 */
public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MartaConfiguration.class).to(MartaConfiguration.class);
    }
}
