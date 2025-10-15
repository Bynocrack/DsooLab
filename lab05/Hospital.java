import java.util.*;

public class Hospital {

    //atributos de clase
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    //constructor 
    public Hospital() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
    }

    //método que usa clase validador y doctor
    public boolean registrarDoctor(Doctor doctor) {
        if (!Validador.codigoUnico(doctores, doctor.getCodigo())) {
            return false;
        }
        doctores.add(doctor);
        System.out.println("Doctor registrado: " + doctor.getNombreCompleto());
        return true;
    }

    //método que usa clase validador y paciente
    public boolean registrarPaciente(Paciente paciente) {
        if (!Validador.codigoUnico(pacientes, paciente.getCodigo())) {
            return false;
        }
        pacientes.add(paciente);
        System.out.println("Paciente registrado: " + paciente.getNombre());
        return true;
    }

    //método que usa clase validador y cita
    public boolean agendarCita(Cita cita) {
        if (!Validador.validarFormatoFecha(cita.getFecha()) ||
            !Validador.validarFormatoHora(cita.getHora())) {
            return false;
        }

        //verifica que no exista otra cita con el mismo código
        for (Cita c : citas) {
            if (c.getCodigoCita().equalsIgnoreCase(cita.getCodigoCita())) {
                Validador.mostrarError("Ya existe una cita con el código: " + cita.getCodigoCita());
                return false;
            }
        }

        //verifica que el doctor no tenga otra cita programada en la misma fecha y hora
        for (Cita c : citas) {
            if (c.getDoctor().getCodigo().equalsIgnoreCase(cita.getDoctor().getCodigo()) &&
                c.getFecha().equalsIgnoreCase(cita.getFecha()) &&
                c.getHora().equalsIgnoreCase(cita.getHora())) {
                Validador.mostrarError("El doctor ya tiene una cita programada a esa hora.");
                return false;
            }
        }

        //verifica que la hora de la cita esté dentro del horario de atención del doctor
        String horario = cita.getDoctor().getHorarioAtencion();
        String[] partes = horario.split("-");
        if (partes.length == 2) {
            String inicio = partes[0];
            String fin = partes[1];
            String horaCita = cita.getHora();

            //compara las horas como cadenas para validar el rango
            if (horaCita.compareTo(inicio) < 0 || horaCita.compareTo(fin) > 0) {
                Validador.mostrarError("La hora de la cita está fuera del horario de atención del doctor (" + horario + ").");
                return false;
            }
        }

        citas.add(cita);
        System.out.println("Cita registrada correctamente.");
        return true;
    }

    //cambiando el estado de una cita
    public boolean cambiarEstadoCita(String codigoCita, String nuevoEstado) {
        for (Cita cita : citas) {
            if (cita.getCodigoCita().equalsIgnoreCase(codigoCita)) {
                // no permitir cambiar si ya está atendida o cancelada
                if (cita.getEstado().equalsIgnoreCase("Atendida") ||
                    cita.getEstado().equalsIgnoreCase("Cancelada")) {
                    System.out.println("No se puede cambiar el estado de una cita atendida o cancelada.");
                    return false;
                }

                cita.cambiarEstado(nuevoEstado);
                System.out.println("Estado de la cita actualizado a: " + nuevoEstado);
                return true;
            }
        }
        Validador.mostrarError("No se encontró una cita con el código " + codigoCita);
        return false;
    }

    //método para mostrar todas las citas
    public void mostrarTodasCitas() {

        //verifica que haya citas
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        System.out.println("===== LISTA DE CITAS =====");
        for (Cita cita : citas) {
            cita.mostrarDetalles();
            System.out.println("Doctor: " + cita.getDoctor().getNombreCompleto());
            System.out.println("Paciente: " + cita.getPaciente().getNombre());
            System.out.println("--------------------------");
        }
    }

    //metodo para listar las citas por doctor
    public List<Cita> listarCitasPorDoctor(String codigoDoctor) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getDoctor().getCodigo().equalsIgnoreCase(codigoDoctor)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    //método para listar las citas por paciente
    public List<Cita> listarCitasPorPaciente(String codigoPaciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente().getCodigo().equalsIgnoreCase(codigoPaciente)) {
                resultado.add(cita);
            }
        }
        return resultado;
    }

    //análisis de citas totales 
    public int obtenerTotalCitasAtendidas() {
        int contador = 0;
        for (Cita cita : citas) {
            if (cita.getEstado().equalsIgnoreCase("atendida")) {
                contador++;
            }
        }
        return contador;
    }

    //busca doctor por código
    public Doctor buscarDoctor(String codigo) {
        for (Doctor d : doctores) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                return d;
            }
        }
        return null;
    }

    //busca paciente por código
    public Paciente buscarPaciente(String codigo) {
        for (Paciente p : pacientes) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    //getters 
    public List<Doctor> getDoctores() {
        return doctores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    //métodos que usan la clase Validador (UML)
    public boolean validarFormatoFecha(String fecha) {
        return Validador.validarFormatoFecha(fecha);
    }

    public boolean validarFormatoHora(String hora) {
        return Validador.validarFormatoHora(hora);
    }
}
