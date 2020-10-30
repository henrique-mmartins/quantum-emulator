package com.qualogy.quantum.core;

import com.qualogy.quantum.core.gate.*;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class MeasureTest {
    public static void main(String args[]) {


        Circuit circuit = new Circuit(1);
        Complex[][] start = {{Complex.ONE}, {Complex.ZERO}};


        circuit.addGate(new ControlledNOT());
        circuit.addGate(new Fourier(4));
        circuit.addGate(new Hadamard());
        circuit.addGate(new Measure());
        circuit.addGate(new NotGate());
        circuit.addGate(new PauliY());
        circuit.addGate(new PauliZ());
        circuit.setStart(start);

        String s1 = circuit.calculateAllSteps();
        System.out.println(s1);
    }
}