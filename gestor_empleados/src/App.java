
import dal.ManagerConnection;
import dal.entity.Departamento;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        ManagerConnection conn = new ManagerConnection();
        List<Departamento> departamentos =  conn.getDepartments();
        conn.getEmployees(10);
    }
}
