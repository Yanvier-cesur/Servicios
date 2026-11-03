package UD02CP06;

import java.math.BigInteger;

public class fibonacci implements Runnable {
    private final int i;
    private final fibonacci[] numero;
    private volatile BigInteger valor;
    private volatile boolean hecho = false;

    public fibonacci(int i, fibonacci[] numero) {
        this.i = i;
        this.numero = numero;
    }

    public BigInteger getValor() {
        return this.valor;
    }

    public boolean isHecho() {
        return this.hecho;
    }

    private fibonacci numero(int i) {
        return this.numero[i];
    }

    @Override
    public void run() {
        if (this.i == 0) {
            this.valor = BigInteger.ZERO;
            this.hecho = true;
        } else if (this.i == 1) {
            this.valor = BigInteger.ONE;
            this.hecho = true;
        } else {
            fibonacci iMinusOne = this.numero(this.i - 1);
            fibonacci iMinusTwo = this.numero(this.i - 2);

            while (!iMinusOne.isHecho() || !iMinusTwo.isHecho()) {
                Thread.yield();
            }

            this.valor = iMinusOne.getValor().add(iMinusTwo.getValor());
            this.hecho = true;
        }
    }
}