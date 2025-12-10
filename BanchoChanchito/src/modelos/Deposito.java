package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposito extends Transaccion { // Clase Deposito hereda de Transaccion

    // Constructor de la clase Deposito
    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Trabajador encargado) throws Exception {
        //Genera un ID único
        DateTimeFormatter plantilla = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS");
        super("D-" + LocalDateTime.now().format(plantilla),
                LocalDateTime.now().format(plantilla), monto, encargado instanceof Empleado, encargado,
                cuenta, cliente, null, true);
    }
    public Deposito(String id, String fechaHora, float monto,
                       boolean atendidoPorEmpleado, Trabajador encargado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal) {

        super(id, fechaHora, monto, atendidoPorEmpleado, encargado, cuenta, cliente, canal);
    }

    @Override
    // Método para procesar el depósito
    public void procesar() {
        cuenta.acreditar(monto, this);
    }
}
