package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class PauliZ extends Gate {
    private static Complex[][] matrix = {{new Complex(1), new Complex(0)},
            {new Complex(0), new Complex(-1)}};
    private static int[] unit = {0};

    public PauliZ() {
        super(1, matrix, unit, "Z");
    }

    public PauliZ(int[] i) {
        super(1, matrix, i, "Z");
    }
}