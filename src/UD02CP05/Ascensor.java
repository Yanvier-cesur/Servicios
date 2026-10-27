package UD02CP05;

import java.util.LinkedList;

import java.util.LinkedList;

public class Ascensor implements Runnable {
    private int pisoActual;
    private LinkedList<Usuario> usuariosPendientes;

    public Ascensor(int pisoInicial) {
        this.pisoActual = pisoInicial;
        this.usuariosPendientes = new LinkedList<>();
    }

    public synchronized int getPisoActual() {
        return pisoActual;
    }

    public synchronized void agregarUsuario(Usuario usuario) {
        usuariosPendientes.add(usuario);
    }

    @Override
    public void run() {
        while (true) {
            Usuario usuarioActual;

            synchronized (this) {
                if (usuariosPendientes.isEmpty()) {
                    break;
                }
                usuarioActual = usuariosPendientes.poll();
            }

            moverA(usuarioActual.getPisoOrigen());

            synchronized (this) {
                notifyAll();
            }

            pausaBreve();

            moverA(usuarioActual.getPisoDestino());

            synchronized (this) {
                notifyAll();
            }

            pausaBreve();
        }

        bajarAPisoMenosUno();
    }

    private void moverA(int pisoObjetivo) {
        while (pisoActual != pisoObjetivo) {
            if (pisoActual < pisoObjetivo) {
                pisoActual++;
            } else {
                pisoActual--;
            }
            System.out.println("Ascensor en piso " + pisoActual);
            pausaBreve();
        }
    }

    private void bajarAPisoMenosUno() {
        while (pisoActual != -1) {
            pisoActual--;
            System.out.println("Ascensor en piso " + pisoActual);
            pausaBreve();
        }
        System.out.println("Ascensor llegó a piso -1. Fin del programa.");
    }

    private void pausaBreve() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}