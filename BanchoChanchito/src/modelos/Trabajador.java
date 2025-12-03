package modelos;

import java.util.*;
public class Trabajador extends Usuario { // Clase Trabajador hereda de Usuario
    private Scanner sc = new Scanner(System.in);

    // Constructor de la clase Trabajador
    public Trabajador(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
    }

    //Metodo para registrar depositos y retiros
    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(cuenta, monto, cliente, this);
    }

    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(cuenta, monto, cliente, this);
    }

    //Metodo para crear nuevas cuentas
    public Cuenta crearCuenta(ArrayList<Cliente> titulares, int numero) {
        /*System.out.print("Saldo inicial: ");

        //Validar monto inicial
        while (!sc.hasNextFloat()) {
            sc.next();
            System.out.print("Monto inválido: ");
        }
        float saldo = sc.nextFloat();
        sc.nextLine();*/
        
        float saldo = 500;

        String codigo = "C" + numero; //Genera el codigo de la cuenta
        Cuenta c = new Cuenta(codigo, "Ahorros", saldo, titulares); //Crea la cuenta
        return c;
    }
}
