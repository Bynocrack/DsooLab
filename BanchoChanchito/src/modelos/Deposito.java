package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposito extends Transaccion { // Clase Deposito hereda de Transaccion

    // Constructor de la clase Deposito
    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        //Genera un ID único
        DateTimeFormatter plantilla = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS");
        super("D-" + LocalDateTime.now().format(plantilla),
                LocalDateTime.now().format(plantilla),monto,encargado instanceof Empleado,encargado,
                cuenta,cliente,null);
    }

    @Override
    // Método para procesar el depósito
    public void procesar() {
        cuenta.acreditar(monto, this);
    }
}
