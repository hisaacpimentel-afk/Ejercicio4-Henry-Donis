public class Alquiler {

    private Equipo equipo;
    private int dias;
    private double montoCobrado;
    private boolean activo;

    // Constructor
    public Alquiler(Equipo equipo, int dias) {

        this.equipo = equipo;
        this.dias = dias;
        this.montoCobrado = equipo.calcularCostoTotal(dias);
        this.activo = true;

        // El equipo deja de estar disponible
        equipo.marcarAlquilado();
    }

    // Devuelve el equipo alquilado
    public Equipo getEquipo() {
        return equipo;
    }

    // Devuelve la cantidad de días
    public int getDias() {
        return dias;
    }

    // Devuelve el monto cobrado
    public double getMontoCobrado() {
        return montoCobrado;
    }

    // Indica si el alquiler continúa activo
    public boolean isActivo() {
        return activo;
    }

    // Registra la devolución
    public void registrarDevolucion() {

        if (activo) {
            activo = false;

            // El equipo vuelve a estar disponible
            equipo.marcarDisponible();
        }
    }
}