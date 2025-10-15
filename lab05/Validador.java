import java.util.*;

public class Validador {

    //Metodo que valida el formato de fecha dd/MM/yyyy
    public static boolean validarFormatoFecha(String fecha) {

        String[] partes = fecha.split("/");
        if (partes.length != 3) {
            mostrarError("Formato de fecha inválido. Use dd/MM/yyyy.");
            return false;
        }

        try {
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int año = Integer.parseInt(partes[2]);

            if (mes < 1 || mes > 12) {
                mostrarError("El mes debe estar entre 1 y 12.");
                return false;
            }

            if (dia < 1 || dia > 32) {
                mostrarError("El día no es válido para el mes ingresado.");
                return false;
            }
            if(año < 2024 || año > 2026) {
                mostrarError("El año debe ser mayor a 2024 y menor a 2026.");
                return false;
            }

            return true;
        } catch (Exception e) {
            mostrarError("Formato de fecha inválido. Use dd/MM/yyyy.");
            return false;
        }
    }

    //Metodo que valida el formato de hora HH:mm
    public static boolean validarFormatoHora(String hora) {

        String[] partes = hora.split(":");
        if (partes.length != 2) {
            mostrarError("Formato de hora inválido. Use HH:mm.");
            return false;
        }

        try {
            int h = Integer.parseInt(partes[0]);
            int m = Integer.parseInt(partes[1]);
            if (h < 0 || h > 23 || m < 0 || m > 59) {
                mostrarError("Hora fuera de rango (00:00 a 23:59).");
                return false;
            }
            return true;
        } catch (Exception e) {
            mostrarError("Formato de hora inválido. Use HH:mm.");
            return false;
        }
    }

    //Metodo que verifica si un codigo es unico en una lista de objetos(doctor-paciente)
    public static boolean codigoUnico(List<?> lista, String codigo) {

        for (Object obj : lista) {
            try {
                String codExistente = (String) obj.getClass().getMethod("getCodigo").invoke(obj);
                if (codExistente.equalsIgnoreCase(codigo)) {
                    mostrarError("El código " + codigo + " ya existe.");
                    return false;
                }
            } catch (Exception e) {
                mostrarError("Error al verificar código.");
                return false;
            }
        }
        return true;
    }

    //Metodo que muestra mensajes de error
    public static void mostrarError(String mensaje) {

        System.out.println("ERROR: " + mensaje);
    }
}
