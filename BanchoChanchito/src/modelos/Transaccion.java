package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaccion {

    // Atributos de la clase Transaccion
    protected String id;
    protected String fechaHora;
    protected float monto;
    protected boolean atendidoPorEmpleado;
    protected Trabajador encargado;
    protected Cuenta cuenta;
    protected Cliente cliente;
    protected AutoServicio canal;

    // Constructor de la clase Transaccion
    public Transaccion(String id, String fechaHora, float monto,
                       boolean atendidoPorEmpleado, Trabajador encargado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal, boolean creado) throws Exception{

        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.atendidoPorEmpleado = atendidoPorEmpleado;
        this.encargado = encargado;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.canal = canal;
        
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "INSERT INTO transacciones (idTransaccion, fechaHora, monto, empleado, idEncargado, idCuenta, idCliente, canal) VALUES(?,?,?,?,?,?,?,?)"
            )){
            pstmt.setString(1, this.id);
            pstmt.setString(2, this.fechaHora);
            pstmt.setFloat(3, this.monto);
            if (encargado instanceof Empleado) {
                pstmt.setInt(4, 1);
                pstmt.setString(5, this.encargado.getIdEmpleado());
            } else {
                pstmt.setInt(4, 0);
                pstmt.setString(5, this.encargado.getIdAdministrador());
            }
            pstmt.setString(6, this.cuenta.getNumero());
            pstmt.setString(7, this.cliente.getIdCliente());
            pstmt.setString(8, null);
            int filas = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "ASDASD");
            throw new Exception("Error al subir la transaccion a la base de datos,\n no se efectuaron los cambios");
        }
    }
    
    public Transaccion(String id, String fechaHora, float monto,
                       boolean atendidoPorEmpleado, Trabajador encargado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal){

        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.atendidoPorEmpleado = atendidoPorEmpleado;
        this.encargado = encargado;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.canal = canal;
    }

    // Métodos getters
    public String getId() { return id; }
    public String getFechaHora() { return fechaHora; }
    public float getMonto() { return monto; }
    public boolean getAtendidoPorEmpleado() { return atendidoPorEmpleado; }
    public Usuario getEncargado() { return encargado; }
    public Cuenta getCuenta() { return cuenta; }
    public Cliente getCliente() { return cliente; }
    public AutoServicio getCanal() { return canal; }

    // Metodo para obtener un resumen de la transacción
    public String getResumen() {
        return "ID: " + id +
                "\nFecha: " + fechaHora +
                "\nMonto: S/ " + monto +
                "\nAtendido por Empleado: " + (atendidoPorEmpleado ? "Si" : "No") +
                "\nEncargado: " + encargado.getNombre() +
                "\nCliente: " + cliente.getNombre() +
                "\nCuenta: " + cuenta.getNumero();
    }

    public void procesar() {
        System.out.println("Procesando transacción...");
    }
}

