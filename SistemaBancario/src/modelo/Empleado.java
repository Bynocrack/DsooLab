package modelo;
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
    public void mostrarPermisos() {
        System.out.println("\n================================");
        System.out.println(" PERMISOS DEL EMPLEADO: ");
        System.out.println("\n================================");
        System.out.println("SI Consultar resumen de cuentas de clientes");
        System.out.println("SI Ver movimientos de cuentas de clientes");
        System.out.println("SI Registrar depósitos y retiros");
        System.out.println("SI Crear nuevas cuentas para clientes");
        System.out.println("SI Registrar nuevos clientes");
    }
}
