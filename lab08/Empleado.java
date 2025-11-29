import java.util.*;
public class Empleado extends Usuario {
    private String idEmpleado;
    private Scanner sc = new Scanner(System.in);

    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idEmpleado = "EM" + dni;
    }

    public String getIdEmpleado() { return idEmpleado; }

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
    @Override
    public void mostrarPermisos() {
        System.out.println("\n================================");
        System.out.println(" PERMISOS DEL EMPLEADO: ");
        System.out.println("\n================================");
        System.out.println("SI Consultar resumen de cuentas de clientes");
        System.out.println("SI Ver movimientos de cuentas de clientes");
        System.out.println("SI Registrar depósitos y retiros");
        System.out.println("SI Crear nuevas cuentas para clientes");
        System.out.println("SI Registrar nuevos clientes");
    }
}
