package modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Retiro extends Transaccion { // Clase Retiro hereda de Transaccion

    // Constructor de la clase Retiro 
    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        //Genera un ID único
        DateTimeFormatter plantilla = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS");
        super("R-" + LocalDateTime.now().format(plantilla), 
                LocalDateTime.now().format(plantilla),
                monto,
                encargado instanceof Empleado,
                encargado, cuenta, cliente, null);
    }

    @Override
    public void procesar() {

        //Intenta debitar el monto de la cuenta
        cuenta.debitar(monto, this);
    }
}
