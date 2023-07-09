package pkg05depositos;

class CajeroAutomatico {
    private double saldo;

    public CajeroAutomatico() {
        saldo = 0.0;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void depositar(double monto) {
        saldo += monto;
    }

    public synchronized void retirar(double monto) {
        saldo -= monto;
    }
}