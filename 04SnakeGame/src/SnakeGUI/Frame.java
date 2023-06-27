package SnakeGUI;

import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame implements Runnable {

    public PanelLetras gamePanel1;
    public PanelFlechas gamePanel2;

    //Frame en donde se juntan los 2 juegos :D
    public void run() {
        String Jugador1 = null;
        String Jugador2 = null;

        while (Jugador1 == null || Jugador1.trim().isEmpty()) {
            Jugador1 = JOptionPane.showInputDialog(null, "Ingresa el nombre del primer jugador");
            if (Jugador1 == null || Jugador1.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre del primer jugador no puede estar en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        while (Jugador2 == null || Jugador2.trim().isEmpty()) {
            Jugador2 = JOptionPane.showInputDialog(null, "Ingresa el nombre del segundo jugador");
            if (Jugador2 == null || Jugador2.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre del segundo jugador no puede estar en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        gamePanel1 = new PanelLetras(Jugador1);
        gamePanel2 = new PanelFlechas(Jugador2);
        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(gamePanel1);
        mainPanel.add(gamePanel2);

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread thread1 = new Thread(gamePanel1);
        Thread thread2 = new Thread(gamePanel2);

        thread1.start();
        thread2.start();

        gamePanel1.play();
        gamePanel2.play();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Serializa los datos del juego al finalizar
        Juego juego = new Juego(gamePanel1.getNombreJugador1(), gamePanel2.getNombreJugador2(), gamePanel1.getComidaComida(), gamePanel2.getComidaComida());

        try {
            FileOutputStream fileOut = new FileOutputStream("datosJuego.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(juego);
            objectOut.close();
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
