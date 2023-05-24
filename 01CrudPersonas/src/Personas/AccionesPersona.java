package Personas;

import java.util.ArrayList;

public class AccionesPersona {

    //sirven principalmente para realizar un CRUD
    /*
    vamos a necesitar un objeto capaz de ser dinamico para poder almacenar 
    diferentes tipos de variables que pertenecen al objeto de persona.
    
    ArrayList
     */
    //<> significa que es un vector
    public ArrayList<Persona> ListaPersonas = new ArrayList<Persona>();

    //Metodos CRUD
    //listas a las personas
    public ArrayList<Persona> mostrarPersonas() {
        return ListaPersonas;
    }

    //Metodo para agregar personas
    public void AgregarPersona(Persona p) {
        ListaPersonas.add(p);
    }

    //Metodo para buscar
    public Persona BuscarPersona(int id) {
        //necesitamos instanciar persona
        Persona encontrada = new Persona();
        for (Persona p : ListaPersonas) {
            if (id == p.getId()) {
                encontrada = p;
            } else {
                System.out.println("Persona no encontrada");
            }
        }
        return encontrada;
    }
    
    //Metodo para actualizar a la persona
    
    public void ActualizarPersona(Persona Actualizada){
        //Perimero debo buscar a la persona
        Persona actualizar = BuscarPersona(Actualizada.getId());
        //Primero borro lo que existe
        ListaPersonas.remove(actualizar);
        //Agregamos lo nuevo
        ListaPersonas.add(actualizar);
    
    }
    
    //Metodo para eliminar a una persona
    
    public void EliminarPersona(Persona eliminar){
        ListaPersonas.remove(eliminar); 
    }      
}
