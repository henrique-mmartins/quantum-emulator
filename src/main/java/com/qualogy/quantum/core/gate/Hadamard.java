package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class Hadamard extends Gate {

    private static Complex[][] matrix = {{new Complex(1 / Math.sqrt(2)), new Complex(1 / Math.sqrt(2))},
            {new Complex(1 / Math.sqrt(2)), new Complex(-1 / Math.sqrt(2))}};

    private static int[] unit = {0};

    public Hadamard() {
        super(1, matrix, unit, Type.H);
    }

    public Hadamard(int[] rows) {
        super(1, matrix, rows, Type.H);
    }
}