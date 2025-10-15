import java.util.*;

public class Biblioteca {

    // Atributos
    private ArrayList<Libro> catalogo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    // Constructor
    public Biblioteca() {
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    //Metodo que agrega libros
    public ArrayList<Libro> agregarLibro(){

        Libro nuevoLibro = new Libro();
            if(!existeLibro(nuevoLibro.getISBN())){
                catalogo.add(nuevoLibro);
                System.out.println("Libro agregado correctamente.");
            } else {
                System.out.println("El libro con ISBN " + nuevoLibro.getISBN() + " ya existe en el catalogo");
            }
        return catalogo;
    }

    //Metodo que agrega usuarios
    public ArrayList<Usuario> agregarUsuario(){

        Usuario nuevoUsuario = new Usuario();   
            if(!existeUsuario(nuevoUsuario.getId())){
                usuarios.add(nuevoUsuario);
                System.out.println("Usuario agregado correctamente.");
            }else{
                System.out.println("El usuario con ID " + nuevoUsuario.getId() + " ya existe en la lista.");
            }
        return usuarios;
    }

    //Getters
    public ArrayList<Libro> getCatalogo(){ return catalogo; }
    public ArrayList<Usuario> getUsuarios(){ return usuarios; }
    public ArrayList<Prestamo> getPrestamos(){ return prestamos; }
    
    //Metodo que verifica si existe un libro 
    public boolean existeLibro(int isbn){

        for (Libro libro : catalogo){
            if (libro.getISBN() == isbn){
                return true;
            }
        }
        return false;
    }

    //Metodo que verifica si existe un usuario
    public boolean existeUsuario(String id){

        for (Usuario usuario : usuarios){
            if (usuario.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    //Metodo que busca un libro por su ISBN
    public Libro buscarLibro(int isbn){

        for (Libro libro : catalogo) {
            if (libro.getISBN() == (isbn)){
                return libro;
            }
        }
        return null;
    }

    //Metodo que busca un usuario por su ID
    public Usuario buscarUsuario(String id){

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }

    //Metodo que presta un libro a un usuario
    public void prestar(int isbn, String idUsuario){

        Libro libro = buscarLibro(isbn);
        Usuario usuario = buscarUsuario(idUsuario);

            if (libro == null){
                System.out.println(" No se encontro el libro con ISBN "+ isbn); 
                    return;
            }
            if (usuario == null){
                System.out.println(" No se encontro el usuario con ID "+ idUsuario);
                    return;
            }
            if (!libro.estaDisponible()){
                System.out.println(" El libro ya esta prestado");
                    return;
            }

        usuario.tomarPrestado(libro);
        prestamos.add(new Prestamo(libro, usuario));
    }

    //Metodo que devuelve un libro prestado por un usuario
    public void devolver(int isbn, String idUsuario){

        Libro libro = buscarLibro(isbn);
        Usuario usuario = buscarUsuario(idUsuario);

            if (libro == null){
                System.out.println("No se encontro el libro con ISBN "+ isbn);
                    return;
            }
            if (usuario == null){
                System.out.println("No se encontro al usuario con ID "+ idUsuario);
                    return;
            }
            if (!usuario.tiene(libro)){
                System.out.println("El usuario no tiene este libro prestado");
                    return;
            }

        usuario.devolver(libro);
    }

    //Metodo que muestra la lista de libros
    public void mostrarCatalogo(){

        System.out.println("Catalogo de libros:");
            for (Libro libro : catalogo) {
                System.out.println(libro);
            }
    }

    //Metodo que muestra la lista de usuarios
    public void mostrarUsuarios(){

        System.out.println("Lista de usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
    }

    @Override
    public String toString() {
        
        return "Biblioteca{" +
                "catalogo=" + catalogo +
                ", usuarios=" + usuarios + ",prestamos=" + prestamos +
                '}';
    }
}