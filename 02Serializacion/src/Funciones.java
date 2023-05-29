
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Funciones {

    //es mis variables y objetos
    ArrayList<Persona> listaProfesores = new ArrayList();
    ArrayList<Persona> listaAlumnos = new ArrayList();
    String rep = "si";
    String nombre = "";
    int edad, num_empleado, bol;

    //vamos aplciar el polimorfismo
    Persona profesor = new Profesor();
    Persona alumno = new Alumno();

    //menu
    void menu() {
        while (true) {
            try {
                String var = JOptionPane.showInputDialog("Ingresa la opcion deseada : \n"
                        + "1.- Registrar nuevo Profesor. \n"
                        + "2.- Consultar Profesor. \n"
                        + "3.- Editar Profesor. \n"
                        + "4.- Eliminar Profesor. \n"
                        + "5.- Registrar nuevo Alumno. \n"
                        + "6.- Consultar Alumno. \n"
                        + "7.- Editar Alumno. \n"
                        + "8.- Eliminar Alumno. \n"
                        + "9.- Salir. \n");

                if (var == null) {
                    System.exit(0);
                }

                int opcion = Integer.parseInt(var);

                switch (opcion) {
                    case 1:
                        PedirDatosProfesor();
                        break;
                    case 2:
                        MostrarProfesores();
                        break;
                    case 3:
                        EditarProfesor();
                        break;
                    case 4:
                        EliminarProfesor();
                        break;
                    case 5:
                        PedirDatosAlumno();
                        break;
                    case 6:
                        MostrarAlumnos();
                        break;
                    case 7:
                        EditarAlumno();
                        break;
                    case 8:
                        EliminarAlumno();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");

                }

            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());

            }
        }
    }

    private void PedirDatosProfesor() {
        try {
            num_empleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de empleado: "));
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado: ");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del empleado: "));

            profesor = new Profesor(num_empleado, nombre, edad);
            listaProfesores.add(profesor);

            guardarDatosProfesor();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para el número de empleado o edad.");
        }
    }

    private void PedirDatosAlumno() {
        try {
            bol = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la boleta del alumno: "));
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del alumno: ");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del alumno: "));

            alumno = new Alumno(bol, nombre, edad);
            listaAlumnos.add(alumno);

            guardarDatosAlumnos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para la boleta o edad.");
        }
    }

    private void MostrarAlumnos() throws FileNotFoundException, IOException, ClassNotFoundException {
        //hay que definir la entrada de un archivo
        FileInputStream archivo = new FileInputStream("Alumnos.dat");
        //genero la lectura del buffer de objetos
        ObjectInputStream entrada = new ObjectInputStream(archivo);
        //que hay que transofrmar los objetos en bytes
        //debo de castear los tipos de dato
        listaAlumnos = (ArrayList) entrada.readObject();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            //recorro lo que necesito del objeto
            Alumno obj = (Alumno) listaAlumnos.get(i);
            //ahora si sacamos la informacion
            JOptionPane.showMessageDialog(null, "\n"
                    + "Id de Alumno : " + (i + 1) + "\n"
                    + "Boleta : " + obj.getBoleta() + "\n"
                    + "Nombre : " + obj.getNombre() + "\n"
                    + "Edad : " + obj.getEdad() + "\n"
                    + "Tipo Persona : " + obj.tipoPersona() + "\n");
        }

    }

    private void MostrarProfesores() throws FileNotFoundException, IOException, ClassNotFoundException {
        //hay que definir la entrada de un archivo
        FileInputStream archivo = new FileInputStream("Profesores.dat");
        //genero la lectura del buffer de objetos
        ObjectInputStream entrada = new ObjectInputStream(archivo);
        //que hay que transofrmar los objetos en bytes
        //debo de castear los tipos de dato
        listaProfesores = (ArrayList) entrada.readObject();
        for (int i = 0; i < listaProfesores.size(); i++) {
            //recorro lo que necesito del objeto
            Profesor obj = (Profesor) listaProfesores.get(i);
            //ahora si sacamos la informacion
            JOptionPane.showMessageDialog(null, "\n"
                    + "Id de Profesor : " + (i + 1) + "\n"
                    + "Numero de Profesor : " + obj.getNum_empleado() + "\n"
                    + "Nombre : " + obj.getNombre() + "\n"
                    + "Edad : " + obj.getEdad() + "\n"
                    + "Tipo de persona : " + obj.tipoPersona() + "\n");
        }

    }

    private void EditarAlumno() {
        try {
            int IdAlumno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de alumno a editar: ")) - 1;

            if (IdAlumno >= 0 && IdAlumno < listaAlumnos.size()) {
                Alumno alumno = (Alumno) listaAlumnos.get(IdAlumno);

                int nuevaBoleta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva boleta: "));
                alumno.setBoleta(nuevaBoleta);
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");
                alumno.setNombre(nuevoNombre);
                int nuevaEdad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad: "));
                alumno.setEdad(nuevaEdad);

                guardarDatosAlumnos();
            } else {
                JOptionPane.showMessageDialog(null, "Id inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para la boleta o edad.");
        }
    }

    private void EditarProfesor() {
        try {
            int IdProfesor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de alumno a editar: ")) - 1;

            if (IdProfesor >= 0 && IdProfesor < listaProfesores.size()) {
                Profesor profesor = (Profesor) listaProfesores.get(IdProfesor);

                int nuevoNumero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo numero de empleado: "));
                profesor.setNum_empleado(nuevoNumero);
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");
                profesor.setNombre(nuevoNombre);
                int nuevaEdad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad: "));
                profesor.setEdad(nuevaEdad);

                guardarDatosProfesor();
            } else {
                JOptionPane.showMessageDialog(null, "Id inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para el número de empleado o edad.");
        }
    }

    private void EliminarAlumno() {
        try {
            int IdAlumno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id de alumno a eliminar: ")) - 1;
            if (IdAlumno >= 0 && IdAlumno < listaAlumnos.size()) {
                listaAlumnos.remove(IdAlumno);
                guardarDatosAlumnos();
                JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Id inválido. No se pudo eliminar el alumno.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para el Id.");
        }
    }

    private void EliminarProfesor() {
        try {
            int IdProfesor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Id de alumno a eliminar: ")) - 1;
            if (IdProfesor >= 0 && IdProfesor < listaProfesores.size()) {
                listaAlumnos.remove(IdProfesor);
                guardarDatosProfesor();
                JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Id inválido. No se pudo eliminar el profesor.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para el Id.");
        }
    }

    private void guardarDatosProfesor() {
        try {
            FileOutputStream archivo = new FileOutputStream("Profesores.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(listaProfesores);
            salida.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage());
        }
    }

    private void guardarDatosAlumnos() {
        try {
            FileOutputStream archivo = new FileOutputStream("Alumnos.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(listaAlumnos);
            salida.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + e.getMessage());
        }
    }

}
