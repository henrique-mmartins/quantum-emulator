package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class Measure extends Gate {

    private int qubit;

    public Measure(int q) {
        super(1, new Complex[2][2], new int[0], Type.Measure);
        qubit = q;
    }

    public Measure() {
        super(1, new Complex[2][2], new int[0], Type.Measure);
        qubit = 0;
    }
}