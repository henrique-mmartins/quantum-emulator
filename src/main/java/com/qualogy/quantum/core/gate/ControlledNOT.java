package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class ControlledNOT extends Gate {

    private static Complex[][] matrix = {{new Complex(1), new Complex(0), new Complex(0), new Complex(0)},
            {new Complex(0), new Complex(1), new Complex(0), new Complex(0)},
            {new Complex(0), new Complex(0), new Complex(0), new Complex(1)},
            {new Complex(0), new Complex(0), new Complex(1), new Complex(0)}};

    private static int[] unit = {0};

    public ControlledNOT() {
        super(2, matrix, unit,  Type.C);
    }

    public ControlledNOT(int[] rows) {
        super(2, matrix, rows, Type.C);
    }
}