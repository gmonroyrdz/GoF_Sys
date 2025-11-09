package business;

import dal.DepartamentoDao;
import dal.EmpleadoDao;
import dal.entity.Departamento;
import dal.entity.Empleado;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DepartamentoService {
    // Atributos de la clase
    private final DepartamentoDao dptDao;
    private final EmpleadoDao empDao;

    @Autowired
    public DepartamentoService(DepartamentoDao dptDao, EmpleadoDao empDao){
        this.dptDao = dptDao;
        this.empDao = empDao;
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

    public List<Departamento> getAllDepartments(){
        return dptDao.getAll();
    }

}
