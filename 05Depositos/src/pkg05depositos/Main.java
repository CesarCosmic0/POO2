package pkg05depositos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CajeroAutomatico cajero = new CajeroAutomatico();
        Cliente cliente = new Cliente("Cliente", cajero);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(cajero, cliente);
            }
        });
    }

    private static void createAndShowGUI(CajeroAutomatico cajero, Cliente cliente) {
        JFrame frame = new JFrame("Cajero Automático");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setLayout(new BorderLayout());

        JLabel saldoLabel = new JLabel("Saldo actual: $" + cajero.getSaldo());
        saldoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
        frame.add(saldoLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 fila, 2 columnas, espacio horizontal 10, espacio vertical 0
        JButton depositarButton = new JButton("Depositar");
        depositarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double monto = solicitarMonto("Ingrese el monto a depositar:");
                if (monto != null) {
                    if (montoValido(monto)) {
                        String clave = solicitarClave("Ingrese la clave:");
                        if (claveCorrecta(clave)) {
                            Deposito deposito = new Deposito(cliente, monto, cajero, saldoLabel);
                            deposito.start();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Clave incorrecta. No se pudo realizar el depósito.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "El monto ingresado no es válido. Ingrese uno de los siguientes montos: 50, 100, 200, 500, 1000.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(depositarButton);

        JButton retirarButton = new JButton("Retirar");
        retirarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double monto = solicitarMonto("Ingrese el monto a retirar:");
                if (monto != null) {
                    if (monto > cajero.getSaldo()) {
                        JOptionPane.showMessageDialog(frame, "El saldo de la cuenta es insuficiente. Ingrese un monto menor o igual al saldo actual.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else if (!montoValido(monto)) {
                        JOptionPane.showMessageDialog(frame, "El monto ingresado no es válido. Ingrese uno de los siguientes montos: 50, 100, 200, 500, 1000.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Retiro retiro = new Retiro(cliente, monto, cajero, saldoLabel);
                        retiro.start();
                    }
                }
            }
        });
        buttonPanel.add(retirarButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static Double solicitarMonto(String mensaje) {
        String montoStr = JOptionPane.showInputDialog(null, mensaje);
        if (montoStr != null) {
            try {
                return Double.parseDouble(montoStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static String solicitarClave(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje);
    }

    private static boolean claveCorrecta(String clave) {
        return clave != null && clave.equals("1234");
    }

    private static boolean montoValido(double monto) {
        List<Double> montosPermitidos = Arrays.asList(50.0, 100.0, 200.0, 500.0, 1000.0);
        return montosPermitidos.contains(monto);
    }
}
