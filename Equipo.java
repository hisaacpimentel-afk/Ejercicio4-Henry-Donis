public abstract class Equipo {

    // Atributos comunes para todos los equipos
    private String codigoInventario;
    private double tarifaDiaria;
    private boolean disponible;

    // Constructor
    public Equipo(String codigoInventario, double tarifaDiaria) {
        this.codigoInventario =
         codigoInventario;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    // Devuelve el código del equipo
    public String getCodigoInventario() {
        return codigoInventario;
    }

    // Devuelve la tarifa diaria
    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    // Indica si el equipo está disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Calcula el costo básico según los días
    public double calcularCostoBase(int dias) {
        return tarifaDiaria * dias;
    }

    // Cada categoría calculará su costo total de forma diferente
    public abstract double calcularCostoTotal(int dias);

    // Cambia el equipo a no disponible
    public void marcarAlquilado() {
        disponible = false;
    }

    // Cambia el equipo nuevamente a disponible
    public void marcarDisponible() {
        disponible = true;
    }
}