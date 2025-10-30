package dal;
import dal.entity.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartamentoDao {

    private static final Logger LOGGER = Logger.getLogger(DepartamentoDao.class.getName());
    private final ManagerConnection mgt;

    public DepartamentoDao() {
        this.mgt = new ManagerConnection();
    }

    /**
     * Obtiene la información de todos los departamentos de la BD escuela
     */
    public List<Departamento> getAll(){
        String query = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();
        try(Connection conn = mgt.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("id"));
                departamento.setNombre(rs.getString("nombre"));
                departamento.setDireccion(rs.getString("direccion"));
                departamentos.add(departamento);
            }

            // Mostrar los resultados (opcional)
            for(Departamento dept : departamentos) {
                System.out.println("ID: " + dept.getId() + 
                                 ", Nombre: " + dept.getNombre() + 
                                 ", Dirección: " + dept.getDireccion());
            }

        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Error fetching all departments", e);
        }
        return departamentos;
    }

    /**
     * Backwards-compatible method name used elsewhere in the project
     */
    public List<Departamento> getDepartments(){
        return getAll();
    }

    /**
     * Obtiene los primeros 'limit' departamentos
     */
    public List<Departamento> getTop(int limit){
        String query = "SELECT * FROM departamento LIMIT ?";
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Departamento departamento = new Departamento();
                    departamento.setId(rs.getInt("id"));
                    departamento.setNombre(rs.getString("nombre"));
                    departamento.setDireccion(rs.getString("direccion"));
                    departamentos.add(departamento);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching top departments", e);
        }
        return departamentos;
    }

    public Departamento getById(int id){
        String query = "SELECT * FROM departamento WHERE id=?";
        Departamento dept = null;
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dept = new Departamento();
                    dept.setId(rs.getInt("id"));
                    dept.setNombre(rs.getString("nombre"));
                    dept.setDireccion(rs.getString("direccion"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching department by id: " + id, e);
        }
        return dept;
    }

    /**
     * Obtiene una lista de departamentos que coinciden con nombre o direccion
     */
    public List<Departamento> getByExample(Departamento departamento){
        String query = "SELECT * FROM departamento WHERE nombre=? OR direccion=?";
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getDireccion());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Departamento d = new Departamento();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setDireccion(rs.getString("direccion"));
                    departamentos.add(d);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching departments by example", e);
        }
        return departamentos;
    }

    public void save(Departamento departamento){
        String query = "INSERT INTO departamento (nombre, direccion) VALUES (?, ?)";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getDireccion());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving department", e);
        }
    }

    public void delete(int id){
        String query = "DELETE FROM departamento WHERE id=?";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting department id: " + id, e);
        }
    }

    public void update(int id, Departamento departamento){
        String query = "UPDATE departamento SET nombre=?, direccion=? WHERE id=?";
        try (Connection conn = mgt.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getDireccion());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating department id: " + id, e);
        }
    }
}
