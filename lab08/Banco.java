import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    // Atributos - listas de clientes, empleados, administradores y cuentas
    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Administrador> administradores = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Método para mostrar los datos de un usuario (Administrador/Empleado/Cliente)
    public void menuMostrarDatos() {
        System.out.println("\n--- MOSTRAR DATOS DEL USUARIO ---");
        System.out.println("1. Administradores");
        System.out.println("2. Empleados");
        System.out.println("3. Clientes");
        System.out.println("0. Regresar");
        System.out.print("Opción: ");
        int op = leerInt();
        switch (op) {
            case 1 -> {
                System.out.println("\n--- LISTA DE ADMINISTRADORES ---");
                for (Administrador a : administradores) {
                    System.out.println(a.getIdAdministrador()+" - " + a.getNombre());
                }
                System.out.println("Ingrese la ID del administrador: ");
                    String id = sc.nextLine();
                for (Administrador a : administradores) {
                    if (a.getIdAdministrador().equals(id)) {
                        System.out.println("Admninistrador seleccionado: " + a.getNombre());
                        a.mostrarDatos();
                        return;
                    }
                }
                System.out.println("No existe ese administrador.");

            }
            case 2 -> {
                System.out.println("\n--- LISTA DE EMPLEADOS ---");
                for (Empleado e : empleados) {
                    System.out.println(e.getIdEmpleado()+" - " + e.getNombre());
                }
                System.out.println("Ingrese la ID del empleado: ");
                    String id = sc.nextLine();
                for (Empleado e : empleados) {
                    if (e.getIdEmpleado().equals(id)) {
                        System.out.println("Empleado seleccionado: " + e.getNombre());
                        e.mostrarDatos();
                        return;
                    }
                }
                System.out.println("No existe ese empleado.");
            }
            case 3 -> {
                System.out.println("\n--- LISTA DE CLIENTES ---");
                for (Cliente c : clientes) {
                    System.out.println(c.getIdCliente()+" - " + c.getNombre());
                }
                System.out.println("Ingrese la ID del cliente: ");
                    String id = sc.nextLine();
                for (Cliente c : clientes) {
                    if (c.getIdCliente().equals(id)) {
                        System.out.println("Cliente seleccionado: " + c.getNombre());
                        c.mostrarDatos();
                        return;
                    }
                }
                System.out.println("No existe ese cliente.");
            }
            case 0 -> {
                System.out.println("Regresando...");
            }
            default -> System.out.println(" Opción inválida.");
        }
    }

    //Metodos de clientes

        // Método para listar los clientes
        public void listarClientes() {
            System.out.println("\n--- LISTA DE CLIENTES ---");
            for (Cliente c : clientes) {
                System.out.println(c.getIdCliente() + " - " + c.getNombre());
            }
        }

        // Método para registrar un cliente
        public void registrarCliente(Trabajador trabajador) {
            Cliente nuevo = trabajador.registrarClienteDesdeTeclado();
            clientes.add(nuevo);

            System.out.println("Cliente registrado correctamente.");
        }

        // Método para seleccionar un cliente por ID
        public Cliente seleccionarClientePorID() {
            listarClientes();
            System.out.print("ID Cliente: ");
            String id = sc.nextLine();

            for (Cliente c : clientes)
                if (c.getIdCliente().equals(id))
                    return c;

            System.out.println("No existe ese cliente.");
            return null;
        }
        // Método para seleccionar un cliente por usuario
        public Cliente seleccionarClientePorUsuario(String usuario) {
            for (Cliente c : clientes)
                if (c.getUsuario().equals(usuario))
                    return c;

            System.out.println("No existe ese cliente.");
            return null;
        }

        // Método para el menú del cliente
        public void menuCliente() {
            System.out.println("Por favor digite su usuario");
            String usuario = sc.nextLine();

            Cliente clienteLogueado = seleccionarClientePorUsuario(usuario);
            if (clienteLogueado == null) return;

            System.out.println("Por favor digite su contraseña");
            String contraseña = sc.nextLine();
            
            if(!(clienteLogueado.login(usuario, contraseña))){
                return;
            }
            int op;
            do {
                System.out.println("\n--- MENÚ CLIENTE (" + clienteLogueado.getNombre() + ") ---");
                System.out.println("1. Consultar resumen de cuentas");
                System.out.println("2. Ver/Filtrar movimientos de una cuenta");
                System.out.println("3. Volver al menú principal");
                System.out.println("4. Ver permisos del cliente");
                System.out.print("Opción: ");

                op = leerInt();

                switch (op) {
                    case 1 -> mostrarResumenCuenta(clienteLogueado);
                    case 2 -> filtrarMovimientos(clienteLogueado);
                    case 3 -> System.out.println("Volviendo...");
                    case 4 -> clienteLogueado.mostrarPermisos();    
                    default -> System.out.println(" Opción inválida.");
                }
            } while (op != 3);
        }

    // Métodos de empleados 

        // Método para listar los empleados
        public void listarEmpleados() {
            System.out.println("\n--- LISTA DE EMPLEADOS ---");
            for (Empleado e : empleados) {
                System.out.println(e.getIdEmpleado() + " - " + e.getNombre());
            }
        }

        // Método para registrar un empleado
        public void registrarEmpleado(Administrador administrador) {
            Empleado nuevo = administrador.registrarEmpleadoDesdeTeclado();
            empleados.add(nuevo);

            System.out.println("Empleado registrado correctamente.");
        }

        // Método para seleccionar un empleado por ID
        public Empleado seleccionarEmpleadoPorID() {
            listarEmpleados();
            System.out.print("ID Empleado: ");
            String id = sc.nextLine();

            for (Empleado e : empleados)
                if (e.getIdEmpleado().equals(id))
                    return e;

            System.out.println("No existe ese empleado.");
            return null;
        }

        // Método para seleccionar un empleado por usuario
        public Empleado seleccionarEmpleadoPorUsuario(String usuario) {
            for (Empleado e : empleados)
                if (e.getUsuario().equals(usuario))
                    return e;

            System.out.println("No existe ese empleado.");
            return null;
        }

        // Método para el menú del empleado
        public void menuEmpleado() {
            System.out.println("Por favor digite su usuario");
            String usuario = sc.nextLine();
            Empleado empleadoLogueado = seleccionarEmpleadoPorUsuario(usuario);
            if (empleadoLogueado == null) return;
            
            System.out.println("Por favor digite su contraseña");
            String contraseña = sc.nextLine();
            if(!(empleadoLogueado.login(usuario, contraseña))) return;
            
            int op;
            do {
                System.out.println("\n--- MENÚ EMPLEADO (" + empleadoLogueado.getNombre() + ") ---");
                System.out.println("1. Listar clientes");
                System.out.println("2. Registrar cliente");
                System.out.println("3. Crear cuenta");
                System.out.println("4. Realizar Operaciones Bancarias (Depósito/Retiro)");
                System.out.println("5. Ver permisos del empleado");
                System.out.println("6. Volver al menú principal");
                System.out.print("Opción: ");

                op = leerInt();

                switch (op) {
                    case 1 -> listarClientes();
                    case 2 -> registrarCliente(empleadoLogueado);
                    case 3 -> crearCuentaDesdeTeclado(empleadoLogueado);
                    case 4 -> operaciones(empleadoLogueado);
                    case 5 -> empleadoLogueado.mostrarPermisos();
                    case 6 -> System.out.println("Volviendo...");
                    default -> System.out.println(" Opción inválida.");
                }
            } while (op != 6);
        }

    // Métodos de administradores
        // Método para seleccionar un administrador por usuario
        public Administrador seleccionarAdministradorPorUsuario(String usuario) {
            for (Administrador a : administradores)
                if (a.getUsuario().equals(usuario))
                    return a;

            System.out.println("No existe ese administrador.");
            return null;
        }
        
        // Método para el menú del administrador
        public void menuAdministrador() {
        System.out.println("Por favor digite su usuario");
        String usuario = sc.nextLine();
        Administrador administradorLogueado = seleccionarAdministradorPorUsuario(usuario);
        if (administradorLogueado == null) return;

        System.out.println("Por favor digite su contraseña");
        String contraseña = sc.nextLine();
        if(!(administradorLogueado.login(usuario, contraseña))) return;

            int op;
            do {
                System.out.println("\n--- MENÚ ADMINISTRADOR(" + administradorLogueado.getNombre() + ") ---");
                System.out.println("1. Listar clientes");
                System.out.println("2. Registrar cliente");
                System.out.println("3. Crear cuenta");
                System.out.println("4. Realizar Operaciones Bancarias (Depósito/Retiro)");
                System.out.println("5. Contratar Empleado");
                System.out.println("6. Despedir Empleado");
                System.out.println("7. Ver permisos del administrador");
                System.out.println("8. Listar empleados");
                System.out.println("9. Mostrar datos de un usuario(Administrador/Empleado/Cliente)");
                System.out.println("0. Volver al menú principal");
                System.out.print("Opción: ");

                op = leerInt();

                switch (op) {
                    case 1 -> listarClientes();
                    case 2 -> registrarCliente(administradorLogueado);
                    case 3 -> crearCuentaDesdeTeclado(administradorLogueado);
                    case 4 -> operaciones(administradorLogueado);
                    case 5 -> registrarEmpleado(administradorLogueado);
                    case 6 -> administradorLogueado.despedirEmpleado(this);
                    case 7 -> administradorLogueado.mostrarPermisos();
                    case 8 -> listarEmpleados();
                    case 9 -> menuMostrarDatos();
                    case 0 -> System.out.println("Volviendo...");
                    default -> System.out.println(" Opción inválida.");
                }
            } while (op != 0);

        }

    // Métodos de cuentas

        // Método para crear una cuenta desde teclado
        public void crearCuentaDesdeTeclado(Trabajador trabajador) {
            ArrayList<Cliente> titulares = new ArrayList<>();
            while (true) {
                System.out.println("Desea crear una cuenta mancomunada? (s/n)");
                char op = sc.nextLine().toUpperCase().charAt(0);
                if (op == 'N') {
                    Cliente titular = seleccionarClientePorID();
                    if (titular == null) return;

                    titulares.add(titular);
                    break;
                }
                else if (op == 'S') {
                    while (true) {
                        Cliente titular = seleccionarClientePorID();
                        if (titular == null) continue;

                        titulares.add(titular);
                        System.out.println("Desea seguir ingresando titulares? (S/n)");
                            op = sc.nextLine().toUpperCase().charAt(0);
                        if (op == 'N') break;
                    }
                    break;
                }
            }

            int numero = cuentas.size()+1;

            Cuenta cuenta = trabajador.crearCuenta(titulares, numero);
                for (Cliente titular : titulares) {
                    titular.agregarCuenta(cuenta);
                }
            cuentas.add(cuenta);
            System.out.println("Cuenta creada con número: " + cuenta.getNumero());
        }

        // Método para realizar operaciones bancarias
        public void operaciones(Trabajador trabajador) {

            Cliente cli = seleccionarClientePorID();
            if (cli == null) return;

            cli.mostrarResumenCuentas();
            Cuenta cuenta = cli.seleccionarCuenta();
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
                        Deposito d = trabajador.registrarDeposito(cuenta, m, cli);
                        d.procesar();
                    }

                    case 3 -> {
                        System.out.print("Monto retiro: ");
                        float m = leerFloat();
                        Retiro r = trabajador.registrarRetiro(cuenta, m, cli);
                        r.procesar();
                    }

                    case 4 -> cuenta.mostrarMovimientos();
                }

            } while (op != 5);
        }

        // Método para mostrar el resumen de cuentas de un cliente
        public void mostrarResumenCuenta(Cliente cli) {
            if(cli.mostrarResumenCuentas()) return;
            Cuenta cuenta = cli.seleccionarCuenta();
            if (cuenta == null) return;

            cuenta.mostrarResumen();
        }

        // Método para filtrar movimientos de una cuenta de un cliente
        public void filtrarMovimientos(Cliente cli) {
            if(cli.mostrarResumenCuentas()) return;
            Cuenta cuenta = cli.seleccionarCuenta();
            if (cuenta == null) return;
            cuenta.filtrarMovimientos();
        }

    //Metodos Auxiliares
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