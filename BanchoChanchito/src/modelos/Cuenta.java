package modelos;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Cuenta {

    // Atributos de la clase Cuenta
    private String numero;
    private String tipo;
    private float saldo;
    private ArrayList<Transaccion> movimientos;
    private ArrayList<Cliente> titulares;

    // Constructor de la clase Cuenta.
    public Cuenta(String numero, String tipo, float saldoInicial, ArrayList<Cliente> titulares) throws Exception{
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = titulares;
        this.movimientos = new ArrayList<>();
        
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "INSERT INTO cuentas (numero, tipo, saldo, titulares) VALUES(?,?,?,?)"
            )){
            pstmt.setString(1, this.numero);
            pstmt.setString(2, this.tipo);
            pstmt.setFloat(3, this.saldo);
            String codTitulares = "";
            for (int i = 0; i < titulares.size(); i++) {
                codTitulares += titulares.get(i).getIdCliente();
                if (i != titulares.size()-1) {
                    codTitulares += ",";
                }
            }
            pstmt.setString(4, codTitulares);
            int filas = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al subir la cuenta a la base de datos,\n no se efectuaron los cambios");
        }
    }
    
    public Cuenta(String numero, String tipo, float saldoInicial, ArrayList<Cliente> titulares, boolean existente) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = titulares;
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
        actualizar();
        movimientos.add(t);
    }

    // Método para debitar un monto de la cuenta
    public boolean debitar(float monto, Transaccion t) {
        if (saldo >= monto) {
            saldo = redondear(saldo - monto);
            actualizar();
            movimientos.add(t);
            return true;
        }
        return false;
    }
    
    public void actualizar() {
        try (Connection DB = ConexionDB.conectar();
             PreparedStatement pstmt = DB.prepareStatement(
                     "UPDATE cuentas SET saldo = ? WHERE numero = ?"
             )){
            pstmt.setFloat(1, this.saldo);
            pstmt.setString(2, this.numero);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void cargarMovimiento(Transaccion t) {
        movimientos.add(t);
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
    public ArrayList<String> mostrarMovimientos() {
        ArrayList<String> out = new ArrayList<>();
        if (movimientos.isEmpty()) {
            out.add("No hay movimientos.");
        }

        for (Transaccion t : movimientos) {
            out.add(((t instanceof Retiro) ? "Retiro: " : "Deposito: ") + t.getMonto());
        }
        
        return out;
    }

    // Método para mostrar el resumen de la cuenta
    public String mostrarResumen() {
        String out = "--- RESUMEN DE CUENTA ---" +
                     "\nNúmero: " + numero +
                     "\nTipo: " + tipo + 
                     "\nSaldo: S/ " + saldo +
                     "\nTitulares:";
        for (Cliente c : titulares) {
            out += "\n - " + c.getNombre();
        }
        return out;
    }

    // Método para filtrar y mostrar movimientos de la cuenta
    public ArrayList<String> filtrarMovimientos(char tipo) {
        ArrayList<String> out = new ArrayList<>();

        for (Transaccion t : movimientos) {
            if (tipo == 'D' && t instanceof Deposito)
                out.add(t.getFechaHora() + " --- " + t.getMonto());
            if (tipo == 'R' && t instanceof Retiro)
                out.add(t.getFechaHora() + " --- " + t.getMonto());
        }
        return out;
    }

}

