package dal;

import dal.entity.Departamento;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
 * Clase para establecer comunicación con el DBMS
 */
public class Connection {
    private String url;
    private String usr_name;
    private String password;

    public Connection() {
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
        } catch (Exception e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
        }
    }

    public void connection(){
        String query = "SELECT * FROM departamento";
        try{
        // Cargar el conector de MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        // Conectarse al DBMS
            java.sql.Connection connection = DriverManager.getConnection(url, usr_name, password);
        // Crear una enunciado de SQL
            Statement statement = connection.createStatement();
        // Ejecución de la consulta
         ResultSet result = statement.executeQuery(query);

         // Proceso de asignación de resultados en los objetos entidad
         List<Departamento> departamentos = new ArrayList();
         while(result.next()){
            Departamento departamento = new Departamento();
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            String direccion = result.getString("direccion");
            departamento.setId(id);
            departamento.setNombre(nombre);
            departamento.setDireccion(direccion);
            departamentos.add(departamento);
         }

         // Mostrar los resultados
         for(Departamento dept : departamentos) {
             System.out.println("ID: " + dept.getId() + 
                              ", Nombre: " + dept.getNombre() + 
                              ", Dirección: " + dept.getDireccion());
         }
        
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
