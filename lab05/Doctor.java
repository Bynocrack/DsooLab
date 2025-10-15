public class Doctor {
    private String codigoDoctor;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String horarioAtencion;

    public Doctor(String codigoDoctor, String nombres, String apellidos, String especialidad, String horarioAtencion) {
        this.codigoDoctor = codigoDoctor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.horarioAtencion = horarioAtencion;
    }

    public void mostrarInformacion() {
        System.out.println("--- Información del Doctor ---");
        System.out.println("Código: " + codigoDoctor);
        System.out.println("Nombre Completo: " + getNombreCompleto());
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Horario de Atención: " + horarioAtencion);
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public String getCodigo() {
        return codigoDoctor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setCodigoDoctor(String codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }
}