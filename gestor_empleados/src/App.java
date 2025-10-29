
import dal.ManagerConnection;
import dal.entity.Departamento;
import dal.entity.Empleado;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        ManagerConnection conn = new ManagerConnection();
        List<Departamento> departamentos =  conn.getDepartments();
        List<Empleado> empleados = conn.getEmployees();

        Empleado empleado1 = empleados.get(1);
        empleado1.setApellido_paterno("Perez");
        conn.update(empleado1.getId(), empleado1);

    }
}
