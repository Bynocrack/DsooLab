import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Banco banco = new Banco();

        Empleado emp = new Empleado("61761556","Diego Cahuana","Yura - Arequipa","997134305","dcauhana@banco.com","DegoX", "AM");
        banco.empleados.add(emp);

        Cliente cliente = new Cliente("60060857","Brayan Motta","Socabaya - Arequipa","945846028","brayan@gmail.com","bmotta","CHOCA");
        banco.clientes.add(cliente);

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);
        Cuenta cuenta = banco.crearCuenta(titulares, 500);
        cliente.agregarCuenta(cuenta);

        int op;

        do {
            System.out.println("\n===============================");
            System.out.println("      SISTEMA DEL BANCO");
            System.out.println("===============================");
            System.out.println("1. Entrar como TRABAJADOR");
            System.out.println("2. Entrar como CLIENTE");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                op = 0; 
            }

            switch (op) {

                case 1 -> banco.menuTrabajador();
                case 2 -> banco.menuCliente();

                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println(" Opción inválida.");
            }

        } while (op != 3);
    }
}