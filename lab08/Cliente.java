import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Usuario {

    private String idCliente;
    private ArrayList<Cuenta> cuentas;
    private Scanner sc = new Scanner(System.in);

    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String usuario, String contraseña) {

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idCliente = "CL" + dni;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getIdCliente() { return idCliente; }

    public float getSaldoTotal() {
        float total = 0;
        for (Cuenta c : cuentas) total += c.getSaldo();
        return Math.round(total * 100) / 100f;
    }
    public Cuenta seleccionarCuenta() {
        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();

        for (Cuenta c : cuentas)
            if (c.getNumero().equals(num))
                return c;

        System.out.println("No existe esa cuenta.");
        return null;
    }
    public boolean mostrarResumenCuentas() {
      if(cuentas.isEmpty()) {
        System.out.println("El cliente no tiene cuentas registradas");
        return false;
      }
        System.out.println("\n--- CUENTAS DEL CLIENTE " + nombre + " ---");
        for (Cuenta c : cuentas) {
            System.out.println(c.getNumero() + " - Saldo: S/ " + c.getSaldo());
        }
        return true;
    }
    @Override
    public void mostrarPermisos() {
        System.out.println("\n================================");
        System.out.println(" PERMISOS DEL CLIENTE: ");
        System.out.println("\n================================");
        System.out.println("SI Consultar resumen de sus cuentas");
        System.out.println("SI Ver movimientos de sus cuentas");
        System.out.println("SI Consultar saldo total");
    }
}
