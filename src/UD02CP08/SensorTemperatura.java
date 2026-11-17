package UD02CP08;

import java.util.Random;

public class SensorTemperatura extends Thread {
    private final BufferTemperatura buffer;
    private final int totalMediciones;
    private final Random random = new Random();

    public SensorTemperatura(BufferTemperatura buffer, int totalMediciones) {
        this.buffer = buffer;
        this.totalMediciones = totalMediciones;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalMediciones; i++) {
                int temp = 15 + random.nextInt(21);

                buffer.publicar(temp, i);
                System.out.printf(" ", i, temp);

                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            buffer.cerrar();
            System.out.println("buffer cerrado");
        }
    }
}
