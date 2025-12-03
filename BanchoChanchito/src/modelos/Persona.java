package modelos;

import java.util.Scanner;

public class Persona {
    private Scanner sc = new Scanner(System.in);

    // Atributos básicos
    protected String dni;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String email;

    // Constructor de la clase Persona
    public Persona(String dni, String nombre, String direccion, String telefono, String email) throws Exception{
        try {
            setDni(dni);
            setNombre(nombre);
            setDireccion(direccion);
            setTelefono(telefono);
            setEmail(email);
        } catch (Exception e) {
            throw e;
        }
    }

    // Métodos getters
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // Métodos setters 
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    //Validacion de telefono (9 digitos)
    public void setTelefono(String telefono) throws Exception{
        if (telefono.matches("\\d{9}")) { //Expresion Regular
            this.telefono = telefono;
            return;
        }
        throw new Exception("El telefono debe tener 9 digitos.");
    }

    //Validacion de email (debe contener '@' y un dominio válido)
    public void setEmail(String email) throws Exception{ 
        if (email.matches("^.+@.+\\..+$")) { //Expresion Regular
            this.email = email;
            return;
        }
        throw new Exception("Email inválido. Debe contener un '@' y un dominio válido.");
    }
    
    //Validacion de DNI (8 digitos)
    public void setDni(String dni) throws Exception{
        if (dni.matches("\\d{8}")) { //Expresion Regular
            this.dni = dni;
            return;
        }
        throw new Exception("DNI inválido. Debe contener 8 dígitos.");
    }
}
