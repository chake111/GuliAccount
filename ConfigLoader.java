import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author chake
 */
public class ConfigLoader {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(".idea/config.properties")) {
            if (input == null) {
                throw new RuntimeException("配置文件 config.properties 未找到！");
            }
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException("加载配置文件失败", e);
        }
    }

    public static String getDbUrl() {
        return PROPS.getProperty("db.url");
    }

    public static String getDbUser() {
        return PROPS.getProperty("db.user");
    }

    public static String getDbPassword() {
        return PROPS.getProperty("db.password");
    }
}