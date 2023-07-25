package cliente;

import cliente.GUI.ClienteGUI;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainCliente {

    public static void main(String[] args) {

        try {
            // Establecer el tema oscuro utilizando NimbusLookAndFeel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // Personalizar los colores para el tema oscuro
            UIManager.put("control", new javax.swing.plaf.ColorUIResource(40, 40, 40)); // Cambiar color de fondo de los controles
            UIManager.put("text", new javax.swing.plaf.ColorUIResource(255, 255, 255)); // Cambiar color de texto
            UIManager.put("nimbusBase", new javax.swing.plaf.ColorUIResource(0, 0, 0)); // Cambiar color base
            UIManager.put("nimbusFocus", new javax.swing.plaf.ColorUIResource(100, 100, 100)); // Cambiar color de enfoque
            UIManager.put("nimbusLightBackground", new javax.swing.plaf.ColorUIResource(30, 30, 30)); // Cambiar color de fondo claro
            UIManager.put("nimbusSelectedText", new javax.swing.plaf.ColorUIResource(255, 255, 255)); // Cambiar color de texto seleccionado

            // Personalizar colores para el header de la ventana
            UIManager.put("InternalFrame.titlePaneBackground", new javax.swing.plaf.ColorUIResource(40, 40, 40)); // Cambiar color de fondo del panel del título
            UIManager.put("InternalFrame.titlePaneForeground", new javax.swing.plaf.ColorUIResource(255, 255, 255));

            // Personalizar estilo cristalino para los botones
            UIManager.put("Button.background", new javax.swing.plaf.ColorUIResource(240, 240, 240)); // Cambiar color de fondo de los botones
            UIManager.put("Button.foreground", new javax.swing.plaf.ColorUIResource(40, 40, 40)); // Cambiar color de texto de los botones
            UIManager.put("Button.border", new javax.swing.plaf.BorderUIResource.EmptyBorderUIResource(5, 10, 5, 10)); // Ajustar el espacio interior de los botones

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Manejar excepciones del Look and Feel
        }

        SwingUtilities.invokeLater(() -> {
        });
        ClienteGUI cliente = new ClienteGUI();
        cliente.setVisible(true);
        new Thread().start();
    }

}
