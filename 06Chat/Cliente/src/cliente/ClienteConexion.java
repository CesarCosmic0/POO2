package cliente;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClienteConexion {

    private final JTextArea chatArea;
    private PrintWriter salidaServidor;
    private final String nombreCliente;

    public ClienteConexion(JTextArea chatArea, String nombreCliente) {
        this.chatArea = chatArea;
        this.nombreCliente = nombreCliente;
        conectarAlServidor();
    }

    private void conectarAlServidor() {
        String serverAddress = "192.168.100.237"; // Dirección IP del servidor
        int serverPort = 5000; // Puerto del servidor
        try {
            Socket clienteSocket = new Socket(serverAddress, serverPort);
            chatArea.append("[S] ¡Conexión exitosa!\n");

            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            salidaServidor = new PrintWriter(clienteSocket.getOutputStream(), true);

            salidaServidor.println(nombreCliente);

            Thread receiveThread;
            receiveThread = new Thread(() -> {
                try {
                    String mensajeEntrada;
                    while ((mensajeEntrada = entradaServidor.readLine()) != null) {
                        chatArea.append(mensajeEntrada + "\n");
                    }
                } catch (IOException e) {
                }
            });

            receiveThread.setDaemon(true);
            receiveThread.start();

        } catch (IOException e) {
        }
    }

    public void sendMessage(String message) {
        salidaServidor.println(message);
    }

}
