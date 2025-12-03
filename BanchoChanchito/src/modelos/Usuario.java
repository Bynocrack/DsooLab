package modelos;

public  class Usuario extends Persona { // Clase Usuario hereda de Persona
    
    // Atributos de la clase Usuario
    protected String usuario;
    protected String contraseña;
    protected String estado;

    // Constructor de la clase Usuario
    public Usuario(String dni, String nombre, String direccion,
               String telefono, String email,
               String usuario, String contraseña) {
    super(dni, nombre, direccion, telefono, email);
    this.usuario = usuario;
    this.contraseña = contraseña;
    this.estado = "INACTIVO"; 
    }

    // Métodos getters
    public String getUsuario() { return usuario; }
    public String getEstado() { return estado; }
    public String getContraseña() { return contraseña; }

    // Métodos setters
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public void setEstado(String estado) { this.estado = estado; }

    // Método login para iniciar sesión
    public void login (String usuario, String contraseña) throws Exception {
        if(this.usuario.equals(usuario) && this.contraseña.equals(contraseña)){
            this.estado = "ACTIVO"; // Cambia el estado a ACTIVO al iniciar sesión
        }
        else {
            throw new Exception("¡Usuario o contraseña incorrectos!");
        }
    }

    // Método para mostrar los datos del usuario (Administrador, Empleado, Cliente)
    public void mostrarDatos() {
        System.out.println("\n--- DATOS DEL USUARIO ---");
        System.out.println("Nombre del Usuario: " + usuario);
        System.out.println("Nombre y Apellido: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Estado: " + estado);
    }

    public String mostrarPermisos() {
        return "Hola";
    }
}