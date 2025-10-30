package dal;

import dal.entity.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpleadoDao {

    private static final Logger LOGGER = Logger.getLogger(EmpleadoDao.class.getName());
    private final ManagerConnection mgt;

    public EmpleadoDao() {
        this.mgt = new ManagerConnection();
    }

    /**
     * 
     */
    public List<Empleado> getAll(){
        String query = "SELECT * FROM empleado";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection conn = mgt.getConnection();
             Statement ps = conn.createStatement();
             ResultSet rs = ps.executeQuery(query)) {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellido_paterno(rs.getString("apellido_paterno"));
                emp.setApellido_materno(rs.getString("apellido_materno"));
                emp.setIdDepartamento(rs.getInt("departamento_id"));
                empleados.add(emp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all employees", e);
        }
        return empleados;
    }
    /**
     * Obtiene la información de todos los empleados de la BD escuela
     * @param limit Define el número de resultados que deseamos obtener de la tabla
     */
    public List<Empleado> getTop(int limit){
        // String query = "SELECT * FROM empleado LIMIT "+ limit;
        // Para evitar el SQL Injection
        String query = "SELECT * FROM empleado LIMIT ?";

        List<Empleado> empleados = new ArrayList<>();
        try (Connection conn = mgt.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, limit);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Empleado emp = new Empleado();
                    emp.setId(rs.getInt("id"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setApellido_paterno(rs.getString("apellido_paterno"));
                    empleados.add(emp);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching top employees", e);
        }
        return empleados;
    }

    public Empleado getById(int id){
        String query = "SELECT * FROM empleado WHERE id=?";
        Empleado emp = null;
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Empleado();
                    emp.setId(rs.getInt("id"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setApellido_paterno(rs.getString("apellido_paterno"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching employee by id: " + id, e);
        }
        return emp;
    }
    /**
     * Obtiene una lista de empleados que coinciden con los atributos no nulos del empleado dado
     * @param empleado
     * @return  
     */
    public List<Empleado> getByExample(Empleado empleado){
        String query = "SELECT * FROM empleado WHERE nombre=? OR apellido_paterno=? OR apellido_materno=?";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido_paterno());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empleado emp = new Empleado();
                    emp.setId(rs.getInt("id"));
                    emp.setNombre(rs.getString("nombre"));
                    emp.setApellido_paterno(rs.getString("apellido_paterno"));
                    emp.setApellido_materno(rs.getString("apellido_materno"));
                    empleados.add(emp);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching employees by example", e);
        }
        return empleados;
    }
    
    public void save(Empleado empleado){
        String query = "INSERT INTO empleado (nombre, apellido_paterno) VALUES (?, ?)";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido_paterno());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving employee", e);
        }
    }   

    public void delete(int id){
        String query = "DELETE FROM empleado WHERE id=?";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting employee id: " + id, e);
        }
    }

    public void update(int id, Empleado empleado){
        String query = "UPDATE empleado SET nombre=?, apellido_paterno=? WHERE id=?";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido_paterno());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating employee id: " + id, e);
        }

    }

}
