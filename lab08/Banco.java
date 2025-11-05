import java.util.ArrayList;

public class Banco {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public void registrarCliente(Cliente c) { clientes.add(c); }
    public void registrarEmpleado(Empleado e) { empleados.add(e); }

    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        String numero = "C" + (cuentas.size() + 1);
        Cuenta c = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(c);
        return c;
    }

    public Cuenta buscarCuentaPorNumero(String numero) {
        for (Cuenta c : cuentas)
            if (c.getNumero().equals(numero))
                return c;
        return null;
    }
}
