package pkg05depositos;

class Cliente {
    private String nombre;
    private CajeroAutomatico cajero;

    public Cliente(String nombre, CajeroAutomatico cajero) {
        this.nombre = nombre;
        this.cajero = cajero;
    }

    public String getNombre() {
        return nombre;
    }

    public CajeroAutomatico getCajero() {
        return cajero;
    }
}
