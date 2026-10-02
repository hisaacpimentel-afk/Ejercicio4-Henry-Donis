public class Cotizacion {

    private Equipo equipo;
    private int dias;
    private double costoTotal;

    // Constructor
    public Cotizacion(Equipo equipo, int dias) {
        this.equipo = equipo;
        this.dias = dias;
        this.costoTotal = equipo.calcularCostoTotal(dias);
    }

    // Devuelve el equipo cotizado
    public Equipo getEquipo() {
        return equipo;
    }

    // Devuelve la cantidad de días
    public int getDias() {
        return dias;
    }

    // Devuelve el costo calculado
    public double getCostoTotal() {
        return costoTotal;
    }

    // Muestra toda la información de la cotización
    public String mostrarCotizacion() {

        String estado;

        if (equipo.isDisponible()) {
            estado = "Disponible";
        } else {
            estado = "Alquilado";
        }

        return "\n--- COTIZACION ---" +
                "\nCodigo: " + equipo.getCodigoInventario() +
                "\nDias: " + dias +
                "\nTarifa diaria: Q" + equipo.getTarifaDiaria() +
                "\nDisponibilidad: " + estado +
                "\nCosto total: Q" + costoTotal;
    }
}