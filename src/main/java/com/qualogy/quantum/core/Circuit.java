package com.qualogy.quantum.core;

import com.qualogy.quantum.core.gate.Gate;
import com.qualogy.quantum.core.gate.Measure;
import com.qualogy.quantum.core.gate.Type;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

public class Circuit {

    private QuantumService start;
    private List<Gate> gates = new ArrayList<>();
    private QuantumService current;
    private MasterGate currentMaster;
    private int qBits;
    private int step = 0;


    public Circuit(int size) {
        qBits = size;
        start = new QuantumService(size);
        current = new QuantumService(size);
        currentMaster = new MasterGate(size);
    }


    private Complex[][] multiply(Complex[][] one, Complex[][] two) {
        Complex[][] result = new Complex[two.length][two[0].length];
        if (result[0].length == 1) {
            for (int i = 0; i < result.length; i++) {
                Complex sum = new Complex(0);
                for (int j = 0; j < result.length; j++) {
                    sum = sum.add((one[i][j].multiply(two[j][0])));
                }
                result[i][0] = sum;
            }
        } else {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    Complex sum = new Complex(0);
                    for (int k = 0; k < result.length; k++) {
                        sum.add((one[i][k].multiply(two[k][j])));
                    }
                    result[i][j] = sum;
                }
            }
        }
        return result;
    }


    public void measure(int i) {
        this.addGate(new Measure(i));
    }

    public void measureAll() {
        for (int i = 0; i < qBits; i++) {
            this.measure(i);
        }
        this.calculateAllSteps();
        for (Complex[] i : current.getSystem()) {
            if (!i[0].equals(Complex.ZERO)) {
                i[0] = Complex.ONE;
            }
        }
    }

    public void setStart(Complex[][] starter) {

        int count = 0;
        for (Complex[] c : starter) {
            start.getSystem()[count][0] = c[0];
            count++;
        }
        count = 0;
        for (Complex[] c : start.getSystem()) {
            current.getSystem()[count][0] = c[0];
            count++;
        }
    }


    public void addGate(Gate g) {
        gates.add(g);
    }

    public void setGates(List<Gate> g) {
        gates = g;
    }

    public StringBuilder step() {
        for (int i = 0; i < current.getSystem().length; i++) {
            if (start.getSystem()[i][0] == null) {
                start.getSystem()[i][0] = Complex.ZERO;
            }
            if (current.getSystem()[i][0] == null) {
                current.getSystem()[i][0] = Complex.ZERO;
            }
        }
        if (!gates.get(step).getType().equals(Type.Fourier)){
            for (int i = 0; i < gates.get(step).getSize(); i++) {
                for (int j = 0; j < gates.get(step).getSize(); j++) {
                    if (gates.get(step).matrix[i][j] == null) {
                        gates.get(step).matrix[i][j] = Complex.ZERO;
                    }
                }
            }
        }

        if (gates.get(step).getType().equals(Type.M)) {
            currentMaster = new MasterGate(current.getSize());
            int which = gates.get(step).getQubits();
            Complex out;
            if (Math.random() < current.calculateProbabilityStateOne(which)) {
                out = Complex.ONE;
            } else {
                out = Complex.ZERO;
            }
            char bit;
            if (Complex.equals(out, Complex.ZERO)) {
                bit = '0';
            } else if (Complex.equals(out, Complex.ONE)) {
                bit = '1';
            } else {
                bit = 'u';
            }
            Complex[][] change = new Complex[current.getSize()][1];
            double div = 0;
            for (int i = 0; i < current.getSize(); i++) {
                String temp = MasterGate.binaryRepresentation(i, current.getqBits());
                if (temp.charAt(current.getqBits() - which) != bit) {
                    change[i][0] = Complex.ZERO;
                } else if (temp.charAt(current.getqBits() - which) == bit) {
                    div += Math.pow(current.getSystem()[i][0].abs(), 2);
                }

            }
            for (int i = 0; i < current.getSize(); i++) {
                String temp = MasterGate.binaryRepresentation(i, current.getqBits());
                if (temp.charAt(current.getqBits() - which) != bit) {
                    change[i][0] = Complex.ZERO;
                } else if (temp.charAt(current.getqBits() - which) == bit) {
                    Complex a = current.getSystem()[i][0];
                    change[i][0] = a.divide(Math.sqrt(div));
                }
            }

            for (int i = 0; i < change.length; i++) {
                current.getSystem()[i][0] = change[i][0];
            }
        } else {
            currentMaster = new MasterGate(gates.get(step), gates.get(step).getRows(), qBits);
            Complex[][] change = multiply(currentMaster.gate, current.getSystem());
            for (int i = 0; i < change.length; i++) {
                current.getSystem()[i][0] = change[i][0];
            }
        }
        step++;

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < current.getSystem().length; i++) {
            value.append("\"")
                 .append(MasterGate.stringRepresentation(i, start.getqBits()))
                 .append("\" : \"").append(QuantumService.display((current.getSystem()[i][0])))
                 .append("\"");
            if (i < current.getSystem().length - 1){
                value.append(", ");
            }
        }

        return value;
    }

    public String calculateAllSteps() {
        StringBuilder value = new StringBuilder();
        int bound = gates.size();
        value.append("[");
        for (int i = step; i < bound; i++) {
            value.append("{").append(step()).append("}");
            if (i < bound - 1){
                value.append(", ");
            }
        }
        value.append("]");
        return value.toString();
    }

}
