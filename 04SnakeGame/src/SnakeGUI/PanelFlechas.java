package SnakeGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class PanelFlechas extends JPanel implements Runnable, ActionListener {

    static final int Ancho = 700;
    static final int Alto = 700;
    static final int Tamaño = 10;
    static final int Tamaño_unidades = (Ancho * Alto) / (Tamaño * Tamaño);

    final int[] x = new int[Tamaño_unidades];
    final int[] y = new int[Tamaño_unidades];

    int Longitud = 4;
    int ComidaComida;
    int ComidaX;
    int ComidaY;
    char Direccion = 'D';
    boolean Correr = false;
    Random Random;
    Timer SerpienteTemporizador;
    Timer Temporizador;
    int secondsPassed = 0;
    JLabel TemporizadorLabel;
    private final String nombreJugador2;

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public int getComidaComida() {
        return ComidaComida;
    }

    public PanelFlechas(String nombreJugador2) {
        Random = new Random();
        this.setPreferredSize(new Dimension(Ancho, Alto));
        this.setBackground(Color.GRAY);
        this.addKeyListener(new Controles());
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.nombreJugador2 = nombreJugador2;
        JLabel nombresJugadores2Label = new JLabel("Jugador: " + nombreJugador2);
        nombresJugadores2Label.setForeground(Color.white);
        nombresJugadores2Label.setFont(new Font("Sans serif", Font.PLAIN, 25));
        nombresJugadores2Label.setHorizontalAlignment(JLabel.CENTER);
        nombresJugadores2Label.setBounds(Ancho - nombresJugadores2Label.getPreferredSize().width - 40, 0, nombresJugadores2Label.getPreferredSize().width, 30);
        this.add(nombresJugadores2Label);
        TemporizadorLabel = new JLabel("Tiempo: 0s");
        TemporizadorLabel.setForeground(Color.white);
        TemporizadorLabel.setFont(new Font("Sans serif", Font.PLAIN, 25));
        TemporizadorLabel.setHorizontalAlignment(JLabel.CENTER);
        TemporizadorLabel.setBounds(0, 0, 240, 30);
        this.setLayout(null);
        this.add(TemporizadorLabel);
    }

    public void play() {
        AñadirComida();
        Correr = true;

        SerpienteTemporizador = new Timer(60, this);
        SerpienteTemporizador.start();

        Temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                TemporizadorLabel.setText("Tiempo: " + secondsPassed + "s");
                if (secondsPassed >= 180) {
                    Correr = false;
                    Temporizador.stop();
                }
            }
        });
        Temporizador.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        PintarSerpiente(graphics);
    }

    public void Mover() {
        for (int i = Longitud; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (Direccion) {
            case 'L':
                x[0] = x[0] - Tamaño;
                break;
            case 'R':
                x[0] = x[0] + Tamaño;
                break;
            case 'U':
                y[0] = y[0] - Tamaño;
                break;
            default:
                y[0] = y[0] + Tamaño;
                break;
        }
    }

    public void ComprobarComida() {
        if (x[0] == ComidaX && y[0] == ComidaY) {
            Longitud++;
            ComidaComida++;
            AñadirComida();
        }
    }

    public void PintarSerpiente(Graphics graphics) {
        if (Correr) {
            graphics.setColor(new Color(255, 0, 0));
            graphics.fillOval(ComidaX, ComidaY, Tamaño, Tamaño);

            graphics.setColor(Color.white);
            graphics.fillRect(x[0], y[0], Tamaño, Tamaño);

            for (int i = 1; i < Longitud; i++) {
                graphics.setColor(new Color(255, 255, 0));
                graphics.fillRect(x[i], y[i], Tamaño, Tamaño);
            }

            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Puntos: " + ComidaComida, (Ancho - metrics.stringWidth("Puntos: " + ComidaComida)) / 2, graphics.getFont().getSize());

        } else {
            GameOver(graphics);
        }
    }

    public void AñadirComida() {
        boolean foodOnSnake;
        do {
            ComidaX = Random.nextInt((int) (Ancho / Tamaño)) * Tamaño;
            ComidaY = Random.nextInt((int) (Alto / Tamaño)) * Tamaño;
            foodOnSnake = false;
            for (int i = 0; i < Longitud; i++) {
                if (ComidaX == x[i] && ComidaY == y[i]) {
                    foodOnSnake = true;
                    break;
                }
            }
        } while (foodOnSnake);
    }

    public void ComprobarColision() {
        if (y[0] >= Alto) {
            y[0] = 0;
        } else if (y[0] < 0) {
            y[0] = Alto - Tamaño;
        }

        if (x[0] >= Ancho) {
            x[0] = 0;
        } else if (x[0] < 0) {
            x[0] = Ancho - Tamaño;
        }

        for (int i = Longitud; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                Correr = false;
            }
        }

        if (!Correr) {
            SerpienteTemporizador.stop();
            Temporizador.stop();
            repaint();
        }
    }

    public void GameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Perdiste ", (Ancho - metrics.stringWidth("Fin ")) / 2, Alto / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Puntos: " + ComidaComida, (Ancho - metrics.stringWidth("Puntos: " + ComidaComida)) / 2, graphics.getFont().getSize());

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (Correr) {
            Mover();
            ComprobarComida();
            ComprobarColision();
        }
        repaint();
    }

    @Override
    public void run() {
    }

    public class Controles extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (Direccion != 'R') {
                        Direccion = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (Direccion != 'L') {
                        Direccion = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (Direccion != 'D') {
                        Direccion = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (Direccion != 'U') {
                        Direccion = 'D';
                    }
                    break;
            }
        }
    }
}
