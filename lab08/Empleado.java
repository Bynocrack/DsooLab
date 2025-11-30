public class Empleado extends Trabajador {
    private String idEmpleado;

    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idEmpleado = "EM" + dni;
    }

    public String getIdEmpleado() { return idEmpleado; }

    @Override
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
