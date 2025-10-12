import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> registrarPoliza();
                case 3 -> asignarPoliza();
                case 4 -> sistema.mostrarAgentesDisponibles();
                case 5 -> registrarSiniestro();
                case 6 -> {
                    sistema.detectarDuplicados();
                    sistema.eliminarDuplicados();
                }
                case 7 -> actualizarEstadoPoliza();
                case 8 -> {
                    Reporte rep = sistema.generarReporteNomina();

                    // Mostrar el reporte en consola (Vista)
                    System.out.println("\n=== REPORTE DE NÓMINA ===");
                    System.out.println("ID: " + rep.getId());
                    System.out.println("Fecha: " + rep.getFechaGeneracion());
                    System.out.println("Empleados incluidos: " + rep.getEmpleadosInvolucrados().size());
                    System.out.println("--------------------------------------");

                    for (Empleado e : rep.getEmpleadosInvolucrados()) {
                        System.out.printf("%-20s %-20s Q%.2f%n",
                                e.getNombre(),
                                "(" + e.getClass().getSimpleName() + ")",
                                e.calcularSalario());
                    }

                    System.out.println("--------------------------------------");
                    System.out.println("Tipo de seguro: " + rep.getTipoSeguro());
                    System.out.println("Aprobado: " + (rep.isAprobado() ? "Sí" : "No"));
                    System.out.println("======================================\n");
                }

                case 9 -> mostrarHistorial();
                case 0 -> System.out.println("Saliendo del sistema... ¡Hasta pronto!");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    // MENÚ PRINCIPAL

    private static void mostrarMenu() {
        System.out.println("----------------------------------------");
        System.out.println("|      INSURANCE MANAGEMENT SYSTEM     |");
        System.out.println("----------------------------------------");
        System.out.println("| 1. Registrar empleado                |");
        System.out.println("| 2. Registrar póliza                  |");
        System.out.println("| 3. Asignar póliza                    |");
        System.out.println("| 4. Mostrar agentes disponibles       |");
        System.out.println("| 5. Registrar siniestro               |");
        System.out.println("| 6. Detectar y eliminar duplicados    |");
        System.out.println("| 7. Actualizar estado de póliza       |");
        System.out.println("| 8. Generar reporte de nómina         |");
        System.out.println("| 9. Ver historial del sistema         |");
        System.out.println("| 0. Salir                             |");
        System.out.println("----------------------------------------");
    }

    // FUNCIONALIDADES

    private static void registrarEmpleado() {
        System.out.println("\n--- Registro de empleado ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Años de experiencia: ");
        int experiencia = leerEntero();
        System.out.print("Salario base: ");
        double salario = leerDouble();

        System.out.println("Seleccione tipo de empleado:");
        System.out.println("1. Agente de ventas");
        System.out.println("2. Analista de riesgos");
        System.out.println("3. Gestor de siniestros");
        System.out.println("4. Ejecutivo de atención");
        System.out.println("5. Supervisor");
        System.out.print("Opción: ");
        int tipo = leerEntero();

        Empleado e = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("Pólizas vendidas: ");
                int pv = leerEntero();
                System.out.print("Comisión por venta: ");
                double com = leerDouble();
                e = new AgenteVentas(nombre, experiencia, salario, pv, com);
            }
            case 2 -> {
                System.out.print("Reportes evaluados: ");
                int rep = leerEntero();
                System.out.print("Bono por reporte: ");
                double bono = leerDouble();
                e = new AnalistaRiesgos(nombre, experiencia, salario, new ArrayList<>(), rep, bono);
            }
            case 3 -> {
                System.out.print("Reclamos procesados: ");
                int rec = leerEntero();
                System.out.print("Incentivo por reclamo: ");
                double inc = leerDouble();
                e = new GestorSiniestros(nombre, experiencia, salario, rec, inc);
            }
            case 4 -> {
                System.out.print("Clientes asignados: ");
                int cli = leerEntero();
                System.out.print("Índice de satisfacción (0 - 100): ");
                double sat = leerDouble();
                e = new EjecutivoAtencion(nombre, experiencia, salario, cli, sat);
            }
            case 5 -> {
                System.out.print("Bono de desempeño: ");
                double bono = leerDouble();
                e = new Supervisor(nombre, experiencia, salario, new ArrayList<>(), bono);
            }
            default -> System.out.println("Tipo inválido. Empleado no creado.");
        }

        if (e != null) {
            sistema.agregarEmpleado(e);
            System.out.println("Empleado registrado exitosamente.");
        }
    }

    private static void registrarPoliza() {
        System.out.println("\n--- Registro de póliza ---");
        System.out.print("Nombre del cliente: ");
        String cliente = sc.nextLine();

        System.out.println("Seleccione tipo de seguro:");
        for (int i = 0; i < TipoSeguro.values().length; i++) {
            System.out.println((i + 1) + ". " + TipoSeguro.values()[i]);
        }

        int tipoIndex = leerEntero() - 1;
        if (tipoIndex < 0 || tipoIndex >= TipoSeguro.values().length) {
            System.out.println("Tipo inválido. No se creó la póliza.");
            return;
        }

        TipoSeguro tipo = TipoSeguro.values()[tipoIndex];
        Poliza p = new Poliza(cliente, tipo);
        sistema.agregarPoliza(p);
        System.out.println("Póliza registrada correctamente.");
    }

    private static void asignarPoliza() {
        if (sistema.mostrarAgentesDisponibles().isEmpty()) {
            System.out.println("No hay agentes disponibles para asignar.");
            return;
        }

        System.out.println("\n--- Asignación de póliza ---");
        if (sistema.getHistorial().isEmpty()) {
            System.out.println("Debe registrar pólizas primero.");
            return;
        }

        System.out.print("Ingrese el ID de la póliza a asignar: ");
        String id = sc.nextLine();

        Poliza encontrada = null;
        for (Poliza p : sistema.getPolizas()) {
            if (p.getId().equals(id)) {
                encontrada = p;
                break;
            }
        }

        if (encontrada == null) {
            System.out.println("No se encontró la póliza con ese ID.");
        } else {
            sistema.asignarPoliza(encontrada);
        }
    }

    private static void registrarSiniestro() {
        System.out.println("\n--- Registro de siniestro ---");
        System.out.print("ID de póliza: ");
        String id = sc.nextLine();

        Poliza pol = null;
        for (Poliza p : sistema.getPolizas()) {
            if (p.getId().equals(id)) {
                pol = p;
                break;
            }
        }

        if (pol == null) {
            System.out.println("Póliza no encontrada.");
            return;
        }

        System.out.print("Título del siniestro: ");
        String titulo = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        Siniestro s = new Siniestro(titulo, desc);
        pol.registrarSiniestro(s);
        System.out.println("Siniestro registrado exitosamente.");
    }

    private static void actualizarEstadoPoliza() {
        System.out.println("\n--- Actualizar estado de póliza ---");
        System.out.print("Ingrese el ID de la póliza: ");
        String id = sc.nextLine();

        Poliza pol = null;
        for (Poliza p : sistema.getPolizas()) {
            if (p.getId().equals(id)) {
                pol = p;
                break;
            }
        }

        if (pol == null) {
            System.out.println("Póliza no encontrada.");
            return;
        }

        System.out.println("Seleccione nuevo estado:");
        for (int i = 0; i < EstadoPoliza.values().length; i++) {
            System.out.println((i + 1) + ". " + EstadoPoliza.values()[i]);
        }

        int idx = leerEntero() - 1;
        if (idx < 0 || idx >= EstadoPoliza.values().length) {
            System.out.println("Opción inválida.");
            return;
        }

        sistema.actualizarEstadoPoliza(pol, EstadoPoliza.values()[idx]);
        System.out.println("Estado actualizado correctamente.");
    }

    private static void mostrarHistorial() {
        System.out.println("\n=== HISTORIAL DEL SISTEMA ===");
        for (String h : sistema.getHistorial()) {
            System.out.println(h);
        }
    }

    // LECTURA
    private static int leerEntero() {
        String input = sc.nextLine();
        int valor = 0;
        boolean valido = false;

        for (int intentos = 0; intentos < 3 && !valido; intentos++) {
            try {
                valor = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                if (intentos < 2) {
                    System.out.print("Entrada inválida. Intente nuevamente: ");
                    input = sc.nextLine();
                } else {
                    System.out.println("Número inválido tras varios intentos. Se usará 0 por defecto.");
                    valor = 0;
                }
            }
        }
        return valor;
    }

    private static double leerDouble() {
        String input = sc.nextLine();
        double valor = 0.0;
        boolean valido = false;

        for (int intentos = 0; intentos < 3 && !valido; intentos++) {
            try {
                valor = Double.parseDouble(input);
                valido = true;
            } catch (NumberFormatException e) {
                if (intentos < 2) {
                    System.out.print("Entrada inválida. Intente nuevamente: ");
                    input = sc.nextLine();
                } else {
                    System.out.println("Número inválido tras varios intentos. Se usará 0.0 por defecto.");
                    valor = 0.0;
                }
            }
        }
        return valor;
    }

}

