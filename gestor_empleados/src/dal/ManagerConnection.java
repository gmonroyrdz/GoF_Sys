package dal;

import dal.entity.Departamento;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/*
 * Clase para establecer comunicación con el DBMS
 */
public class ManagerConnection {
    private String url;
    private String usr_name;
    private String password;
    private java.sql.Connection conn;

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
            Connection conn = DriverManager.getConnection(url,usr_name,password);
            return (java.sql.Connection) conn;
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * 
     */
    public void getEmployees(){

    }

    /**
     * Obtiene la información de todos los empleados de la BD escuela
     * @param limit Define el número de resultados que deseamos obtener de la tabla
     */
    public void getEmployees(int limit){
        // String query = "SELECT * FROM empleado LIMIT "+ limit;
        // Para evitar el SQL Injection
        String query = "SELECT * FROM empleado LIMIT ?";
        .
        .
        .
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, limit);

        

    }


    /**
     * Obtiene la información de los departamentos de la BD escuela
     */
    public List<Departamento> getDepartments(){
        String query = "SELECT * FROM departamento";
        try{
           Connection connection =  getConnection();
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
        
         return departamentos;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
