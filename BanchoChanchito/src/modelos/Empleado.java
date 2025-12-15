package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Empleado extends Trabajador { // Clase Empleado hereda de Trabajador

    // Atributo de la clase empleado
    private String idEmpleado;

    // Constructor de la clase Empleado
    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idEmpleado = "EM" + dni;
        
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "INSERT INTO empleados (idEmpleado, dni, nombre, direccion, telefono, email, usuario, contrasena, estado) VALUES(?,?,?,?,?,?,?,?,?)"
            )){
            pstmt.setString(1, this.idEmpleado);
            pstmt.setString(2, this.dni);
            pstmt.setString(3, this.nombre);
            pstmt.setString(4, this.direccion);
            pstmt.setString(5, this.telefono);
            pstmt.setString(6, this.email);
            pstmt.setString(7, this.usuario);
            pstmt.setString(8, this.contraseña);
            pstmt.setInt(9, 0);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al subir el empleado a la base de datos,\n no se efectuaron los cambios");
        }
    }
    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String usuario, String contraseña, String idEmpleado) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idEmpleado = idEmpleado;
    }
    
    public void despedido() throws Exception{
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "DELETE FROM empleados WHERE idEmpleado = ?"
            )){
            pstmt.setString(1, this.idEmpleado);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al despedir empelado de la base de datos,\nNo se efectuaron los cambios.");
        }
    }

    // Método getter
    @Override
    public String getIdEmpleado() { return idEmpleado; }

    // Método para mostrar los permisos del empleado
    @Override
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
