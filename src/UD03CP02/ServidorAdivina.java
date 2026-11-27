package UD03CP02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;


public class ServidorAdivina {

    private static final int PUERTO = 5002;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor 'Adivina el número' en puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new Partida(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }

    private static class Partida implements Runnable {
        private final Socket cliente;

        Partida(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            int secreto = ThreadLocalRandom.current().nextInt(1, 101);
            int intentos = 0;

            try (Socket s = cliente;
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(s.getInputStream()));
                 PrintWriter salida = new PrintWriter(s.getOutputStream(), true)) {

                System.out.println("Nuevo cliente. Número secreto: " + secreto);

                String linea;
                while ((linea = entrada.readLine()) != null) {
                    int intento;
                    try {
                        intento = Integer.parseInt(linea.trim());
                    } catch (NumberFormatException e) {
                        salida.println("ERROR");
                        continue;
                    }

                    intentos++;

                    if (secreto > intento) {
                        salida.println("MAYOR");
                    } else if (secreto < intento) {
                        salida.println("MENOR");
                    } else {
                        salida.println("CORRECTO " + intentos);
                        System.out.println("Cliente acertó en " + intentos + " intentos.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error con el cliente: " + e.getMessage());
            }
        }
    }
}
