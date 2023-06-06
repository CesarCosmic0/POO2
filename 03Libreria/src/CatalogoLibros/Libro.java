package CatalogoLibros;

import Controles.ArchivoL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Libro implements Serializable {

    private ArrayList<CLibro> listalibros;
    private ArchivoL objarchivo;

    public Libro() {
        listalibros = new ArrayList<>();
        objarchivo = new ArchivoL();
        listalibros = objarchivo.leer();
    }

    public void agregarLibro() {
        Scanner entrada = new Scanner(System.in);
        char resp = 's';

        while (resp == 's') {
            CLibro objlibro = new CLibro();
            objlibro.aceptarDatos();
            listalibros.add(objlibro);
            System.out.println("Libro agregado correctamente.");
            System.out.println("¿Desea agregar otro libro? (s/n)");
            resp = entrada.next().charAt(0);
            entrada.nextLine();
        }
    }

    public void consultaGral() {
        if (listalibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Los libros son:\n");
            for (int i = 0; i < listalibros.size(); i++) {
                System.out.println("-----------");
                System.out.println("Libro: " + listalibros.get(i).getNombre());
                System.out.println("Autor: " + listalibros.get(i).getAutor());
                System.out.println("Editorial: " + listalibros.get(i).getEditorial());
                System.out.println("Precio: " + listalibros.get(i).getPrecio());
                System.out.println("-----------");
            }
        }
    }

    private int traePosicion(String nombreBuscar) {
        int pos = -1;
        boolean existe = false;

        for (int i = 0; i < listalibros.size(); i++) {
            if (nombreBuscar.equals(listalibros.get(i).getNombre())) {
                pos = i;
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("No existe el registro de dicho libro.");
        }

        return pos;
    }

    public void buscar() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el nombre del libro a buscar");
        String nombreBusca = entrada.nextLine();
        int posBuscando = traePosicion(nombreBusca);

        if (posBuscando != -1) {
            CLibro libroEncontrado = listalibros.get(posBuscando);
            System.out.println("-----------");
            System.out.println("Nombre: " + libroEncontrado.getNombre());
            System.out.println("Editorial: " + libroEncontrado.getEditorial());
            System.out.println("Precio: " + libroEncontrado.getPrecio());
            System.out.println("Autor: " + libroEncontrado.getAutor());
            System.out.println("ID: " + libroEncontrado);
            System.out.println("-----------");
        }
    }

    public void borrar() {
        if (listalibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Elija el libro a borrar:");
            buscar();
            Scanner entrada = new Scanner(System.in);
            System.out.println("Ingrese el número de libro a borrar:");
            int posBorrar = entrada.nextInt();
            if (posBorrar >= 0 && posBorrar < listalibros.size()) {
                listalibros.remove(posBorrar);
                System.out.println("Libro borrado correctamente.");
            } else {
                System.out.println("Posición inválida.");
            }
        }
    }

    public void modificar() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el nombre del libro a modificar");
        String nombreModificar = entrada.nextLine();
        int posModificar = traePosicion(nombreModificar);

        if (posModificar != -1) {
            CLibro libroModificar = listalibros.get(posModificar);
            String opcion = "s";

            while (opcion.equals("s")) {
                System.out.println("¿Qué dato deseas modificar?");
                System.out.println("1.- Autor");
                System.out.println("2.- Editorial");
                System.out.println("3.- Precio");

                int resMods = entrada.nextInt();
                entrada.nextLine();

                switch (resMods) {
                    case 1:
                        System.out.println("El autor actual es: " + libroModificar.getAutor());
                        System.out.println("Ingresa el nuevo autor: ");
                        libroModificar.setAutor(entrada.nextLine());
                        System.out.println("El dato ha sido modificado.");
                        System.out.println("Autor: " + libroModificar.getAutor());
                        break;

                    case 2:
                        System.out.println("La editorial actual es: " + libroModificar.getEditorial());
                        System.out.println("Ingresa la nueva editorial: ");
                        libroModificar.setEditorial(entrada.nextLine());
                        System.out.println("El dato ha sido modificado.");
                        System.out.println("Editorial: " + libroModificar.getEditorial());
                        break;

                    case 3:
                        System.out.println("El precio actual es: " + libroModificar.getPrecio());
                        System.out.println("Ingresa el nuevo precio: ");
                        libroModificar.setPrecio(entrada.nextFloat());
                        System.out.println("El dato ha sido modificado.");
                        System.out.println("Precio: " + libroModificar.getPrecio());
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        break;
                }

                System.out.println("¿Desea cambiar más datos? (s/n)");
                opcion = entrada.next();
                entrada.nextLine();
            }
        }
    }

    public ArrayList<CLibro> getListalibros() {
        return listalibros;
    }

    public void setListalibros(ArrayList<CLibro> listalibros) {
        this.listalibros = listalibros;
    }

    public ArchivoL getObjarchivo() {
        return objarchivo;
    }

    public void setObjarchivo(ArchivoL objarchivo) {
        this.objarchivo = objarchivo;
    }

    public void grabar() {
        objarchivo.serializar(listalibros);
    }

}
