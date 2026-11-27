package UD03CP02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteAdivina {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5002;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(
                     new InputStreamReader(System.in))) {

            System.out.println("Conectado al juego 'Adivina el número'.");
            System.out.println("He pensado un número entre 1 y 100. Adivínalo.");

            String intento;
            while ((intento = teclado.readLine()) != null) {
                salida.println(intento);

                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    break;
                }
                System.out.println(respuesta);

                if (respuesta.startsWith("CORRECTO")) {
                    System.out.println("¡Enhorabuena!");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error de comunicación: " + e.getMessage());
        }
    }
}
