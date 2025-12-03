package modelo;
import java.time.LocalDateTime;

public class Retiro extends Transaccion { // Clase Retiro hereda de Transaccion

    // Constructor de la clase Retiro 
    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        //Genera un ID único
        super("R-" + System.currentTimeMillis(), 
                LocalDateTime.now(),
                monto,
                (encargado instanceof Empleado) ? true : false,
                encargado, cuenta, cliente, null);
    }

    @Override
    public void procesar() {

        //Intenta debitar el monto de la cuenta
        if (cuenta.debitar(monto, this)) {
            System.out.println(" Retiro exitoso.");
        } else {
            System.out.println(" Saldo insuficiente.");
        }
    }
}
