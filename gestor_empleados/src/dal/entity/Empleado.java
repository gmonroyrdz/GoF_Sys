package dal.entity;

public class Empleado {
    // Atributos de clase
    private int id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int edad;
    // Contructores
    public Empleado(){

    }
    public Empleado(int id, String nombre, String apellido_paterno, String apellido_materno, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.edad = edad;
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
    

}
