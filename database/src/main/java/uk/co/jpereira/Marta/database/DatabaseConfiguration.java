package uk.co.jpereira.Marta.database;

import com.google.inject.Inject;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import uk.co.jpereira.Marta.MartaConfiguration;
import uk.co.jpereira.Marta.database.repository.RepositoryPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao on 4/20/16.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
@ComponentScan(basePackages={"uk.co.jpereira.Marta.database"})
public class DatabaseConfiguration extends AbstractMongoConfiguration {

    @Inject
    MartaConfiguration martaConfiguration;

    @Autowired
    private List<Converter<?, ?>> converters;

    @Override
    protected String getDatabaseName() {
        return null;
    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#customConversions()
     */
    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(converters);
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        if(dbConfig.asAuthentication())
            return withAuthentication();
        else
            return withoutAuthentication();
    }
    private MongoClient withAuthentication()  throws Exception {
        MongoCredential a = MongoCredential.createCredential(dbConfig.getUsername(),
                getDatabaseName(),
                dbConfig.getPassword().toCharArray());
        ArrayList<MongoCredential> arr = new ArrayList<>();
        arr.add(a);
        ServerAddress addr = new ServerAddress(dbConfig.getHost(),
                dbConfig.getPort());

        MongoClient client = new MongoClient(addr, arr);
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }
    private MongoClient withoutAuthentication()  throws Exception {
        ServerAddress addr = new ServerAddress(dbConfig.getHost(),
                dbConfig.getPort());
        MongoClient client = new MongoClient(addr);
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return "uk.co.jpereira.Marta.database.domain";
    }

    // ---------------------------------------------------- MongoTemplate

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
