package UD02CP05;

public class main {
    public static void main(String[] args) {
        Ascensor ascensor = new Ascensor(0);

        Usuario u1 = new Usuario(ascensor, 0, 3, "1");
        Usuario u2 = new Usuario(ascensor, 2, -1, "2");
        Usuario u3 = new Usuario(ascensor, 1, 5, "3");

        ascensor.agregarUsuario(u1);
        ascensor.agregarUsuario(u2);
        ascensor.agregarUsuario(u3);

        Thread tU1 = new Thread(u1);
        Thread tU2 = new Thread(u2);
        Thread tU3 = new Thread(u3);

        tU1.start();
        tU2.start();
        tU3.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Thread tAscensor = new Thread(ascensor);
        tAscensor.start();
    }
}