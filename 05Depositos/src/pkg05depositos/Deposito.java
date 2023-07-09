package pkg05depositos;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class Deposito extends Thread {
    private Cliente cliente;
    private double monto;
    private CajeroAutomatico cajero;
    private JLabel saldoLabel;

    public Deposito(Cliente cliente, double monto, CajeroAutomatico cajero, JLabel saldoLabel) {
        this.cliente = cliente;
        this.monto = monto;
        this.cajero = cajero;
        this.saldoLabel = saldoLabel;
    }

    @Override
    public void run() {
        CajeroAutomatico cajero = cliente.getCajero();
        cajero.depositar(monto);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                saldoLabel.setText("Saldo actual: $" + cajero.getSaldo());
            }
        });
    }
}
