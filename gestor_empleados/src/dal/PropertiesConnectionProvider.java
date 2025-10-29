package dal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionProvider implementation that reads connection properties from
 * a `config.properties` resource on the classpath.
 */
public class PropertiesConnectionProvider implements ConnectionProvider {
    private final String url;
    private final String username;
    private final String password;

    public PropertiesConnectionProvider() {
        Properties p = loadProperties();
        this.url = p.getProperty("db.url");
        this.username = p.getProperty("db.username");
        this.password = p.getProperty("db.password");
    }

    PropertiesConnectionProvider(Properties p) {
        this.url = p.getProperty("db.url");
        this.username = p.getProperty("db.username");
        this.password = p.getProperty("db.password");
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se puede encontrar config.properties en el classpath");
            }
            prop.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Error al leer config.properties", e);
        }
        return prop;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            // Ensure driver is loaded (no-op on modern drivers but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBC Driver not found", e);
        }
        return DriverManager.getConnection(url, username, password);
    }
}
