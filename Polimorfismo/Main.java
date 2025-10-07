import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcion;

        do {
            System.out.println("\n=== SIMULADOR DE MODELOS MATEMÁTICOS ===");
            System.out.println("1. Registrar modelo estadístico");
            System.out.println("2. Registrar modelo probabilístico");
            System.out.println("3. Registrar modelo geométrico");
            System.out.println("4. Listar modelos");
            System.out.println("5. Usar un modelo");
            System.out.println("6. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nombre del modelo estadístico: ");
                    String nombre = sc.nextLine();
                    System.out.print("¿Cuántos datos desea ingresar?: ");
                    int n = sc.nextInt();
                    List<Float> datos = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Dato " + (i + 1) + ": ");
                        datos.add(sc.nextFloat());
                    }
                    List<Parametro> params = List.of(new ConjuntoParametros("Datos", datos));
                    MEstadistico modelo = new MEstadistico(nombre, params);
                    sistema.agregarModelo(modelo);
                    System.out.println("Modelo estadístico registrado correctamente.");
                }

                case 2 -> {
                    System.out.print("Ingrese el nombre del modelo probabilístico: ");
                    String nombre = sc.nextLine();
                    System.out.print("Casos favorables: ");
                    float fav = sc.nextFloat();
                    System.out.print("Casos totales: ");
                    float tot = sc.nextFloat();
                    List<Parametro> params = List.of(
                            new ParametroUnico("Casos favorables", fav),
                            new ParametroUnico("Casos totales", tot)
                    );
                    MProbabilistico modelo = new MProbabilistico(nombre, params);
                    sistema.agregarModelo(modelo);
                    System.out.println("Modelo probabilístico registrado correctamente.");
                }

                case 3 -> {
                    System.out.print("Ingrese el nombre del modelo geométrico: ");
                    String nombre = sc.nextLine();
                    System.out.println("Seleccione la figura:");
                    System.out.println("1. Cuadrilátero\n2. Triángulo\n3. Círculo\n4. Trapecio");
                    int tipo = sc.nextInt();

                    List<Parametro> params = new ArrayList<>();
                    MGeometrico.Figura figura = null;

                    switch (tipo) {
                        case 1 -> {
                            figura = MGeometrico.Figura.CUADRILATERO;
                            System.out.print("Base: ");
                            float base = sc.nextFloat();
                            System.out.print("Altura: ");
                            float altura = sc.nextFloat();
                            params.add(new ParametroUnico("Base", base));
                            params.add(new ParametroUnico("Altura", altura));
                        }
                        case 2 -> {
                            figura = MGeometrico.Figura.TRIANGULO;
                            System.out.print("Base: ");
                            float base = sc.nextFloat();
                            System.out.print("Altura: ");
                            float altura = sc.nextFloat();
                            params.add(new ParametroUnico("Base", base));
                            params.add(new ParametroUnico("Altura", altura));
                        }
                        case 3 -> {
                            figura = MGeometrico.Figura.CIRCULO;
                            System.out.print("Radio: ");
                            float radio = sc.nextFloat();
                            params.add(new ParametroUnico("Radio", radio));
                        }
                        case 4 -> {
                            figura = MGeometrico.Figura.TRAPECIO;
                            System.out.print("Base mayor: ");
                            float base1 = sc.nextFloat();
                            System.out.print("Base menor: ");
                            float base2 = sc.nextFloat();
                            System.out.print("Altura: ");
                            float altura = sc.nextFloat();
                            params.add(new ParametroUnico("Base mayor", base1));
                            params.add(new ParametroUnico("Base menor", base2));
                            params.add(new ParametroUnico("Altura", altura));
                        }
                        default -> System.out.println("Opción de figura no válida.");
                    }

                    if (figura != null) {
                        MGeometrico modelo = new MGeometrico(nombre, params, figura);
                        sistema.agregarModelo(modelo);
                        System.out.println("Modelo geométrico registrado correctamente.");
                    }
                }

                case 4 -> {
                    List<String> modelos = sistema.listarModelos();
                    if (modelos.isEmpty()) {
                        System.out.println("No hay modelos registrados.");
                    } else {
                        System.out.println("Modelos registrados:");
                        for (int i = 0; i < modelos.size(); i++) {
                            System.out.println(i + " - " + modelos.get(i));
                        }
                    }
                }

                case 5 -> {
                    List<String> modelos = sistema.listarModelos();
                    if (modelos.isEmpty()) {
                        System.out.println("No hay modelos para usar.");
                    } else {
                        System.out.println("Seleccione el modelo que desea usar:");
                        for (int i = 0; i < modelos.size(); i++) {
                            System.out.println(i + " - " + modelos.get(i));
                        }
                        int idx = sc.nextInt();
                        sistema.usarModelo(idx);
                        System.out.println("Modelo ejecutado. Revise el historial para ver los resultados.");
                    }
                }

                case 6 -> {
                    System.out.println("=== HISTORIAL DE ACCIONES ===");
                    for (String h : sistema.getHistorial()) {
                        System.out.println(h);
                    }
                }

                case 0 -> System.out.println("Saliendo del programa...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
