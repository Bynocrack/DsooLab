import java.time.LocalDateTime;

public class Transaccion {

    protected String id;
    protected LocalDateTime fechaHora;
    protected float monto;
    protected boolean atendidoPorEmpleado;
    protected Usuario encargado;
    protected Cuenta cuenta;
    protected Cliente cliente;
    protected AutoServicio canal;

    public Transaccion() {
        this.fechaHora = LocalDateTime.now();
    }

    public Transaccion(String id, LocalDateTime fechaHora, float monto,
                       boolean atendidoPorEmpleado, Usuario encargado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal) {

        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.atendidoPorEmpleado = atendidoPorEmpleado;
        this.encargado = encargado;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.canal = canal;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public float getMonto() { return monto; }
    public boolean getAtendidoPorEmpleado() { return atendidoPorEmpleado; }
    public Usuario getEncargado() { return encargado; }
    public Cuenta getCuenta() { return cuenta; }
    public Cliente getCliente() { return cliente; }
    public AutoServicio getCanal() { return canal; }

    public String getResumen() {
        return "ID: " + id +
                "\nFecha: " + fechaHora +
                "\nMonto: S/ " + monto +
                "\nAtendido por Empleado: " + (atendidoPorEmpleado ? "Si" : "No") +
                "\nEncargado: " + encargado.getNombre() +
                "\nCliente: " + cliente.getNombre() +
                "\nCuenta: " + cuenta.getNumero();
    }

    public void procesar() {
        System.out.println("Procesando transacción...");
    }
}

