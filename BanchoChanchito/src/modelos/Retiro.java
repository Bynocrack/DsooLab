package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Retiro extends Transaccion { // Clase Retiro hereda de Transaccion

    // Constructor de la clase Retiro 
    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Trabajador encargado) throws Exception {
        //Genera un ID único
        DateTimeFormatter plantilla = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS");
        super("R-" + LocalDateTime.now().format(plantilla), 
                LocalDateTime.now().format(plantilla), monto,
                encargado instanceof Empleado, encargado, cuenta, cliente, null, true);
    }
    public Retiro(String id, String fechaHora, float monto,
                       boolean atendidoPorEmpleado, Trabajador encargado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal) {

        super(id, fechaHora, monto, atendidoPorEmpleado, encargado, cuenta, cliente, canal);
    }

    @Override
    public void procesar() {

        //Intenta debitar el monto de la cuenta
        cuenta.debitar(monto, this);
    }
}
