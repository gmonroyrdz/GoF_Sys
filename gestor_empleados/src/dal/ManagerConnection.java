package dal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Clase simple para establecer comunicación con el DBMS.
 * Lee las propiedades desde config.properties y devuelve una
 * conexión JDBC usando DriverManager.
 */
public class ManagerConnection {
    private String url;
    private String usr_name;
    private String password;

    public ManagerConnection() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                System.err.println("No se puede encontrar config.properties");
                return;
            }
            prop.load(input);
            url = prop.getProperty("db.url");
            usr_name = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            input.close();
        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
        }
    }

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usr_name, password);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
    
