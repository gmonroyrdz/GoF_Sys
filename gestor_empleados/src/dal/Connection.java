package dal;

import dal.entity.Departamento;
import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
 * Clase para establecer comunicación con el DBMS
 */
public class Connection {
    // Hardcoding

    // Cadena de conexión (String connection)
    private String url = "jdbc://mysql://localhost:3306";
    private String usr_name = "usrEmployees";
    private String password = "p455W0rd";

    public void connection(){
        String query = "SELECT * FROM Departamento";
        try{
        // Cargar el conector de MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        // Conectarse al DBMS
            Connection connection = (Connection) DriverManager.getConnection(url, usr_name, password);
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

        
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
