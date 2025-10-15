import java.util.*;

public class Usuario {
  private Scanner sc = new Scanner(System.in);
  private String id;
  private String nombre;
  private ArrayList<Libro> librosPrestados;

  public Usuario (String id, String nombre, ArrayList<Libro> historial) {
    this.id = id;
    this.nombre = nombre;
    this.librosPrestados = historial;
  }

  public Usuario () {
    System.out.print("Digite el nombre del nuevo usuario: ");
    String nombre = sc.nextLine();
    while(!setNombre(nombre)) {
      System.out.print("Nombre no válido.\nIntente de nuevo: ");
      nombre = sc.nextLine();
    }
    System.out.print("Digite el id del nuevo usuario: ");
    String id = sc.next();
    while(!setId(id)) {
      System.out.print("Id no válido.\nIntente de nuevo: ");
      id = sc.next();
    }
    System.out.println("Listo, nuevo usuario creado!")
  }

  public boolean tomarPrestado (Libro libro) {
    if (!libro.estaDisponible()) {
      System.out.println("Ese libro no se encuentra disponible en este momento!");
      return false;
    }
    this.librosPrestados.add(libro);
    libro.setDisponible(false);
    return true;
  }

  public boolean tiene(Libro libro) {
    for (Libro prestado : this.librosPrestados) {
      if (libro.getTitulo().toLowerCase().equals(prestado.getTitulo().toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public boolean devolver(Libro libro) {
    if (!tiene(libro)) {
      System.out.println("Ese libro no se encuentra en posesión de " + usuario);
    }
    this.librosPrestados.remove(libro);
    libro.setDisponible(true);
  }

  public void toString() {
    System.out.println("Usuario: " + this.nombre + "\nId: " + this.id + "Libros en su posesión: ");
    for (Libro libro : this.librosPrestados) {
      System.out.println(libro.getTitulo());
    }
  }

  public boolean setNombre(String nombre) {
    this.nombre = nombre;
    return true;
  }

  public boolean setId(String id) {
    this.id = id;
    return true;
  }
}
