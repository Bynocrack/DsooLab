package modelos;

import java.util.*;
public class Administrador extends Trabajador{ // Clase Administrador hereda de Trabajador

    // Atributos de la clase Administrador
    private String idAdministrador;
    private Scanner sc = new Scanner(System.in);

    // Constructor de la clase Administrador
    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario, String contraseña) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = "AD" + dni;
    }

    // Método getter 
    public String getIdAdministrador() { return idAdministrador; }

    // Método para contratar a un nuevo empleado
    public Empleado registrarEmpleadoDesdeTeclado() {
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

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña Empleado: ");
        String contraseña = sc.nextLine();

        Empleado nuevo = new Empleado(dni, nombre, direccion, telefono, email, usuario, contraseña);
        return nuevo;
    }

    // Método para despedir a un empleado
    public void despedirEmpleado(Banco banco) {
        System.out.println("Ingrese el empleado a despedir:");
        Empleado emp = banco.seleccionarEmpleadoPorID();
        if (banco.empleados.remove(emp)) {
            System.out.println("Empleado " + emp.getNombre() + " despedido exitosamente.");
        } else {
            System.out.println("Empleado no encontrado en la lista.");
        }
    }

    @Override
    // Método para mostrar los permisos del administrador
    public String mostrarPermisos() {
        return "================================" +
        "\n PERMISOS DEL ADMINISTRADOR: " +
        "\n================================" +
        "\nSI Consultar resumen de cuentas de clientes" +
        "\nSI Ver movimientos de cuentas de clientes" +
        "\nSI Registrar depósitos y retiros" +
        "\nSI Crear nuevas cuentas para clientes" +
        "\nSI Registrar nuevos clientes" +
        "\nSI Contratar y despedir empleados";
    }
}
