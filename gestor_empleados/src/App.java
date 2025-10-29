
import dal.DepartamentoDao;
import dal.EmpleadoDao;
import dal.entity.Departamento;
import dal.entity.Empleado;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        DepartamentoDao deptoDao = new DepartamentoDao();
        EmpleadoDao empleadoDao = new EmpleadoDao();

        List<Departamento> departamentos = deptoDao.getDepartments();
        for (Departamento d : departamentos) {
            System.out.println("Departamento: " + d.getNombre());
        }
        
        List<Empleado> empleados = empleadoDao.getAll();
        for (Empleado e : empleados) {
            System.out.println("Empleado: " + e.getId()+ ", "+ e.getNombre() + " " + e.getApellido_paterno());
        }

        Empleado empleado1 = empleados.get(1);
        empleado1.setApellido_paterno("Perez");
        empleadoDao.update(empleado1.getId(), empleado1);

        empleados = empleadoDao.getAll();
        for (Empleado e : empleados) {
            System.out.println("Empleado: " + e.getNombre() + " " + e.getApellido_paterno());
        }

    }
}
