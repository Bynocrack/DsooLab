import java.time.LocalDate;

public class Prestamo {
    Libro libro;
    Usuario usuario;
    LocalDate fechaPrestamo;

    
    // Constructor fecha 
    public Prestamo(Libro libro, Usuario usuario, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Getters
    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Setters
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    //toString
    @Override
    public String toString() {
        return "Prestamo:\n" +
               "Libro: " + libro.getTitulo() + " (ISBN: " + libro.getISBN() + ")\n" +
               "Usuario: " + usuario.getNombre() + " (ID: " + usuario.getId() + ")\n" +
               "Fecha préstamo: " + fechaPrestamo;
    }
}