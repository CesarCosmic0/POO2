package cliente.GUI;

import cliente.ClienteConexion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClienteGUI extends JFrame {

    private ClienteConexion clienteConexion;
    private final JTextArea chatArea;
    private JTextField messageField;
    private final JPopupMenu emojiMenu;
    private final JButton sendButton;

    public ClienteGUI() {
        
        ImageIcon icono = new ImageIcon(getClass().getResource("chat.png"));
        setIconImage(icono.getImage());
        setFont(new Font("Apple Color Emoji", Font.PLAIN, 12));
        setTitle("Cliente Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        sendButton = new JButton("Enviar");
        sendButton.addActionListener((ActionEvent e) -> {
            String message = messageField.getText();
            clienteConexion.sendMessage(message);
            messageField.setText("");
        });

        String nombreCliente = null;
        do {
            try {
                nombreCliente = JOptionPane.showInputDialog(null, "Ingresa tu nombre: ");
                if (nombreCliente == null) {
                    System.exit(0);
                } else if (nombreCliente.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un nombre.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa un nombre válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.exit(1);
            }
        } while (nombreCliente == null || nombreCliente.isEmpty());

        clienteConexion = new ClienteConexion(chatArea, "[C] " + nombreCliente);

        JButton emojiButton = new JButton("😊");
        emojiButton.addActionListener(e -> showEmojiMenu(emojiButton));
        messageField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });

        // Actualizamos el estado del botón de enviar inicialmente
        updateButtonState();

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel emojiPanel = new JPanel(new BorderLayout());
        emojiPanel.add(emojiButton, BorderLayout.WEST);
        emojiPanel.add(sendButton, BorderLayout.EAST);

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(emojiPanel, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(400, 400);
        setLocation(900, 200);

        emojiMenu = new JPopupMenu();

        JMenu subMenuEmojis = new JMenu("Emojis");
        subMenuEmojis.add(createEmojiMenuItem("😊"));
        subMenuEmojis.add(createEmojiMenuItem("😄"));
        subMenuEmojis.add(createEmojiMenuItem("😍"));
        subMenuEmojis.add(createEmojiMenuItem("😘"));
        subMenuEmojis.add(createEmojiMenuItem("😎"));
        subMenuEmojis.add(createEmojiMenuItem("😇"));
        subMenuEmojis.add(createEmojiMenuItem("🤗"));
        subMenuEmojis.add(createEmojiMenuItem("😂"));
        subMenuEmojis.add(createEmojiMenuItem("😋"));
        subMenuEmojis.add(createEmojiMenuItem("🤔"));
        subMenuEmojis.add(createEmojiMenuItem("😡"));
        subMenuEmojis.add(createEmojiMenuItem("😭"));

        JMenu subMenuAnimales = new JMenu("Animales");
        subMenuAnimales.add(createEmojiMenuItem("🐶"));
        subMenuAnimales.add(createEmojiMenuItem("🐱"));
        subMenuAnimales.add(createEmojiMenuItem("🐭"));
        subMenuAnimales.add(createEmojiMenuItem("🐰"));
        subMenuAnimales.add(createEmojiMenuItem("🦊"));
        subMenuAnimales.add(createEmojiMenuItem("🦁"));
        subMenuAnimales.add(createEmojiMenuItem("🐻"));
        subMenuAnimales.add(createEmojiMenuItem("🐯"));
        subMenuAnimales.add(createEmojiMenuItem("🐸"));
        subMenuAnimales.add(createEmojiMenuItem("🐌"));
        subMenuAnimales.add(createEmojiMenuItem("🐘"));
        subMenuAnimales.add(createEmojiMenuItem("🦑"));

        JMenu subMenuComida = new JMenu("Comida");
        subMenuComida.add(createEmojiMenuItem("🍕"));
        subMenuComida.add(createEmojiMenuItem("🍣"));
        subMenuComida.add(createEmojiMenuItem("🍔"));
        subMenuComida.add(createEmojiMenuItem("🌮"));
        subMenuComida.add(createEmojiMenuItem("🍿"));
        subMenuComida.add(createEmojiMenuItem("🍝"));
        subMenuComida.add(createEmojiMenuItem("🍱"));
        subMenuComida.add(createEmojiMenuItem("🍛"));
        subMenuComida.add(createEmojiMenuItem("🍲"));
        subMenuComida.add(createEmojiMenuItem("🍦"));
        subMenuComida.add(createEmojiMenuItem("🍩"));
        subMenuComida.add(createEmojiMenuItem("🍺"));
        subMenuComida.add(createEmojiMenuItem("🍹"));

        JMenu subMenuViajes = new JMenu("Viajes");
        subMenuViajes.add(createEmojiMenuItem("✈️"));
        subMenuViajes.add(createEmojiMenuItem("🚗"));
        subMenuViajes.add(createEmojiMenuItem("🚆"));
        subMenuViajes.add(createEmojiMenuItem("🚢"));
        subMenuViajes.add(createEmojiMenuItem("🚲"));
        subMenuViajes.add(createEmojiMenuItem("🏔️"));
        subMenuViajes.add(createEmojiMenuItem("🗽"));
        subMenuViajes.add(createEmojiMenuItem("🏰"));
        subMenuViajes.add(createEmojiMenuItem("🗺️"));
        subMenuViajes.add(createEmojiMenuItem("🏝️"));
        subMenuViajes.add(createEmojiMenuItem("🏟️"));
        subMenuViajes.add(createEmojiMenuItem("🏞️"));

        JMenu subMenuNaturaleza = new JMenu("Naturaleza");
        subMenuNaturaleza.add(createEmojiMenuItem("🌞"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌧️"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌈"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌪️"));
        subMenuNaturaleza.add(createEmojiMenuItem("❄️"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌊"));
        subMenuNaturaleza.add(createEmojiMenuItem("🍃"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌸"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌳"));
        subMenuNaturaleza.add(createEmojiMenuItem("🍂"));
        subMenuNaturaleza.add(createEmojiMenuItem("☔"));
        subMenuNaturaleza.add(createEmojiMenuItem("🌋"));

        // Agrega los submenús al menú principal
        emojiMenu.add(subMenuEmojis);
        emojiMenu.add(subMenuAnimales);
        emojiMenu.add(subMenuNaturaleza);
        emojiMenu.add(subMenuComida);
        emojiMenu.add(subMenuViajes);

    }

    private void updateButtonState() {
        String text = messageField.getText().trim();
        boolean isEmpty = text.isEmpty();
        sendButton.setEnabled(!isEmpty);
    }

    private void showEmojiMenu(JButton emojiButton) {
        emojiMenu.show(emojiButton, 0, emojiButton.getHeight());
    }

    private JMenuItem createEmojiMenuItem(String emoji) {
        JMenuItem item = new JMenuItem(emoji);
        item.addActionListener(e -> messageField.setText(messageField.getText() + emoji));
        return item;
    }

}
