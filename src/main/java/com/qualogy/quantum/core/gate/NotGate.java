package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class NotGate extends Gate {
    private static Complex[][] matrix = {{new Complex(0), new Complex(1)},
            {new Complex(1), new Complex(0)}};
    private static int[] unit = {0};

    NotGate() {
        super(1, matrix, unit, "X");
    }

    NotGate(int[] i) {
        super(1, matrix, i, "X");
    }
}