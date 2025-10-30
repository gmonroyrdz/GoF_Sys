
import business.DepartamentoService;
import dal.entity.Empleado;
import java.util.List;



public class App {
    public static void main(String[] args) throws Exception {
        DepartamentoService service = new DepartamentoService();
        List<Empleado> empleados = service.searchById(100);
        System.out.println("==============\n");

        empleados = service.searchById(1);
        for(Empleado e: empleados){
            System.out.println(e.toString());
        }

        System.out.println("==============\n");

        empleados = service.searchById(2);
        for(Empleado e: empleados){
            System.out.println(e.toString());
        }

        System.out.println("==============\n");

        empleados = service.searchById(3);
        for(Empleado e: empleados){
            System.out.println(e.toString());
        }
        
    }
}
