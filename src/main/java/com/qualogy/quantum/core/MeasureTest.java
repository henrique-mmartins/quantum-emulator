package com.qualogy.quantum.core;

import com.qualogy.quantum.core.gate.Gate;
import com.qualogy.quantum.core.gate.Hadamard;
import com.qualogy.quantum.core.gate.Measure;
import com.qualogy.quantum.core.gate.NotGate;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class MeasureTest {
    public static void main(String args[]) {

        Circuit circuit1 = new Circuit(2);
        Complex[][] start1 = {{Complex.ONE}, {Complex.ZERO}, {Complex.ZERO}, {Complex.ZERO}};
        ArrayList<Gate> gates1 = new ArrayList<>();
        gates1.add(new Hadamard()); //set to bit 1
        //gates.add(new Measure(0));
        circuit1.setStart(start1);
        circuit1.setGates(gates1);
        circuit1.calculateAllSteps();
        circuit1.measureAll(); //sorta like an end state
        System.out.println("_________________________________");
        Circuit circuit = new Circuit(1);
        Complex[][] start = {{Complex.ONE}, {Complex.ZERO}};
        List<Gate> gates = new ArrayList<>();
        gates.add(new Hadamard());
        circuit.setStart(start);
        circuit.setGates(gates);
        circuit.calculateAllSteps();
        circuit.measureAll();

    }
}