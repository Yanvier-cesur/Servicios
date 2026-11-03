package UD02CP06;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero fibonacci a calcular: ");
        int n = sc.nextInt();

        fibonacci[] numeros = new fibonacci[n + 1];
        Thread[] threads = new Thread[n + 1];

        numeros[0] = new fibonacci(0, numeros);
        threads[0] = new Thread(numeros[0]);

        for (int i = 1; i <= n; i++) {
            numeros[i] = new fibonacci(i, numeros);
            threads[i] = new Thread(numeros[i]);
        }

        for (Thread thread : threads) {
            if (thread != null) {
                thread.start();
            }
        }

        for (Thread thread : threads) {
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            System.out.println("Fibonacci " + i + ": " + numeros[i].getValor());
        }
        System.out.printf("FIBONACCI", n, numeros[n].getValor());
    }
}