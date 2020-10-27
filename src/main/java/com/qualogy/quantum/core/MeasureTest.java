package com.qualogy.quantum.core;

import com.qualogy.quantum.core.gate.Gate;
import com.qualogy.quantum.core.gate.Measure;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class MeasureTest {
    public static void main(String args[])
    {
        Circuit circuit = new Circuit(2);
        Complex[][] start = {{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))}};
        List<Gate> gates = new ArrayList<>();
        gates.add(new Measure(0));
        gates.add(new Measure(1));
        circuit.setStart(start);
        circuit.setGates(gates);
        circuit.restStep();
        circuit.measureAll();
    }
}