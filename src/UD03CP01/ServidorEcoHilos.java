package UD03CP01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEcoHilos {

    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor eco (hilos) escuchando en puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new ManejadorCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }

    private static class ManejadorCliente implements Runnable {
        private final Socket cliente;

        ManejadorCliente(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            String id = "[" + Thread.currentThread().getName() + "] ";
            try (Socket s = cliente;
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(s.getInputStream()));
                 PrintWriter salida = new PrintWriter(s.getOutputStream(), true)) {

                System.out.println(id + "Cliente conectado: " + s.getInetAddress());

                String linea;
                while ((linea = entrada.readLine()) != null) {
                    if (linea.equals("FIN")) {
                        System.out.println(id + "Cliente solicita cierre.");
                        break;
                    }
                    System.out.println(id + "Recibido: " + linea);
                    salida.println(linea.toUpperCase());
                }
            } catch (IOException e) {
                System.err.println(id + "Error con el cliente: " + e.getMessage());
            }
            System.out.println(id + "Conexión cerrada.");
        }
    }
}
