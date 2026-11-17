package UD02CP08;

public class BufferTemperatura {
    private int temperatura;
    private int numeroMedicion;
    private volatile boolean disponible = false;
    private volatile boolean cerrado = false;

    public void publicar(int temperatura, int numMedicion) {
        while (disponible && !cerrado) {
            Thread.yield();
        }
        this.temperatura = temperatura;
        this.numeroMedicion = numMedicion;
        this.disponible = true;
    }

    public Integer leer() {
        while (!disponible && !cerrado) {
            Thread.yield();
        }
        if (!disponible && cerrado) {
            return null;
        }
        int tempLeida = this.temperatura;
        this.disponible = false;
        return tempLeida;
    }

    public int getNumeroMedicion() {
        return numeroMedicion;
    }

    public void cerrar() {
        this.cerrado = true;
    }
}
