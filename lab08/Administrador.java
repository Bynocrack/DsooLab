import java.util.*;
public class Administrador extends Trabajador{
    private String idAdministrador;
    private Scanner sc = new Scanner(System.in);

    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = "AD" + dni;
    }

    public String getIdAdministrador() { return idAdministrador; }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n================================");
        System.out.println(" PERMISOS DEL ADMINISTRADOR: ");
        System.out.println("\n================================");
        System.out.println("SI Consultar resumen de cuentas de clientes");
        System.out.println("SI Ver movimientos de cuentas de clientes");
        System.out.println("SI Registrar depósitos y retiros");
        System.out.println("SI Crear nuevas cuentas para clientes");
        System.out.println("SI Registrar nuevos clientes");
        System.out.println("SI Contratar y despedir empleados");
    }
}
