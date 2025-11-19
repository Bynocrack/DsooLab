import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    // ============================================
    //   CLIENTES
    // ============================================

    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : clientes) {
            System.out.println(c.getIdCliente() + " - " + c.getNombre());
        }
    }

    public void registrarClienteDesdeTeclado() {
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

        System.out.print("ID Cliente: ");
        String idCliente = sc.nextLine();

        System.out.println("Contraseña Usuario: ");
        String contraseña = sc.nextLine();

        // Los setters con validación se ejecutan en el constructor de Cliente
        Cliente nuevo = new Cliente(dni, nombre, direccion, telefono, email, idCliente, contraseña);
        clientes.add(nuevo);

        System.out.println("✅ Cliente registrado correctamente.");
    }

    public Cliente seleccionarCliente() {
        listarClientes();
        System.out.print("ID Cliente: ");
        String id = sc.nextLine();

        for (Cliente c : clientes)
            if (c.getIdCliente().equals(id))
                return c;

        System.out.println("❌ No existe ese cliente.");
        return null;
    }

    // ============================================
    //   EMPLEADOS
    // ============================================

    public void listarEmpleados() {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Empleado e : empleados) {
            System.out.println(e.getIdEmpleado() + " - " + e.getNombre());
        }
    }

    public void registrarEmpleadoDesdeTeclado() {
        System.out.println("\n--- REGISTRAR EMPLEADO ---");

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

        System.out.print("ID Empleado: ");
        String idEmpleado = sc.nextLine();

        System.out.print("Contraseña Empleado");
        String contraseña = sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();



        // Los setters con validación se ejecutan en el constructor de Empleado
        Empleado nuevo = new Empleado(dni, nombre, direccion, telefono, email, idEmpleado, cargo, contraseña);
        empleados.add(nuevo);

        System.out.println("✅ Empleado registrado correctamente.");
    }

    public Empleado seleccionarEmpleado() {
        listarEmpleados();
        System.out.print("ID Empleado: ");
        String id = sc.nextLine();

        for (Empleado e : empleados)
            if (e.getIdEmpleado().equals(id))
                return e;

        System.out.println("❌ No existe ese empleado.");
        return null;
    }

    // ============================================
    //   CUENTAS
    // ============================================

    public void crearCuentaDesdeTeclado() {
        Cliente titular = seleccionarCliente();
        if (titular == null) return;

        System.out.print("Saldo inicial: ");
        float saldo = leerFloat();

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(titular);

        Cuenta cuenta = crearCuenta(titulares, saldo);
        titular.agregarCuenta(cuenta);

        System.out.println("✅ Cuenta creada con número: " + cuenta.getNumero());
    }

    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        String numero = "C" + (cuentas.size() + 1);
        Cuenta c = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(c);
        return c;
    }

    public Cuenta seleccionarCuenta(Cliente cli) {
        System.out.println("\n--- CUENTAS DE " + cli.getNombre() + " ---");
        for (Cuenta c : cli.getCuentas()) {
            System.out.println(c.getNumero() + " - Saldo: S/ " + c.getSaldo());
        }

        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();

        for (Cuenta c : cli.getCuentas())
            if (c.getNumero().equals(num))
                return c;

        System.out.println("❌ No existe esa cuenta.");
        return null;
    }

    // ============================================
    //   OPERACIONES
    // ============================================

    // Modificado para recibir el empleado
    public void operaciones(Empleado emp) {

        Cliente cli = seleccionarCliente();
        if (cli == null) return;

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        int op;
        do {
            System.out.println("\n--- OPERACIONES ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Retiro");
            System.out.println("4. Ver movimientos");
            System.out.println("5. Volver");
            System.out.print("Opción: ");

            op = leerInt();

            switch (op) {

                case 1 -> System.out.println("Saldo actual: S/ " + cuenta.getSaldo());

                case 2 -> {
                    System.out.print("Monto depósito: ");
                    float m = leerFloat();
                    // Se usa el empleado pasado por parámetro
                    Deposito d = emp.registrarDeposito(cuenta, m, cli);
                    d.procesar();
                }

                case 3 -> {
                    System.out.print("Monto retiro: ");
                    float m = leerFloat();
                    // Se usa el empleado pasado por parámetro
                    Retiro r = emp.registrarRetiro(cuenta, m, cli);
                    r.procesar();
                }

                case 4 -> cuenta.mostrarMovimientos();
            }

        } while (op != 5);
    }

    // Modificado para recibir el cliente
    public void mostrarResumenCuenta(Cliente cli) {

        if (cli.getCuentas().isEmpty()) {
            System.out.println("El cliente no tiene cuentas registradas.");
            return;
        }

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        cuenta.mostrarResumen();
    }

    // ============================================
    //   FILTRO DE MOVIMIENTOS
    // ============================================

    // Modificado para recibir el cliente
    public void filtrarMovimientos(Cliente cli) {

        if (cli.getCuentas().isEmpty()) {
            System.out.println("El cliente no tiene cuentas registradas.");
            return;
        }

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        cuenta.filtrarMovimientos();
    }


    // ============================================
    //   MENÚS DE ROL (Nuevos)
    // ============================================

    public void menuTrabajador() {
        Empleado empleadoLogueado = seleccionarEmpleado();
        
        if (empleadoLogueado == null) return;
        System.out.println("Por favor digite su contraseña");
        String contraseña = sc.nextLine();
        
        if(!(empleadoLogueado.autenticar(contraseña))){
            System.out.println("¡CONTRASEÑA EQUIVOCADA!");
            return;
        }
        int op;
        do {
            System.out.println("\n--- MENÚ TRABAJADOR (" + empleadoLogueado.getNombre() + ") ---");
            System.out.println("1. Listar clientes");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Listar empleados");
            System.out.println("4. Registrar empleado");
            System.out.println("5. Crear cuenta");
            System.out.println("6. Realizar Operaciones Bancarias (Depósito/Retiro)");
            System.out.println("7. Volver al menú principal");
            System.out.print("Opción: ");

            op = leerInt();

            switch (op) {
                case 1 -> listarClientes();
                case 2 -> registrarClienteDesdeTeclado();
                case 3 -> listarEmpleados();
                case 4 -> registrarEmpleadoDesdeTeclado();
                case 5 -> crearCuentaDesdeTeclado();
                case 6 -> operaciones(empleadoLogueado); // Pasar el empleado
                case 7 -> System.out.println("Volviendo...");
                default -> System.out.println(" Opción inválida.");
            }
        } while (op != 7);
    }

    public void menuCliente() {
        Cliente clienteLogueado = seleccionarCliente();
        if (clienteLogueado == null) return;
         System.out.println("Por favor digite su contraseña");
        String contraseña = sc.nextLine();
        
        if(!(clienteLogueado.autenticar(contraseña))){
            System.out.println("¡CONTRASEÑA EQUIVOCADA!");
            return;
        }
        int op;
        do {
            System.out.println("\n--- MENÚ CLIENTE (" + clienteLogueado.getNombre() + ") ---");
            System.out.println("1. Consultar resumen de cuentas");
            System.out.println("2. Ver/Filtrar movimientos de una cuenta");
            System.out.println("3. Volver al menú principal");
            System.out.print("Opción: ");

            op = leerInt();

            switch (op) {
                case 1 -> mostrarResumenCuenta(clienteLogueado); // Pasar el cliente
                case 2 -> filtrarMovimientos(clienteLogueado); // Pasar el cliente
                case 3 -> System.out.println("Volviendo...");
                default -> System.out.println(" Opción inválida.");
            }
        } while (op != 3);
    }


    // ============================================
    //   AUXILIARES
    // ============================================

    private int leerInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Número inválido: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    private float leerFloat() {
        while (!sc.hasNextFloat()) {
            sc.next();
            System.out.print("Monto inválido: ");
        }
        float f = sc.nextFloat();
        sc.nextLine();
        return f;
    }
}