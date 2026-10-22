package UD02CP05;

public class Usuario implements Runnable{
    private String Id;
    private int pisoOrigen;
    private int pisoDestino;
    private Ascensor ascensor;

    public Usuario(Ascensor ascensor, int pisoDestino, int pisoOrigen, String id) {
        this.ascensor = ascensor;
        this.pisoDestino = pisoDestino;
        this.pisoOrigen = pisoOrigen;
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getPisoOrigen() {
        return pisoOrigen;
    }

    public void setPisoOrigen(int pisoOrigen) {
        this.pisoOrigen = pisoOrigen;
    }

    public int getPisoDestino() {
        return pisoDestino;
    }

    public void setPisoDestino(int pisoDestino) {
        this.pisoDestino = pisoDestino;
    }

    public Ascensor getAscensor() {
        return ascensor;
    }

    public void setAscensor(Ascensor ascensor) {
        this.ascensor = ascensor;
    }

    public void esperarAscensor(Ascensor ascensor) {
        try {
            synchronized (ascensor) {
                System.out.println("Usuario " + Id + " espera en piso " + pisoOrigen);

                while (ascensor.getPisoActual() != pisoOrigen) {
                    ascensor.wait();
                }

                System.out.println("Usuario " + Id + " sube al ascensor.");

                while (ascensor.getPisoActual() != pisoDestino){
                    ascensor.wait();
                }

                System.out.println("Usuario " + Id + " baja del ascensor.");

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        esperarAscensor(this.ascensor);
    }
}
