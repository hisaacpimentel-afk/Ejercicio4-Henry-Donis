public class Proyector extends Equipo {

    // Características propias del proyector
    private int luminosidad;
    private boolean conectividadInalambrica;

    // Constructor
    public Proyector(String codigoInventario, double tarifaDiaria,
                     int luminosidad, boolean conectividadInalambrica) {

        super(codigoInventario, tarifaDiaria);

        this.luminosidad = luminosidad;
        this.conectividadInalambrica = conectividadInalambrica;
    }

    // Devuelve la luminosidad en lúmenes
    public int getLuminosidad() {
        return luminosidad;
    }

    // Indica si tiene conectividad inalámbrica
    public boolean tieneConectividadInalambrica() {
        return conectividadInalambrica;
    }

    // Calcula el costo total del proyector
    @Override
    public double calcularCostoTotal(int dias) {

        double costoBase = calcularCostoBase(dias);

        // Si tiene conexión inalámbrica se cobran Q50 por día
        if (conectividadInalambrica) {
            double recargo = 50 * dias;
            return costoBase + recargo;
        }

        return costoBase;
    }
}