import java.util.*;

public class Sistema {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Hospital hospital = new Hospital();
        int opcion;
        
        //bucle con menú de opciones
        do {
            System.out.println("\n===== SISTEMA DE CITAS MÉDICAS =====");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Agendar Cita");
            System.out.println("4. Cambiar Estado de Cita");
            System.out.println("5. Mostrar Todas las Citas");
            System.out.println("6. Listar Citas por Doctor");
            System.out.println("7. Listar Citas por Paciente");
            System.out.println("8. Total de Citas Atendidas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1 -> registrarDoctor(hospital);
                case 2 -> registrarPaciente(hospital);
                case 3 -> agendarCita(hospital);
                case 4 -> cambiarEstado(hospital);
                case 5 -> hospital.mostrarTodasCitas();
                case 6 -> listarPorDoctor(hospital);
                case 7 -> listarPorPaciente(hospital);
                case 8 -> System.out.println("Total de citas atendidas: " + hospital.obtenerTotalCitasAtendidas());
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    //REGISTRO DE DOCTOR
    private static void registrarDoctor(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- Registro de Doctor ---");

        //validar código único
        String codigo;
        do {
            System.out.print("Código: ");
            codigo = scan.nextLine();
            if (!Validador.codigoUnico(hospital.getDoctores(), codigo)) {
                System.out.println("El código ya existe. Intente de nuevo.");
            }
        } while (!Validador.codigoUnico(hospital.getDoctores(), codigo));

        //ingreso de datos
        System.out.print("Nombres: ");
        String nombres = scan.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scan.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scan.nextLine();

        //validar horario de atención con formato adecuado
        String horario;
        while (true) {
            System.out.print("Horario de atención (ej. 08:00-14:00): ");
            horario = scan.nextLine();

            if (horario.contains("-")) {
                String[] partes = horario.split("-");
                if (partes.length == 2 &&
                    Validador.validarFormatoHora(partes[0]) &&
                    Validador.validarFormatoHora(partes[1])) {
                    break;
                }
            }
            System.out.println("ERROR: Formato inválido. Use HH:mm-HH:mm");
        }

        //agregar doctor al arraylist
        Doctor d = new Doctor(codigo, nombres, apellidos, especialidad, horario);
        if (hospital.registrarDoctor(d)) {
            System.out.println("Doctor registrado correctamente.");
        }
    }

    //REGISTRO DE PACIENTE
    private static void registrarPaciente(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- Registro de Paciente ---");

        //validar código único del paciente
        String codigo;
        do {
            System.out.print("Código: ");
            codigo = scan.nextLine();
            if (!Validador.codigoUnico(hospital.getPacientes(), codigo)) {
                System.out.println("El código ya existe. Intente de nuevo.");
            }
        } while (!Validador.codigoUnico(hospital.getPacientes(), codigo));

        //ingreso de datos: nombre
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();

        //validar edad mayor a 0
        int edad;
        do {
            System.out.print("Edad: ");
            edad = scan.nextInt();
            scan.nextLine();
            if (edad <= 0) {
                System.out.println("La edad debe ser mayor que 0.");
            }
        } while (edad <= 0);

        //ingreso de datos: documento de identidad
        System.out.print("Número de documento: ");
        String doc = scan.nextLine();

        //agregar nuevo paciente al arraylist
        Paciente p = new Paciente(codigo, nombre, edad, doc);
        if (hospital.registrarPaciente(p)) {
            System.out.println("Paciente registrado correctamente.");
        }
    }

    //AGENDAR CITA
    private static void agendarCita(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- Agendar Cita ---");

        //ingreso de datos: código de cita
        System.out.print("Código de cita: ");
        String codigo = scan.nextLine();

        //verificar si el doctor se encuentra registrado
        System.out.print("Código de doctor: ");
        String codDoc = scan.nextLine();
        Doctor doctor = hospital.buscarDoctor(codDoc);
        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        //verificar si el paciente se encuentra registrado
        System.out.print("Código de paciente: ");
        String codPac = scan.nextLine();
        Paciente paciente = hospital.buscarPaciente(codPac);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        //ingreso de fecha de consulta, valida el correcto ingreso de formato
        String fecha;
        do {
            System.out.print("Fecha (dd/MM/yyyy): ");
            fecha = scan.nextLine();
        } while (!Validador.validarFormatoFecha(fecha));

        //ingreso de hora de consulta, valida el correcto ingreso de formato
        String hora;
        do {
            System.out.print("Hora (HH:mm): ");
            hora = scan.nextLine();
        } while (!Validador.validarFormatoHora(hora));

        //agrega la cita al arraylist
        Cita c = new Cita(codigo, "Pendiente", fecha, hora, doctor, paciente);
        hospital.agendarCita(c);
    }

    //MÉTODO PARA CAMBIAR ESTADO DE CITA
    private static void cambiarEstado(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- Cambiar Estado de Cita ---");
        System.out.print("Código de cita: ");
        String cod = scan.nextLine();
        System.out.print("Nuevo estado (Atendida / Cancelada): ");
        String nuevo = scan.nextLine();

        //actualiza el nuevo estado de cita, se usa clase sistema
        if (hospital.cambiarEstadoCita(cod, nuevo))
            System.out.println("Estado actualizado correctamente.");
        else
            System.out.println("No se encontró la cita o el estado es inválido.");
    }

    //LISTAR CITAS POR DOCTOR
    private static void listarPorDoctor(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Código de doctor: ");
        String cod = scan.nextLine();
        List<Cita> citas = hospital.listarCitasPorDoctor(cod);

        //verifica que haya citas
        if (citas.isEmpty()) System.out.println("No hay citas para este doctor.");
        else for (Cita c : citas) c.mostrarDetalles();
    }

    //LISTAR CITAS POR PACIENTE
    private static void listarPorPaciente(Hospital hospital) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Código de paciente: ");
        String cod = scan.nextLine();
        List<Cita> citas = hospital.listarCitasPorPaciente(cod);

        //verifica que hay citas
        if (citas.isEmpty()) System.out.println("No hay citas para este paciente.");
        else for (Cita c : citas) c.mostrarDetalles();
    }
}
