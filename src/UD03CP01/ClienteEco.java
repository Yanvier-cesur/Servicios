package UD03CP01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteEco {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(
                     new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor eco. Escribe mensajes (FIN para salir):");

            String linea;
            while ((linea = teclado.readLine()) != null) {
                salida.println(linea);
                if (linea.equals("FIN")) {
                    break;
                }
                System.out.println("Servidor: " + entrada.readLine());
            }
            System.out.println("Cliente finalizado.");

        } catch (IOException e) {
            System.err.println("Error de comunicación: " + e.getMessage());
        }
    }
}