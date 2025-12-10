/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    
    // Metodo que nos permite conectarnos
    public static Connection conectar() throws Exception{
        try {
            // Intentamos establecer la sesión con la base de datos
            // El DriverManager usa la URL para encontrar el driver correcto y abrir el canal
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            return conexion;

        } catch (SQLException e) {
            // Si algo falla (puerto cerrado, contraseña mal, base no existe)
            throw new Error("Error de Conexión: " + e.getErrorCode());
        }
    }
}