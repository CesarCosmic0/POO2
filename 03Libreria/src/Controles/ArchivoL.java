package Controles;

import java.io.Serializable;
import java.io.*;
import java.util.*;
import CatalogoLibros.CLibro;

public class ArchivoL implements Serializable {

    ArrayList<CLibro> listaLibrosRecuperados = new ArrayList<CLibro>();

    public ArrayList<CLibro> leer() {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Libro.txt"))) {
                listaLibrosRecuperados = (ArrayList<CLibro>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaLibrosRecuperados;
    }

    public void serializar(ArrayList<CLibro> listaLibrosSerializados) {
        try {
            FileOutputStream salida = new FileOutputStream("Libro.txt");
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(salida);
            salidaObjeto.writeObject(listaLibrosSerializados);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

}
