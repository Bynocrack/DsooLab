package modelo;
public class AutoServicio {

    // Atributo de la clase AutoServicio
    private String tipo;

    // Constructor de la clase AutoServicio
    public AutoServicio(String tipo) {
        this.tipo = tipo;
    }

    // Método para registrar un depósito sin empleado
    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        Deposito d = new Deposito(cuenta, monto, cliente, null);
        d.procesar();
        return d;
    }

    // Método para registrar un retiro sin empleado
    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        Retiro r = new Retiro(cuenta, monto, cliente, null);
        r.procesar();
        return r;
    }

    @Override
    public String toString() {
        return tipo; 
    }
}
