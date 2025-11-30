import java.time.LocalDateTime;

public class Retiro extends Transaccion {

    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        super("R-" + System.currentTimeMillis(),
                LocalDateTime.now(),
                monto,
                (encargado instanceof Empleado) ? true : false,
                encargado,
                cuenta,
                cliente,
                null);
    }

    @Override
    public void procesar() {
        if (cuenta.debitar(monto, this)) {
            System.out.println(" Retiro exitoso.");
        } else {
            System.out.println(" Saldo insuficiente.");
        }
    }
}
