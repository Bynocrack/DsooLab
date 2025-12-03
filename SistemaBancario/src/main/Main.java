package main;

import modelo.*;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        
        Banco banco = new Banco();

        // Agregar datos iniciales
        Empleado emp = new Empleado("61761556","Diego Cahuana","Yura - Arequipa","997134305","dcauhana@banco.com","DegoX", "Camargo");
        banco.empleados.add(emp);

        Cliente cliente = new Cliente("60060857","Brayan Motta","Socabaya - Arequipa","945846028","brayan@gmail.com","bmotta","CHOCA");
        banco.clientes.add(cliente);

        Administrador adm = new Administrador("34628123", "Josue Madueño", "Lima - San Isidro", "963852741", "at@gmail.com", "atun", "atuncito");
        banco.administradores.add(adm);
        
        // Lanzar la GUI
        java.awt.EventQueue.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(banco);
            ventana.setVisible(true);
        });
    }
}