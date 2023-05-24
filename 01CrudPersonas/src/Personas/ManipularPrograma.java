package Personas;

import java.util.ArrayList;
import java.util.Scanner;

public class ManipularPrograma {

    AccionesPersona control = new AccionesPersona();

    public void menu() {

        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        
        //instanciamos CRUD
        while (!salir) {
            System.out.println("Bienvenido al programa para manipular gente");
            System.out.println("Elija la opción deseada");
            System.out.println("1.- Mostrar todas las personas");
            System.out.println("2.- Registrar nueva persona");
            System.out.println("3.- Editar los datos de la persona");
            System.out.println("4.- Borrar persona");
            System.out.println("5.- Salir");

            int opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {

                case 1:

                    //instanciamos
                    ArrayList<Persona> ListadePersonas = control.mostrarPersonas();
                    //imprimir los resultados del arreglo
                    for (Persona objeto : ListadePersonas) {
                        System.out.println("El id de la persona es: " + objeto.getId()
                                + " El nombre de la persona es: " + objeto.getNombre()
                                + " El apellido paterno de la persona es: " + objeto.getAppaterno()
                                + " El apellido materno de la persona es: " + objeto.getApmaterno()
                                + " La edad de la persona es: " + objeto.getEdad()
                                + " La colonia en donde vive la persona es: " + objeto.getColonia()
                                + " La dirección de la persona es: " + objeto.getDireccion());

                    }

                    break;

                case 2:

                    //Agregar las demas variables :D 
                    System.out.println("Ingrese el id de la persona");
                    int idpersona = Integer.parseInt(entrada.nextLine());
                    System.out.println("Ingrese el nombre de la persona");
                    String nombrepersona = entrada.nextLine();
                    System.out.println("Ingrese el apellido paterno de la persona");
                    String appatpersona = entrada.nextLine();
                    System.out.println("Ingrese el apellido paterno de la persona");
                    String apmatpersona = entrada.nextLine();
                    System.out.println("Ingrese la edad de la persona");
                    int edadpersona = Integer.parseInt(entrada.nextLine());
                    System.out.println("Ingrese la colonia en donde vive la persona");
                    String coloniapersona = entrada.nextLine();
                    System.out.println("Ingrese la dirección de la persona");
                    String direccionpersona = entrada.nextLine();

                    //guardamos
                    Persona personaPersona = new Persona(idpersona, nombrepersona, appatpersona, apmatpersona, edadpersona, coloniapersona, direccionpersona);
                    //mando a llamar al metodo para guardar
                    control.AgregarPersona(personaPersona);

                    break;

                case 3:

                    //primero debo hacer una busqueda por el id
                    System.out.println("Ingrese el id de la persona a buscar");
                    idpersona = Integer.parseInt(entrada.nextLine());
                    //creo mi objeto para buscar a una persona
                    Persona personaBuscar = control.BuscarPersona(idpersona);

                    System.out.println("La informacion de la persona es: \n"
                            + "ID: " + personaBuscar.getId() + "\n"
                            + "Nombre: " + personaBuscar.getNombre() + "\n"
                            + "Apellido paterno: " + personaBuscar.getAppaterno() + "\n"
                            + "Apellido materno: " + personaBuscar.getApmaterno() + "\n"
                            + "Edad: " + personaBuscar.getEdad() + "\n"
                            + "Colonia: " + personaBuscar.getColonia() + "\n"
                            + "Dirección: " + personaBuscar.getDireccion());

                    //cambiarlos
                    System.out.println("Ingresa el nuevo nombre: ");
                    String nuevonombre = entrada.nextLine();
                    System.out.println("Ingresa el nuevo apellido paterno: ");
                    String nuevoappat = entrada.nextLine();
                    System.out.println("Ingresa el nuevo apellido materno: ");
                    String nuevoapmat = entrada.nextLine();
                    System.out.println("Ingresa la nueva edad: ");
                    int nuevaedad = Integer.parseInt(entrada.nextLine());
                    System.out.println("Ingresa la nueva colonia: ");
                    String nuevacolonia = entrada.nextLine();
                    System.out.println("Ingresa la nueva dirección: ");
                    String nuevadireccion = entrada.nextLine();

                    //envio los nuevos datos
                    personaBuscar.setNombre(nuevonombre);
                    personaBuscar.setAppaterno(nuevoappat);
                    personaBuscar.setApmaterno(nuevoapmat);
                    personaBuscar.setEdad(nuevaedad);
                    personaBuscar.setColonia(nuevacolonia);
                    personaBuscar.setDireccion(nuevadireccion);

                    //actualizo
                    control.ActualizarPersona(personaBuscar);

                    break;

                case 4:

                    System.out.println("Ingrese el id de la persona a eliminar: ");
                    idpersona = Integer.parseInt(entrada.nextLine());
                    //creo el objeto para eliminar
                    Persona personaEliminar = control.BuscarPersona(idpersona);

                    //la elimino
                    control.EliminarPersona(personaEliminar);

                    System.out.println("La persona ha sido eliminada");

                    break;

                case 5:

                    salir = true;
                    System.out.println("Adios :D");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }
}
