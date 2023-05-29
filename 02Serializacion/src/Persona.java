
import java.io.Serializable;

public abstract class Persona implements Serializable {

    //porque queremos ocupar la propiedad de polimorfismo
    private String nombre;
    private int edad;

    public Persona() {

    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
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

    //voy a crear el metodo abstracto para que pueda modificar al objeto principal
    abstract String tipoPersona();

}
