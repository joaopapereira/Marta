package uk.co.jpereira.Marta;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by joao on 4/18/16.
 */
public class MartaConfiguration {


    private Logger logger = Logger.getLogger(String.valueOf(MartaConfiguration.class));
    private Map< String, String > log;
    private String pathToKey;

    private String key = null;
    public String getKey(){
        if(key != null){
            return key;
        }
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(getPathToKey());

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                key += line;
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            logger.severe(
                    "Unable to open file '" +
                            getPathToKey() + "'");
        }
        catch(IOException ex) {
            logger.severe(
                    "Error reading file '"
                            + getPathToKey() + "'");
        }
        return key;
    }


    public String getPathToKey() {
        return pathToKey;
    }
    public void setPathToKey(String pathToKey) {
        this.pathToKey = pathToKey;
    }
    public Map< String, String > getLog() {
        return log;
    }

    public void setLog(Map< String, String > users) {
        this.log = users;
    }

    public static MartaConfiguration loadConfigurationFile(String path, Class configurationClass) {
        Yaml yaml = new Yaml();
        try( InputStream in = Files.newInputStream(Paths.get(path)) ) {
            MartaConfiguration config = yaml.loadAs( in, MartaConfiguration.class );
            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void add(MartaConfiguration configuration) {
        if(configuration == null)
            return;
        if(configuration.getPathToKey() != null ) {
            setPathToKey(configuration.getPathToKey());
        }
        for(Map.Entry<String, String> logLevel: configuration.getLog().entrySet()){
            log.put(logLevel.getKey(), logLevel.getValue());
        }
        updateLogLevel();
    }
    private void updateLogLevel() {
        for(Map.Entry<String, String> logLevel: log.entrySet()) {
            Logger.getLogger(logLevel.getKey()).setLevel(Level.parse(logLevel.getValue()));
        }
    }

    public String getDatabaseURI() {
        return "";
    }
}
