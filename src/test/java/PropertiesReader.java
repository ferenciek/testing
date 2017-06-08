import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Marins on 08.06.2017.
 */
public class PropertiesReader {
    public static Properties readProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream inputStream  = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
