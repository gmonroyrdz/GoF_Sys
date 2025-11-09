package dal.entity;

public class Empleado {
    // Atributos de clase
    private int id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int edad;
    private int id_departamento;
    // Contructores
    public Empleado(){

    }
    public Empleado(int id, String nombre, String apellido_paterno, String apellido_materno, int edad, int id_departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.edad = edad;
        this.id_departamento = id_departamento;
    }
    // Metodos de clase
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido_paterno() {
        return apellido_paterno;
    }
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }
    public String getApellido_materno() {
        return apellido_materno;
    }
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setIdDepartamento(int idDepartamento){
        this.id_departamento = idDepartamento;
    }
    public int getIdDepartamento(){
        return this.id_departamento;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        String sep = " | ";
        str.append(this.id);
        str.append(sep);
        str.append(this.nombre);
        str.append(sep);
        str.append(this.apellido_paterno);
        str.append(sep);
        str.append(this.apellido_materno);
        str.append(sep);
        str.append(this.edad);
        str.append(sep);
        str.append(this.id_departamento);
        str.append("\n");

        return str.toString();
    }
    

}
