import java.util.ArrayList;
import java.util.Scanner;

public class SistemaEnEscena {

    private ArrayList<Equipo> equipos;
    private ArrayList<Alquiler> alquileres;
    private double ingresosAcumulados;

    // Constructor
    public SistemaEnEscena() {
        equipos = new ArrayList<>();
        alquileres = new ArrayList<>();
        ingresosAcumulados = 0;
    }

    // Registra un equipo si el código no está repetido
    public boolean registrarEquipo(Equipo equipo) {
        if (buscarEquipo(equipo.getCodigoInventario()) != null) {
            return false;
        }

        equipos.add(equipo);
        return true;
    }

    // Busca un equipo por código
    public Equipo buscarEquipo(String codigoInventario) {
        for (Equipo equipo : equipos) {
            if (equipo.getCodigoInventario().equalsIgnoreCase(codigoInventario)) {
                return equipo;
            }
        }

        return null;
    }

    // Genera una cotización
    public Cotizacion generarCotizacion(String codigoInventario, int dias) {
        Equipo equipo = buscarEquipo(codigoInventario);

        if (equipo == null || dias <= 0) {
            return null;
        }

        return new Cotizacion(equipo, dias);
    }

    // Registra un alquiler
    public Alquiler registrarAlquiler(String codigoInventario, int dias) {
        Equipo equipo = buscarEquipo(codigoInventario);

        if (equipo == null || !equipo.isDisponible() || dias <= 0) {
            return null;
        }

        Alquiler alquiler = new Alquiler(equipo, dias);
        alquileres.add(alquiler);
        ingresosAcumulados += alquiler.getMontoCobrado();

        return alquiler;
    }

    // Registra una devolución
    public boolean registrarDevolucion(String codigoInventario) {
        for (Alquiler alquiler : alquileres) {
            if (alquiler.isActivo() &&
                alquiler.getEquipo().getCodigoInventario().equalsIgnoreCase(codigoInventario)) {

                alquiler.registrarDevolucion();
                return true;
            }
        }

        return false;
    }

    // Devuelve los ingresos acumulados
    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }

    // Muestra el resumen de los equipos
    public String mostrarResumen() {
        int disponibles = 0;
        int alquilados = 0;

        for (Equipo equipo : equipos) {
            if (equipo.isDisponible()) {
                disponibles++;
            } else {
                alquilados++;
            }
        }

        return "Registrados: " + equipos.size() +
               "\nDisponibles: " + disponibles +
               "\nAlquilados: " + alquilados +
               "\nIngresos: Q" + ingresosAcumulados;
    }

    // Menú principal
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaEnEscena sistema = new SistemaEnEscena();
        int opcion;

        do {
            System.out.println("\n--- ENESCENA ---");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Buscar equipo");
            System.out.println("3. Cotizar");
            System.out.println("4. Alquilar");
            System.out.println("5. Devolver");
            System.out.println("6. Resumen");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();

                    System.out.print("Tarifa diaria: ");
                    double tarifa = sc.nextDouble();

                    System.out.println("1. Proyector");
                    System.out.println("2. Camara");
                    System.out.println("3. Sonido");
                    System.out.print("Tipo: ");
                    int tipo = sc.nextInt();

                    Equipo equipo = null;

                    if (tipo == 1) {
                        System.out.print("Luminosidad: ");
                        int luminosidad = sc.nextInt();

                        System.out.print("Inalambrico (true/false): ");
                        boolean inalambrico = sc.nextBoolean();

                        equipo = new Proyector(
                                codigo, tarifa, luminosidad, inalambrico);

                    } else if (tipo == 2) {
                        System.out.print("Resolucion: ");
                        int resolucion = sc.nextInt();

                        equipo = new CamaraVideo(
                                codigo, tarifa, resolucion);

                    } else if (tipo == 3) {
                        System.out.print("Potencia kW: ");
                        double potencia = sc.nextDouble();

                        equipo = new EquipoSonido(
                                codigo, tarifa, potencia);
                    }

                    if (equipo != null && sistema.registrarEquipo(equipo)) {
                        System.out.println("Equipo registrado.");
                    } else {
                        System.out.println("No se pudo registrar.");
                    }

                    break;

                case 2:
                    System.out.print("Codigo: ");
                    String buscar = sc.nextLine();

                    Equipo encontrado = sistema.buscarEquipo(buscar);

                    if (encontrado != null) {
                        System.out.println("Codigo: " +
                                encontrado.getCodigoInventario());
                        System.out.println("Tarifa: Q" +
                                encontrado.getTarifaDiaria());
                        System.out.println("Disponible: " +
                                encontrado.isDisponible());
                    } else {
                        System.out.println("No encontrado.");
                    }

                    break;

                case 3:
                    System.out.print("Codigo: ");
                    String codigoCotizar = sc.nextLine();

                    System.out.print("Dias: ");
                    int diasCotizar = sc.nextInt();
                    sc.nextLine();

                    Cotizacion cotizacion =
                            sistema.generarCotizacion(codigoCotizar, diasCotizar);

                    if (cotizacion != null) {
                        System.out.println(cotizacion.mostrarCotizacion());
                    } else {
                        System.out.println("No se pudo cotizar.");
                    }

                    break;

                case 4:
                    System.out.print("Codigo: ");
                    String codigoAlquiler = sc.nextLine();

                    System.out.print("Dias: ");
                    int dias = sc.nextInt();
                    sc.nextLine();

                    Equipo e = sistema.buscarEquipo(codigoAlquiler);

                    if (e == null || !e.isDisponible()) {
                        System.out.println("Equipo no disponible.");
                        break;
                    }

                    System.out.println("Total: Q" +
                            e.calcularCostoTotal(dias));

                    System.out.print("Confirmar (s/n): ");
                    String confirmar = sc.nextLine();

                    if (confirmar.equalsIgnoreCase("s")) {
                        sistema.registrarAlquiler(codigoAlquiler, dias);
                        System.out.println("Alquiler registrado.");
                    } else {
                        System.out.println("Alquiler cancelado.");
                    }

                    break;

                case 5:
                    System.out.print("Codigo: ");
                    String devolver = sc.nextLine();

                    if (sistema.registrarDevolucion(devolver)) {
                        System.out.println("Equipo devuelto.");
                    } else {
                        System.out.println("No se pudo devolver.");
                    }

                    break;

                case 6:
                    System.out.println(sistema.mostrarResumen());
                    break;

                case 7:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 7);

        sc.close();
    }
}