package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class Swap extends Gate {

    private static Complex[][] matrix = {{new Complex(1), new Complex(0), new Complex(0), new Complex(0)},
            {new Complex(0), new Complex(0), new Complex(1), new Complex(0)},
            {new Complex(0), new Complex(1), new Complex(0), new Complex(0)},
            {new Complex(0), new Complex(0), new Complex(0), new Complex(1)}};
    private static int[] unit = {0, 1};

    public Swap() {
        super(2, matrix, unit, "SWAP");
    }

    public Swap(int[] i) {
        super(2, matrix, i, "SWAP");
    }
}