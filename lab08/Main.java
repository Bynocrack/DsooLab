import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Banco banco = new Banco();

        // DATOS PREDETERMINADOS
        Empleado emp = new Empleado("12345678","Carlos López","Av. Lima 101","999999999","empleado@banco.com","EMP01","Cajero");
        banco.empleados.add(emp);

        Cliente cliente = new Cliente("87654321","Brayan Motta","Cayma - Arequipa","912345678","brayan@gmail.com","CLI01");
        banco.clientes.add(cliente);

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);
        Cuenta cuenta = banco.crearCuenta(titulares, 500);
        cliente.agregarCuenta(cuenta);

        String op;

        do {
            System.out.println("\n===============================");
            System.out.println("      SISTEMA DEL BANCO");
            System.out.println("===============================");
            System.out.println("1. Listar clientes");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Listar empleados");
            System.out.println("4. Registrar empleado");
            System.out.println("5. Crear cuenta");
            System.out.println("6. Operaciones bancarias");
            System.out.println("7. Resumen de cuenta");
            System.out.println("8. Filtrar movimientos");
            System.out.println("9. Salir");
            System.out.print("Opción: ");

            op = sc.nextLine();

            switch (op) {

                case "1" -> banco.listarClientes();
                case "2" -> banco.registrarClienteDesdeTeclado();
                case "3" -> banco.listarEmpleados();
                case "4" -> banco.registrarEmpleadoDesdeTeclado();
                case "5" -> banco.crearCuentaDesdeTeclado();
                case "6" -> banco.operaciones();
                case "7" -> banco.mostrarResumenCuenta();
                case "8" -> banco.filtrarMovimientos();

                case "9" -> System.out.println("Saliendo...");
                default -> System.out.println(" Opción inválida.");
            }

        } while (!op.equals("9"));
    }
}