package modelos;

public class Administrador extends Trabajador{ // Clase Administrador hereda de Trabajador

    // Atributos de la clase Administrador
    private String idAdministrador;

    // Constructor de la clase Administrador
    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario,
            String contraseña) throws Exception{
        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = "AD" + dni;
    }
    
    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario,
            String contraseña, String idAdministrador) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = idAdministrador;
    }

    // Método getter 
    public String getIdAdministrador() { return idAdministrador; }

    @Override
    // Método para mostrar los permisos del administrador
    public String mostrarPermisos() {
        return "================================" +
        "\n PERMISOS DEL ADMINISTRADOR: " +
        "\n================================" +
        "\nSI Consultar resumen de cuentas de clientes" +
        "\nSI Ver movimientos de cuentas de clientes" +
        "\nSI Registrar depósitos y retiros" +
        "\nSI Crear nuevas cuentas para clientes" +
        "\nSI Registrar nuevos clientes" +
        "\nSI Contratar y despedir empleados";
    }
}
