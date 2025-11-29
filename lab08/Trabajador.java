import java.util.*;
public class Trabajador extends Usuario {
    private Scanner sc = new Scanner(System.in);

    public Trabajador(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
    }

    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(cuenta, monto, cliente, this);
    }

    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(cuenta, monto, cliente, this);
    }

    public Cliente registrarClienteDesdeTeclado() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Contraseña Usuario: ");
        String contraseña = sc.nextLine();

        Cliente nuevo = new Cliente(dni, nombre, direccion, telefono, email, usuario, contraseña);
        return nuevo;
    }

    public Cuenta crearCuenta(ArrayList<Cliente> titulares, int numero) {
        System.out.print("Saldo inicial: ");

        while (!sc.hasNextFloat()) {
            sc.next();
            System.out.print("Monto inválido: ");
        }
        float saldo = sc.nextFloat();
        sc.nextLine();

        String codigo = "C" + numero;
        Cuenta c = new Cuenta(codigo, "Ahorros", saldo, titulares);
        return c;
    }
}
