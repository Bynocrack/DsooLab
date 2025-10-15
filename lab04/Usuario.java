// Importamos las librerias necesarias
import java.util.*;

public class Usuario {
  // Declaramos el scanner asi como las propiedades que corresponden a cada usuario
  private Scanner sc = new Scanner(System.in);
  private String id;
  private String nombre;
  private ArrayList<Libro> librosPrestados;

  // Primer constructor, en caso se desee crear algun usuario estatico, es decir, que siempre este presente en el sistema
  public Usuario (String id, String nombre, ArrayList<Libro> historial) {
    setNombre(nombre);
    setId(id);
    setLibrosPrestados(historial);
  }

  // Constructor sobrecargado, para crear nuevos usuarios durante ejecucion del sistema
  public Usuario () {
    // Solicitamos nombre para el usuario
    System.out.print("Digite el nombre del nuevo usuario: ");
    String nombre = sc.nextLine();

    // Verificamos que sea un nombre valido, y en caso no lo sea, pedimos que se digite de nuevo
    while(!setNombre(nombre)) {
      System.out.print("Nombre no válido.\nIntente de nuevo: ");
      nombre = sc.nextLine();
    }

    // Solicitamos su id, y en caso no sea valido, seguimos solicitando hasta que se vuelva válido.
    System.out.print("Digite el id del nuevo usuario: ");
    String id = sc.next();
    while(!setId(id)) {
      System.out.print("Id no válido.\nIntente de nuevo: ");
      id = sc.next();
    }

    this.librosPrestados = new ArrayList<>();

    System.out.println("Listo, nuevo usuario creado!");
  }

  // Metodo para tomar libros prestados
  public void tomarPrestado (Libro libro) {
    this.librosPrestados.add(libro);
    libro.setDisponible(false);
    return;
  }

  // Metodo para verificar que el usuario tenga posesión de un libro
  public boolean tiene(Libro libro) {
    // Buscamos el libro y si se encuentra, devolvemos true
    for (Libro prestado : this.librosPrestados) {
      if (libro.getISBN() == prestado.getISBN()) {
        return true;
      }
    }

    // En caso no se encuentre, devolvemos false
    return false;
  }

  // Metodo para devolver un libro
  public void devolver(Libro libro) {
    this.librosPrestados.remove(libro);
    libro.setDisponible(true);
  }

  // Setters
  public boolean setNombre(String nombre) {
    this.nombre = nombre;
    return true;
  }

  public boolean setId(String id) {
    this.id = id;
    return true;
  }

  public boolean setLibrosPrestados(ArrayList<Libro> historial) {
    this.librosPrestados = historial;
    return true;
  }

  // Getters
  public String getNombre() {
    return this.nombre;
  }

  public String getId() {
    return this.id;
  }

  public ArrayList<Libro> getLibrosPrestados() {
    return this.librosPrestados;
  }

  @Override
  // Metodo toString 
  public String toString() {
    return "Usuario: " + this.nombre + "\nId: " + this.id + "Libros en su posesión: " + librosPrestados;
  }
}
