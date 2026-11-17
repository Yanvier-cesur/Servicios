package UD02CP08;

public class ControladorAmbiental extends Thread {
    private final BufferTemperatura buffer;
    private final int umbral;

    public ControladorAmbiental(BufferTemperatura buffer, int umbral) {
        this.buffer = buffer;
        this.umbral = umbral;
    }

    @Override
    public void run() {
        System.out.println("[CONTROL] esperando medicion...");

        while (true) {
            Integer temp = buffer.leer();

            if (temp == null) {
                break;
            }

            int num = buffer.getNumeroMedicion();

            if (temp > umbral) {
                System.out.printf("[CONTROL] lee medicion %02d: %d°C -> EXCEDE EL UMBRAL de %d°C, VENTILACION ACTIVADA%n",
                        num, temp, umbral);
            } else {
                System.out.printf("[CONTROL] lee medicion %02d: %d°C -> en rango normal, todo correcto%n",
                        num, temp);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("[CONTROL] fin del monitoreo");
    }
}
