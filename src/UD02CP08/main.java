package UD02CP08;

public class main {
    public static void main(String[] args) {
        final int UMBRAL = 28;
        final int TOTAL_MEDICIONES = 12;

        System.out.println(" Monitorizacion ambiental del invernadero (productor/consumidor) ");
        System.out.println("Espera activa: el sensor no sobrescribe hasta que el controlador ha leido.");
        System.out.println("Umbral de ventilacion: " + UMBRAL + "°C | mediciones: " + TOTAL_MEDICIONES);

        BufferTemperatura buffer = new BufferTemperatura();

        SensorTemperatura sensor = new SensorTemperatura(buffer, TOTAL_MEDICIONES);
        ControladorAmbiental controlador = new ControladorAmbiental(buffer, UMBRAL);

        sensor.start();
        controlador.start();

        try {
            sensor.join();
            controlador.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}