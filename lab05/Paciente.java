public class Paciente {
    private String codigo;
    private String nombre;
    private int edad;
    private String numeroDocumento;

    public Paciente(String codigo, String nombre, int edad, String numeroDocumento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.numeroDocumento = numeroDocumento;
    }

    public void mostrarInformacion() {
        System.out.println("--- Información del Paciente ---");
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Documento: " + numeroDocumento);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}