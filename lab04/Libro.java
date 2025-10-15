import java.util.*;

public class Libro {
    private String titulo;
    private String autor;
    private int ISBN;
    private boolean disponible;

    public Libro(String titulo, String autor, int ISBN, boolean disponible){
        this.titulo=titulo;
        setAutor(autor);
        this.ISBN=ISBN;
        this.disponible=disponible;
    }
    public Libro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Ingreso de Datos de Nuevo Libro ---");

        System.out.print("Ingrese el título: ");
        this.titulo = scanner.nextLine();

        System.out.print("Ingrese el nombre del autor: ");
        String autorTemp = scanner.nextLine();
        setAutor(autorTemp);

        System.out.print("Ingrese el ISBN (solo números): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error. Por favor, ingrese un número entero para el ISBN:");
            scanner.next();
        }
        this.ISBN = scanner.nextInt();
        scanner.nextLine();

        this.disponible = true;
        System.out.println("--- Libro creado exitosamente ---");
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    public void setAutor(String autor){
        // ^   -> Inicio de la cadena.
        // [a-zA-Z] -> Cualquier letra de la 'a' a la 'z', mayúscula o minúscula.
        // +   -> Una o más veces.
        // $   -> Fin de la cadena.
        if(autor.matches("^[a-zA-Z]+$")){
            this.autor=autor;
        }else{
            System.out.println("Caractéres no válidos, ingrese e nuevo el nombre");
        }
    }
    public void ISBN(int ISBN){
        this.ISBN=ISBN;
    }
    public void setDisponible(boolean disponible){
        this.disponible=disponible;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public int getISBN(){
        return ISBN;
    }
    //Retorna true o false dependiendo que se asignó al inicio
    public boolean estaDisponible(){
        return disponible;
    }
    @Override
    public String toString() {
        return "Título: " +titulo +" / Autor: " +autor +" / ISBN: " +ISBN +" / Disponibilidad: " +disponible;
    }
}
