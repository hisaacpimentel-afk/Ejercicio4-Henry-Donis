public class CamaraVideo extends Equipo {

    // Resolución máxima vertical de la cámara
    private int resolucionMaxima;

    // Constructor
    public CamaraVideo(String codigoInventario, double tarifaDiaria,
                       int resolucionMaxima) {

        super(codigoInventario, tarifaDiaria);

        this.resolucionMaxima = resolucionMaxima;
    }

    // Devuelve la resolución máxima
    public int getResolucionMaxima() {
        return resolucionMaxima;
    }

    // Calcula el costo total de la cámara
    @Override
    public double calcularCostoTotal(int dias) {

        double costoBase = calcularCostoBase(dias);

        // Las cámaras con resolución mayor a 1080
        // pagan Q75 adicionales una sola vez
        if (resolucionMaxima > 1080) {
            return costoBase + 75;
        }

        return costoBase;
    }
}