package servidor;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Servidor {

    private final JTextArea chatArea;
    private final Set<PrintWriter> clientes = new HashSet<>();

    public Servidor(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public void iniciarServidor() {
        int PUERTO = 5000;
        broadcast("[S] ¡Servidor lanzado exitosamente!");
        try ( ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                PrintWriter salidaCliente = new PrintWriter(clienteSocket.getOutputStream(), true);
                clientes.add(salidaCliente);
                Thread handleClientThread = new Thread(() -> handleClient(clienteSocket));
                handleClientThread.setDaemon(true);
                handleClientThread.start();
            }
        } catch (IOException e) {
        }
    }

    private void handleClient(Socket clienteSocket) {
        try {
            try (clienteSocket;  BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {
                String nombreCliente = entradaCliente.readLine();
                broadcast("[S] " + nombreCliente + " se ha unido al chat del servidor.");
                String mensajeCliente;
                while ((mensajeCliente = entradaCliente.readLine()) != null) {
                    broadcast(nombreCliente + ": " + mensajeCliente);
                }
            }
        } catch (IOException e) {
        }
    }

    public void broadcast(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(mensaje + "\n");
            for (PrintWriter cliente : clientes) {
                cliente.println(mensaje);
            }
        });
    }
}
