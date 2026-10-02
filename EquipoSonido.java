public class EquipoSonido extends Equipo {

    // Potencia del equipo expresada en kilovatios
    private double potenciaNominal;

    // Constructor
    public EquipoSonido(String codigoInventario, double tarifaDiaria,
                        double potenciaNominal) {

        super(codigoInventario, tarifaDiaria);

        this.potenciaNominal = potenciaNominal;
    }

    // Devuelve la potencia nominal
    public double getPotenciaNominal() {
        return potenciaNominal;
    }

    // Calcula el costo total del equipo de sonido
    @Override
    public double calcularCostoTotal(int dias) {

        double costoBase = calcularCostoBase(dias);

        // Se cobran Q100 por cada kilovatio por cada día
        double recargo = potenciaNominal * 100 * dias;

        return costoBase + recargo;
    }
}