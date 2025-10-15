import java.util.*;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Datos inicializados
        biblioteca.getCatalogo().add(new Libro("Cien años de soledad", "García", 1001, true));
        biblioteca.getCatalogo().add(new Libro("El principito", "Exupery", 1002, true));
        biblioteca.getUsuarios().add(new Usuario("U01", "Jose", new ArrayList<>()));
        biblioteca.getUsuarios().add(new Usuario("U02", "Maria", new ArrayList<>()));
        
        //Menú de opciones, el bucle continúa hasta que el usuario elige la opción Salir
        int opcion;
        do {
            System.out.println("\n===== BIBLIOTECA =====");
            System.out.println("1. Listar libros");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Registrar nuevo libro");
            System.out.println("4. Registrar nuevo usuario");
            System.out.println("5. Prestar libro");
            System.out.println("6. Devolver libro");
            System.out.println("0. Salir");
            System.out.println("======================");
            System.out.print("Elige una opción: ");

            opcion = scan.nextInt();
            scan.nextLine(); 

            switch (opcion) {
                case 1:
                    biblioteca.mostrarCatalogo();
                    break;
                case 2:
                    biblioteca.mostrarUsuarios();
                    break;
                case 3:
                    biblioteca.agregarLibro(); 
                    break;
                case 4:
                    biblioteca.agregarUsuario(); 
                    break;
                case 5:
                    prestarLibro(biblioteca);
                    break;
                case 6:
                    devolverLibro(biblioteca);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    //Permite realizar el préstamo de un libro a un usuario y llama al método prestar() de la clase Biblioteca
    public static void prestarLibro(Biblioteca biblioteca) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPRÉSTAMO DE LIBRO");
        System.out.print("ISBN del libro a prestar: ");
        int isbn = scan.nextInt();
        scan.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scan.nextLine();
        biblioteca.prestar(isbn, idUsuario);
    }

    //Permite registrar la devolución de un libro prestado y luego llama al método devolver() de la clase Biblioteca
    public static void devolverLibro(Biblioteca biblioteca) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nDEVOLVER LIBRO");
        System.out.print("ISBN del libro a devolver: ");
        int isbn = scan.nextInt();
        scan.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scan.nextLine();
        biblioteca.devolver(isbn, idUsuario);
    }
}
