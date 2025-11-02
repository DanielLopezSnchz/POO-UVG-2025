import java.util.List;
import java.util.Scanner;

public class Main {

    private static Sistema sistema;
    private static Scanner scanner;

    public static void main(String[] args) {
        sistema = new Sistema();
        scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  SISTEMA DE INVENTARIO DE EQUIPOS");
        System.out.println("     Instrumentación para Física");
        System.out.println("========================================\n");

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    verCatalogoCompleto();
                    break;
                case 2:
                    consultarEquipoPorID();
                    break;
                case 3:
                    ordenarPorConsumo();
                    break;
                case 4:
                    agregarNuevoEquipo();
                    break;
                case 5:
                    eliminarEquipoPorID();
                    break;
                case 6:
                    salir = true;
                    System.out.println("\n¡Gracias por usar el sistema!");
                    System.out.println("Saliendo...\n");
                    break;
                default:
                    System.out.println("\n Opción inválida. Intente nuevamente.\n");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│          MENÚ PRINCIPAL            │");
        System.out.println("├────────────────────────────────────┤");
        System.out.println("│ 1. Ver catálogo completo           │");
        System.out.println("│ 2. Consultar equipo por ID         │");
        System.out.println("│ 3. Ordenar por consumo eléctrico   │");
        System.out.println("│ 4. Agregar nuevo equipo            │");
        System.out.println("│ 5. Eliminar equipo por ID          │");
        System.out.println("│ 6. Salir                           │");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void verCatalogoCompleto() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       CATÁLOGO COMPLETO DE EQUIPOS     ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        List<Equipo> catalogo = sistema.getCatalogo();

        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.\n");
            return;
        }

        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println("Equipo #" + (i + 1) + ":");
            System.out.println(catalogo.get(i));
            System.out.println("----------------------------------------");
        }

        System.out.println("Total de equipos: " + catalogo.size() + "\n");
        pausar();
    }

    private static void consultarEquipoPorID() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       CONSULTAR EQUIPO POR ID          ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.print("Ingrese el ID del equipo: ");
        String id = scanner.nextLine().trim();

        Equipo equipo = sistema.buscarEquipo(id);

        if (equipo != null) {
            System.out.println("\nEquipo encontrado:\n");
            System.out.println(equipo);
        } else {
            System.out.println("\nNo se encontró ningún equipo con el ID: " + id + "\n");
        }

        pausar();
    }

    private static void ordenarPorConsumo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ORDENAR POR CONSUMO ELÉCTRICO        ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        sistema.ordenarPorConsumo();
        System.out.println("Catálogo ordenado por consumo eléctrico (menor a mayor)\n");

        List<Equipo> catalogo = sistema.getCatalogo();

        for (int i = 0; i < catalogo.size(); i++) {
            Equipo e = catalogo.get(i);
            System.out.printf("%d. [%s] %s - %.2f W\n",
                    (i + 1), e.getId(), e.getNombre(), e.getConsumoElectrico());
        }

        System.out.println();
        pausar();
    }

    private static void agregarNuevoEquipo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        AGREGAR NUEVO EQUIPO            ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.println("Seleccione el tipo de equipo:");
        System.out.println("1. Equipo Mecánico");
        System.out.println("2. Equipo Óptico");
        System.out.println("3. Equipo de Electromagnetismo");
        System.out.println("4. Equipo de Física Moderna");
        System.out.print("\nOpción: ");

        int tipo = leerOpcion();

        try {
            Equipo nuevoEquipo = null;

            switch (tipo) {
                case 1:
                    nuevoEquipo = crearEquipoMecanico();
                    break;
                case 2:
                    nuevoEquipo = crearEquipoOptico();
                    break;
                case 3:
                    nuevoEquipo = crearEquipoElectromagnetismo();
                    break;
                case 4:
                    nuevoEquipo = crearEquipoModerno();
                    break;
                default:
                    System.out.println("\nTipo de equipo inválido.\n");
                    return;
            }

            if (nuevoEquipo != null) {
                sistema.agregarEquipo(nuevoEquipo);
                System.out.println("\nEquipo agregado exitosamente al catálogo.\n");
            }

        } catch (Exception e) {
            System.out.println("\nError al agregar el equipo: " + e.getMessage() + "\n");
        }

        pausar();
    }

    private static EMecanica crearEquipoMecanico() {
        System.out.println("\n--- Datos del Equipo Mecánico ---");

        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine().trim();

        System.out.print("Consumo eléctrico (W): ");
        float consumo = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Características: ");
        String caracteristicas = scanner.nextLine().trim();

        System.out.print("Tipo de sensor: ");
        String tipoSensor = scanner.nextLine().trim();

        System.out.print("Rango de medición: ");
        float rango = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Precisión: ");
        float precision = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Unidad de medida: ");
        String unidad = scanner.nextLine().trim();

        System.out.print("¿Requiere calibración? (s/n): ");
        boolean requiereCalibracion = scanner.nextLine().trim().equalsIgnoreCase("s");

        return new EMecanica(id, nombre, fabricante, consumo, caracteristicas,
                tipoSensor, rango, precision, unidad, requiereCalibracion);
    }

    private static EOptica crearEquipoOptico() {
        System.out.println("\n--- Datos del Equipo Óptico ---");

        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine().trim();

        System.out.print("Consumo eléctrico (W): ");
        float consumo = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Características: ");
        String caracteristicas = scanner.nextLine().trim();

        System.out.print("Longitud de onda (nm): ");
        float longitudOnda = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Potencia óptica (mW): ");
        float potencia = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Tipo de fuente: ");
        String tipoFuente = scanner.nextLine().trim();

        System.out.print("Rango espectral: ");
        String rangoEspectral = scanner.nextLine().trim();

        System.out.print("Material óptico: ");
        String material = scanner.nextLine().trim();

        return new EOptica(id, nombre, fabricante, consumo, caracteristicas,
                longitudOnda, potencia, tipoFuente, rangoEspectral, material);
    }

    private static EElectromagnetismo crearEquipoElectromagnetismo() {
        System.out.println("\n--- Datos del Equipo de Electromagnetismo ---");

        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine().trim();

        System.out.print("Consumo eléctrico (W): ");
        float consumo = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Características: ");
        String caracteristicas = scanner.nextLine().trim();

        System.out.print("Voltaje máximo (V): ");
        float voltaje = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Corriente máxima (A): ");
        float corriente = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Tipo de conexión: ");
        String tipoConexion = scanner.nextLine().trim();

        System.out.print("Frecuencia de operación (Hz): ");
        float frecuencia = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("¿Tiene protección? (s/n): ");
        boolean tieneProteccion = scanner.nextLine().trim().equalsIgnoreCase("s");

        return new EElectromagnetismo(id, nombre, fabricante, consumo, caracteristicas,
                voltaje, corriente, tipoConexion, frecuencia, tieneProteccion);
    }

    private static EModerna crearEquipoModerno() {
        System.out.println("\n--- Datos del Equipo de Física Moderna ---");

        System.out.print("ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine().trim();

        System.out.print("Consumo eléctrico (W): ");
        float consumo = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Características: ");
        String caracteristicas = scanner.nextLine().trim();

        System.out.print("Energía máxima (eV): ");
        float energia = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Sensibilidad: ");
        float sensibilidad = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("Precisión de medición (%): ");
        float precision = Float.parseFloat(scanner.nextLine().trim());

        System.out.print("¿Requiere blindaje? (s/n): ");
        boolean requiereBlindaje = scanner.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("Tipo de detección: ");
        String tipoDeteccion = scanner.nextLine().trim();

        return new EModerna(id, nombre, fabricante, consumo, caracteristicas,
                energia, sensibilidad, precision, requiereBlindaje, tipoDeteccion);
    }

    private static void eliminarEquipoPorID() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       ELIMINAR EQUIPO POR ID           ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.print("Ingrese el ID del equipo a eliminar: ");
        String id = scanner.nextLine().trim();

        System.out.print("¿Está seguro de eliminar el equipo " + id + "? (s/n): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = sistema.eliminarEquipo(id);

            if (eliminado) {
                System.out.println("\nEquipo eliminado exitosamente.\n");
            } else {
                System.out.println("\nNo se encontró ningún equipo con el ID: " + id + "\n");
            }
        } else {
            System.out.println("\nOperación cancelada.\n");
        }

        pausar();
    }

    private static void pausar() {
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
        System.out.println();
    }
}
