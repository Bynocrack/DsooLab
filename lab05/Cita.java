class Cita {
  // Declaramos todos lo atributos (segun UML)
  private String codigoCita;
  private String fecha;
  private String hora;
  private String estado;
  private Doctor doctor;
  private Paciente paciente;

  // Constructor de Cita
  public Cita(String codigoCita, String estado, String fecha, String hora, Doctor doctor, Paciente paciente) {
    setFecha(fecha);
    setEstado(estado);
    setCodigoCita(codigoCita);
    setHora(hora);
    setDoctor(doctor);
    setPaciente(paciente);
  }

  // Sirve para mostrar los detalles de la cita
  public void mostrarDetalles() {
    System.out.println("Codigo de cita: " + this.codigoCita);
    System.out.println("Fecha de la cita: " + this.fecha);
    System.out.println("Hora de la cita: " + this.hora);
    System.out.println("Estado de la cita: " + this.estado);
  }

  // Con esto cambiamos el estado de la cita (pendiente, atendida, cancelada)
  public void cambiarEstado(String nuevoEstado) {
    this.estado = nuevoEstado;
  }

  // Metodos getters
  public String getCodigoCita() { return this.codigoCita; }
  public String getFecha() { return this.fecha; }
  public String getHora() { return this.hora; }
  public String getEstado() { return this.estado; }
  public Doctor getDoctor() { return this.doctor; }
  public Paciente getPaciente() { return this.paciente; }

  // Metodos setters
  public void setCodigoCita(String codigo) { this.codigoCita = codigo; }
  public void setFecha(String fecha) { this.fecha = fecha; }
  public void setHora(String hora) { this.hora = hora; }
  public void setEstado(String estado) { this.estado = estado; }
  public void setDoctor(Doctor doctor) { this.doctor = doctor; }
  public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
