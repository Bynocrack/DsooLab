import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Banco banco = new Banco();

        // Se agregan datos iniciales (1 empleado, 1 cliente y 1 administrador)
        Empleado emp = new Empleado("61761556","Diego Cahuana","Yura - Arequipa","997134305","dcauhana@banco.com","DegoX", "Camargo");
        banco.empleados.add(emp);

        Cliente cliente = new Cliente("60060857","Brayan Motta","Socabaya - Arequipa","945846028","brayan@gmail.com","bmotta","CHOCA");
        banco.clientes.add(cliente);

        Administrador adm = new Administrador("34628123", "Atuncito", "Lima - San Isidro", "963852741", "at@gmail.com", "atun", "shh");
        banco.administradores.add(adm);

        int op;

        do {
            // Menú principal del sistema
            System.out.println("\n===============================");
            System.out.println(" BIENVENIDO A BANCO CHANCHITO");
            System.out.println("===============================");
            System.out.println("1. Entrar como ADMINISTRADOR");
            System.out.println("2. Entrar como EMPLEADO");
            System.out.println("3. Entrar como CLIENTE");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                // Se lee la opción del usuario
                op = sc.nextInt();

            } catch (InputMismatchException e) {
                // Si ingresa algo diferente de un número, se marca como inválido
                op = -1; 

            }
            sc.nextLine();

            switch (op) {

                case 1 -> banco.menuAdministrador();
                case 2 -> banco.menuEmpleado();
                case 3 -> banco.menuCliente();
                
                case 0 -> {
                    System.out.println("Saliendo...");
                    System.out.println("Gracias por usar Banco Chanchito :D . ¡Hasta luego!");
                }
                default -> System.out.println(" Opción inválida.");
            }
        } while (op != 0); // Repite hasta que se elija salir
        
    }
}
