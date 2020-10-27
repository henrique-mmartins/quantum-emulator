package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.Complex;

public class SquareRootNOTGate extends Gate {

    private static Complex[][] matrix = {{(Complex.ONE.add(Complex.I)).divide(2), (Complex.ONE.subtract(Complex.I)).divide(2)},
            {(Complex.ONE.subtract(Complex.I)).divide(2), (Complex.ONE.add(Complex.I)).divide(2)}};
    private static int[] unit = {0};

    public SquareRootNOTGate() {
        super(1, matrix, unit, Type.SqrtNOT);
    }

    public SquareRootNOTGate(int[] i) {
        super(1, matrix, i, Type.SqrtNOT);
    }
}