package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Administrador extends Trabajador{ // Clase Administrador hereda de Trabajador

    // Atributos de la clase Administrador
    private String idAdministrador;

    // Constructor de la clase Administrador
    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario,
            String contraseña) throws Exception{
        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = "AD" + dni;
        
        try (Connection DB = ConexionDB.conectar();
            PreparedStatement pstmt = DB.prepareStatement(
                    "INSERT INTO administradores (idAdministrador, dni, nombre, direccion, telefono, email, usuario, contrasena, estado) VALUES(?,?,?,?,?,?,?,?,?)"
            )){
            pstmt.setString(1, this.idAdministrador);
            pstmt.setString(2, this.dni);
            pstmt.setString(3, this.nombre);
            pstmt.setString(4, this.direccion);
            pstmt.setString(5, this.telefono);
            pstmt.setString(6, this.email);
            pstmt.setString(7, this.usuario);
            pstmt.setString(8, this.contraseña);
            pstmt.setInt(9, 0);
            int filas = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al subir el empleado a la base de datos,\n no se efectuaron los cambios");
        }
    }
    
    public Administrador(String dni, String nombre, String direccion, String telefono, String email, String usuario,
            String contraseña, String idAdministrador) throws Exception{

        super(dni, nombre, direccion, telefono, email, usuario, contraseña);
        this.idAdministrador = idAdministrador;
    }

    // Método getter 
    @Override
    public String getIdAdministrador() { return idAdministrador; }

    @Override
    // Método para mostrar los permisos del administrador
    public String mostrarPermisos() {
        return "================================" +
        "\n PERMISOS DEL ADMINISTRADOR: " +
        "\n================================" +
        "\nSI Consultar resumen de cuentas de clientes" +
        "\nSI Ver movimientos de cuentas de clientes" +
        "\nSI Registrar depósitos y retiros" +
        "\nSI Crear nuevas cuentas para clientes" +
        "\nSI Registrar nuevos clientes" +
        "\nSI Contratar y despedir empleados";
    }
}
