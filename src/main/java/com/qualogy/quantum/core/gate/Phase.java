package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class Phase extends Gate {

    private static Complex[][] matrix = {{Complex.ONE, Complex.ZERO}, {Complex.ZERO, null}};
    private static int[] unit = {0};

    public Phase(double phi) {
        super(1, matrix, unit, Type.Phase);
        super.matrix[1][1] = (Complex.I.multiply(phi)).exp();
    }

    public Phase(double phi, int[] i) {
        super(1, matrix, unit, Type.Phase);
        super.matrix[1][1] = (Complex.I.multiply(phi)).exp();
    }
}