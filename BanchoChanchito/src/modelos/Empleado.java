package modelos;

public class Empleado extends Trabajador { // Clase Empleado hereda de Trabajador

    // Atributo de la clase empleado
    private String idEmpleado;

    // Constructor de la clase Empleado
    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idEmpleado = "EM" + dni;
    }

    // Método getter
    public String getIdEmpleado() { return idEmpleado; }

    @Override
    // Método para mostrar los permisos del empleado
    public String mostrarPermisos() {
        return "================================" +
        "\n PERMISOS DEL EMPLEADO: " +
        "\n================================" +
        "\nSI Consultar resumen de cuentas de clientes" +
        "\nSI Ver movimientos de cuentas de clientes" +
        "\nSI Registrar depósitos y retiros" +
        "\nSI Crear nuevas cuentas para clientes" +
        "\nSI Registrar nuevos clientes";
    }
}
