package Personas;

public class Persona {

    private int id;
    private String nombre;
    private int edad;
    private String appaterno;
    private String apmaterno;
    private String direccion;
    private String colonia;
    //appat
    //apmat
    //dir
    //colonia
    
    //constructor
    public Persona(){
        
    }

    
    //este constructor posee sobrecarga
    public Persona(int id, String nombre, String appaterno, String apmaterno, int edad, String colonia, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.edad = edad;
        this.colonia = colonia;
        this.direccion = direccion;
        
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String appmaterno) {
        this.apmaterno = appmaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    
    
    
    
    
}
