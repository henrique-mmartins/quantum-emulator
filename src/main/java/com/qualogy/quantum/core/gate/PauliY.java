package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class PauliY extends Gate { //Pauli-Y gate
    private static Complex[][] matrix = {{new Complex(0), new Complex(0, -1)},
            {new Complex(0, 1), new Complex(0)}};
    private static int[] unit = {0};

    public PauliY() {
        super(1, matrix, unit, Type.Y);
    }

    public PauliY(int[] i) {
        super(1, matrix, i, Type.Y);
    }
}