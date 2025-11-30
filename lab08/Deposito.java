import java.time.LocalDateTime;

public class Deposito extends Transaccion {

    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        super("D-" + System.currentTimeMillis(),
                LocalDateTime.now(),
                monto,
                true,
                encargado,
                cuenta,
                cliente,
                null);
    }

    @Override
    public void procesar() {
        cuenta.acreditar(monto, this);
        System.out.println("✅ Depósito exitoso.");
    }
}
