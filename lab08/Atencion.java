import java.time.LocalDateTime;

public class Atencion {

    // Atributos de la clase Atencion
    private String id;
    private LocalDateTime fechaHora;
    private Cliente cliente;
    private Empleado empleado;
    private String motivo;
    private String notas = "";

    // Constructor de la clase Atencion
    public Atencion(Cliente cliente, String motivo, Empleado empleado) {
        this.id = "A-" + System.currentTimeMillis();
        this.fechaHora = LocalDateTime.now();
        this.cliente = cliente;
        this.motivo = motivo;
        this.empleado = empleado;
    }

    // Métodos getters
    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public String getMotivo() { return motivo; }
    public String getNotas() { return notas; }

    // Método para agregar una nota 
    public void agregarNota(String nota) {
        notas += nota + "\n";
    }
}
