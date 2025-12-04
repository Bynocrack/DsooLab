package modelos;

import java.time.LocalDateTime;

public class Deposito extends Transaccion { // Clase Deposito hereda de Transaccion

    // Constructor de la clase Deposito
    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Usuario encargado) {
        //Genera un ID único
        super("D-" + System.currentTimeMillis(),
                LocalDateTime.now(),monto,true,encargado,
                cuenta,cliente,null);
    }

    @Override
    // Método para procesar el depósito
    public void procesar() {
        cuenta.acreditar(monto, this);
    }
}
