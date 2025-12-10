package modelos;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Banco {

    // Atributos - listas de clientes, empleados, administradores y cuentas
    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Administrador> administradores = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void cargarDatos() throws Exception{
        try (Connection DB = new ConexionDB().conectar()) {
            PreparedStatement pstmt = DB.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Cliente c = new Cliente(rs.getString("dni"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getString("telefono"),
                        rs.getString("email"), rs.getString("usuario"), rs.getString("contrasena"),
                        rs.getString("idCliente"));
                clientes.add(c);
            }
            rs.close();
            pstmt.close();
            pstmt = DB.prepareStatement("SELECT * FROM empleados");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Empleado e = new Empleado(rs.getString("dni"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getString("telefono"),
                        rs.getString("email"), rs.getString("usuario"), rs.getString("contrasena"),
                        rs.getString("idEmpleado"));
                empleados.add(e);
            }
            rs.close();
            pstmt.close();
            pstmt = DB.prepareStatement("SELECT * FROM administradores");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Administrador a = new Administrador(rs.getString("dni"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getString("telefono"),
                        rs.getString("email"), rs.getString("usuario"), rs.getString("contrasena"),
                        rs.getString("idAdministrador"));
                administradores.add(a);
            }
            rs.close();
            pstmt.close();
            pstmt = DB.prepareStatement("SELECT * FROM cuentas");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String[] duenos = rs.getString("titulares").split(",");
                ArrayList<Cliente> titulares = new ArrayList<>();
                for (String dueno : duenos) {
                    try {
                        Cliente titular = seleccionarClientePorID(dueno);
                        titulares.add(titular);
                    } catch (Exception e) {
                        throw new Exception("Error al cargar el titular: " + dueno);
                    }
                }
                Cuenta c = new Cuenta(rs.getString("numero"), rs.getString("tipo"),
                        rs.getFloat("saldo"), titulares);
                cuentas.add(c);
                for (Cliente titular : titulares) {
                    titular.agregarCuenta(c);
                }
            } 
        } catch (Exception e) {
            throw e;
        }
    }

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
        public ArrayList<String> listarClientes() {
            ArrayList<String> out = new ArrayList<>();
            for (Cliente c : clientes) {
                out.add(c.getIdCliente() + " - " + c.getNombre());
            }
            return out;
        }

        // Método para registrar un cliente
        public void registrarCliente(Cliente nuevo) {
            clientes.add(nuevo);
        }

        // Método para seleccionar un cliente por ID
        public Cliente seleccionarClientePorID(String id) throws Exception{
            for (Cliente c : clientes)
                if (c.getIdCliente().equals(id))
                    return c;

            throw new Exception("No existe ese cliente");
        }
        // Método para seleccionar un cliente por usuario
        public Cliente seleccionarClientePorUsuario(String usuario) throws Exception {
            for (Cliente c : clientes)
                if (c.getUsuario().equals(usuario))
                    return c;

            throw new Exception("Usuario no encontrado");
        }

    // Métodos de empleados 

        // Método para listar los empleados
        public ArrayList<String> listarEmpleados() {
            ArrayList<String> out = new ArrayList<>();
            for (Empleado e : empleados) {
                out.add(e.getIdEmpleado() + " - " + e.getNombre());
            }
            return out;
        }
        
        public ArrayList<String> listarAdministradores() {
            ArrayList<String> out = new ArrayList<>();
            for (Administrador a : administradores) {
                out.add(a.getIdAdministrador() + " - " + a.getNombre());
            }
            return out;
        }

        // Método para registrar un empleado
        public void registrarEmpleado(Empleado nuevo) {
            empleados.add(nuevo);
        }

        // Método para seleccionar un empleado por ID
        public Empleado seleccionarEmpleadoPorID(String id) throws Exception {
            for (Empleado e : empleados)
                if (e.getIdEmpleado().equals(id))
                    return e;

            throw new Exception("Empleado no encontrado");
        }

        // Método para seleccionar un empleado por usuario
        public Empleado seleccionarEmpleadoPorUsuario(String usuario) throws Exception{
            for (Empleado e : empleados)
                if (e.getUsuario().equals(usuario))
                    return e;

            throw new Exception("Empleado no ecnontrado");
        }

    // Métodos de administradores
        // Método para seleccionar un administrador por usuario
        public Administrador seleccionarAdministradorPorUsuario(String usuario) throws Exception{
            for (Administrador a : administradores)
                if (a.getUsuario().equals(usuario))
                    return a;

            throw new Exception("Administrador no encontrado");
        }
        
        // Método para el menú del administrador
        public void menuAdministrador() {
                System.out.println("9. Mostrar datos de un usuario(Administrador/Empleado/Cliente)");
                menuMostrarDatos();

        }

    // Métodos de cuentas

        // Método para crear una cuenta desde teclado
        public void crearCuenta(Trabajador trabajador, ArrayList<Cliente> titulares) {
            try {
                int numero = cuentas.size()+1;
                float saldo = 0;
                saldo = Float.parseFloat(JOptionPane.showInputDialog("Digite el saldo inicial"));

                Cuenta cuenta = trabajador.crearCuenta(titulares, numero, saldo);
                    for (Cliente titular : titulares) {
                        titular.agregarCuenta(cuenta);
                    }
                cuentas.add(cuenta);
                JOptionPane.showMessageDialog(null, "Cuenta creada con número: " + cuenta.getNumero(), "Hecho", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
}