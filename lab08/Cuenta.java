import java.util.ArrayList;
import java.util.Scanner;

public class Cuenta {

    // Atributos de la clase Cuenta
    private String numero;
    private String tipo;
    private float saldo;
    private ArrayList<Transaccion> movimientos;
    private ArrayList<Cliente> titulares;
    private Scanner sc = new Scanner(System.in);

    // Constructor de la clase Cuenta.
    public Cuenta(String numero, String tipo, float saldoInicial, ArrayList<Cliente> titulares) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = (titulares != null) ? titulares : new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }
    
    // Método para redondear montos a dos decimales
    private float redondear(float monto) {
        return Math.round(monto * 100) / 100f;
    }

    // Métodos getters 
    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public float getSaldo() { return saldo; }
    public ArrayList<Cliente> getTitulares() { return titulares; }
    public ArrayList<Transaccion> getMovimientos() { return movimientos; }

    // Métodos setters
    public void setNumero(String numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setSaldo(float saldo) { this.saldo = redondear(saldo); }
    public void setTitulares(ArrayList<Cliente> titulares) { this.titulares = titulares; }

    // Método para acreditar un monto en la cuenta
    public void acreditar(float monto, Transaccion t) {
        saldo = redondear(saldo + monto);
        movimientos.add(t);
    }

    // Método para debitar un monto de la cuenta
    public boolean debitar(float monto, Transaccion t) {
        if (saldo >= monto) {
            saldo = redondear(saldo - monto);
            movimientos.add(t);
            return true;
        }
        return false;
    }
    
    // Método para listar los movimientos de la cuenta
    public ArrayList<Transaccion> listarMovimientos() {
        return movimientos;
    }

    // Método para agregar un titular a la cuenta
    public void agregarTitular(Cliente cliente) {
        titulares.add(cliente);
    }

    // Método para mostrar los movimientos de la cuenta
    public void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
            return;
        }

        for (Transaccion t : movimientos) {
            System.out.println(t.getResumen());
            System.out.println("----------------");
        }
    }

    // Método para mostrar el resumen de la cuenta
    public void mostrarResumen() {
        System.out.println("\n--- RESUMEN DE CUENTA ---");
        System.out.println("Número: " + numero);
        System.out.println("Tipo: " + tipo);
        System.out.println("Saldo: S/ " + saldo);

        System.out.println("Titulares:");
        for (Cliente c : titulares) {
            System.out.println(" - " + c.getNombre());
        }

        mostrarMovimientos();
    }

    // Método para filtrar y mostrar movimientos de la cuenta
    public void filtrarMovimientos() {
        System.out.println("\n1. Solo depósitos");
        System.out.println("2. Solo retiros");
        System.out.print("Opción: ");
        int op = sc.nextInt();

        for (Transaccion t : movimientos) {
            if (op == 1 && t instanceof Deposito)
                System.out.println(t.getResumen());
            if (op == 2 && t instanceof Retiro)
                System.out.println(t.getResumen());
        }
    }

}
