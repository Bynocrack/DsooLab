package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Cliente extends Usuario { // Clase Cliente hereda de Usuario

    // Atributos de la clase Cliente
    private String idCliente;
    private ArrayList<Cuenta> cuentas;
    private String[] codCuentas;

    // Constructor de la clase Cliente
    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String usuario, String contraseña) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idCliente = "CL" + dni;
        this.cuentas = new ArrayList<>();
        
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "INSERT INTO clientes (idCliente, dni, nombre, direccion, cuentas, telefono, email, usuario, contrasena, estado) VALUES(?,?,?,?,?,?,?,?,?,?)"
            )){
            pstmt.setString(1, this.idCliente);
            pstmt.setString(2, this.dni);
            pstmt.setString(3, this.nombre);
            pstmt.setString(4, this.direccion);
            String codCuentas = "";
            for (int i = 0; i < cuentas.size(); i++) {
                codCuentas += cuentas.get(i).getNumero();
                if (i != cuentas.size()-1) {
                    codCuentas += ",";
                }
            }
            pstmt.setString(5, codCuentas);
            pstmt.setString(6, this.telefono);
            pstmt.setString(7, this.email);
            pstmt.setString(8, this.usuario);
            pstmt.setString(9, this.contraseña);
            pstmt.setInt(10, 0);
            int filas = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al subir el cliente a la base de datos,\n no se efectuaron los cambios");
        }
    }
    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String usuario, String contraseña,
                   String idCliente) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idCliente = idCliente;
        this.cuentas = new ArrayList<>();
    }

    // Método para agregar una cuenta al cliente
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para obtener la lista de cuentas del cliente
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    // Métodos getters
    public String getIdCliente() { return idCliente; }
    public float getSaldoTotal() {
        float total = 0;
            for (Cuenta c : cuentas) total += c.getSaldo();
                return Math.round(total * 100) / 100f;
    }

    // Método para seleccionar una cuenta del cliente
    public Cuenta seleccionarCuenta(String num) throws Exception {
        for (Cuenta c : cuentas)
            if (c.getNumero().equals(num))
                return c;

        throw new Exception("No se encontro esa cuenta");
    }

    // Método para mostrar el resumen de cuentas del cliente
    public ArrayList<String> obtenerResumenCuentas() {
        ArrayList<String> resumen = new ArrayList<>();
        resumen.add("--- CUENTAS DEL CLIENTE " + nombre + " ---");
        for (int i = 0; i < cuentas.size(); i++) {
            resumen.add(cuentas.get(i).getNumero() + " - Saldo: S/ " + cuentas.get(i).getSaldo());
        }
        return resumen;
    }
    @Override

    // Método para mostrar los permisos del cliente
    public String mostrarPermisos() {
        return "================================" +
        "\nPERMISOS DEL CLIENTE: " +
        "\n================================" +
        "\nSI Consultar resumen de sus cuentas" +
        "\nSI Ver movimientos de sus cuentas" +
        "\nSI Consultar saldo total";
    }
}
