package UD02CP07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        System.out.println(" ordenacion por mezcla recurrente");

        Random random = new Random();
        int nElementos = random.nextInt(96) + 5;
        List<Integer> listaoriginal = new ArrayList<>();

        for (int i = 0; i < nElementos; i++) {
            listaoriginal.add(random.nextInt(100) + 1);
        }

        System.out.println("Lista original: " + listaoriginal.size());
        System.out.println(listaoriginal);

        nodo raiz = new nodo(listaoriginal);
        raiz.start();

        try {
            raiz.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("lista ordenada" + raiz.lista);
    }
}
