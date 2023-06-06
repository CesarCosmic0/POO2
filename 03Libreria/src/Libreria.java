import CatalogoLibros.Libro;
import java.util.Scanner;

public class Libreria {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        Libro objlista = new Libro();

        int opcion;

        do {
            System.out.println("Bienvenido a la Libreria Cesar");
            System.out.println("Elija la opción deseada");
            System.out.println("1.- Agregar un nuevo libro");
            System.out.println("2.- Consultar Libro");
            System.out.println("3.- Consultar todos los libros");
            System.out.println("4.- Borrar un libro");
            System.out.println("5.- Modificar un libro");
            System.out.println("6.- Salir");

            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    objlista.agregarLibro();
                    break;
                case 2:
                    objlista.buscar();
                    break;
                case 3:
                    objlista.consultaGral();
                    break;
                case 4:
                    objlista.borrar();
                    break;
                case 5:
                    objlista.modificar();
                    break;
                case 6:
                    System.out.println("Se genera el archivo al salir");
                    objlista.grabar();
                    break;
                default:
                    System.out.println("Error: Opción inválida");
            }
        } while (opcion != 6);
    }
}
