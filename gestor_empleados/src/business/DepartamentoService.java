package business;

import dal.DepartamentoDao;
import dal.EmpleadoDao;
import dal.entity.Departamento;
import dal.entity.Empleado;
import java.util.ArrayList;
import java.util.List;



public class DepartamentoService {
    // Atributos de la clase
    private DepartamentoDao dptDao;
    private EmpleadoDao empDao;

    public DepartamentoService(){
        this.dptDao = new DepartamentoDao();
        this.empDao = new EmpleadoDao();
    }

    public List<Empleado> searchById(int idDepartamento){
        
        Departamento dpto = dptDao.getById(idDepartamento);
        if(dpto == null){
            System.out.println("El departamento no existe");
            return null;
        }
        List<Empleado> empleados = empDao.getAll();
        List<Empleado> filtered = new ArrayList<>();
        for(Empleado e: empleados){
            if(e.getIdDepartamento() == idDepartamento)
                filtered.add(e);
        }
        return filtered;
    }

}
