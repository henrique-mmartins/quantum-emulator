package com.qualogy.quantum.core;

import org.apache.commons.math3.complex.Complex;

public class QuantumService {

    private int qBits;
    private int size;
    private Complex[][] system;

    public QuantumService(int numBits) {
        qBits = numBits;
        size = (int) Math.pow(2, qBits);
        system = new Complex[size][1];
    }

    public Complex getC(int index) {
        return system[index][0];
    }

    public Complex getSingleQC(int qubit) {
        if (qBits > 1)
            return null;
        return getC(qubit);
    }

    public void set(int index, Complex value) {
        system[index][0] = value;
    }

    public boolean isPossible() {
        double prob = 0;
        for (int i = 0; i < size; i++) {
            prob += prob(getC(i));
        }
        if (Math.abs(1.0 - prob) < 0.00000001)
            return true;
        return false;
    }

    public void normalize() {
        if (!isPossible()) {
            double coeff = 0;
            for (int i = 0; i < size; i++) {
                coeff += prob(getC(i));
            }
            coeff = Math.sqrt(coeff);
            for (int i = 0; i < system.length; i++) {
                for (int j = 0; j < system[i].length; j++) {
                    system[i][j] = system[i][j].divide(coeff);
                }
            }
        }
    }


    public double probState(int i) {
        return prob(getC(i));
    }

    //returns probability of a single qubit being one
    public double calculateProbabilityStateOne(int j) {
        double prob = 0;
        for (int i = 0; i < size; i++) {
            if (i / ((int) Math.pow(2, j)) % 2 == 1)
                prob += prob(getC(i));
        }
        return prob;
    }

    public double calculateProbabilityStateZero(int j) {
        return 1 - calculateProbabilityStateOne(j);
    }

    //returns formatted string for complex number
    public static String display(Complex c)  {

        int sigFigs = 2;
        double r = c.getReal();
        boolean realInt = isInt(r);
        double i = c.getImaginary();
        boolean imaginaryInt = isInt(i);
        boolean pureImaginary = (r == 0);
        boolean pureReal = (i == 0);

        String op = "+";
        if (i < 0) {
            op = "";
        }

        String left; //real
        String right; //imaginary

        if (pureImaginary) {
            left = "";
        } else if (realInt && !pureReal) {
            left = "" + Math.abs(Math.round(r)) + op;
        } else if (realInt && pureReal) {
            left = "" +  Math.abs(Math.round(r));
        } else if (pureReal) {
            left = "" +  Math.abs((Math.round(Math.pow(10, sigFigs) * r) / Math.pow(10, sigFigs)));
        } else {
            left = "" +  Math.abs((Math.round(Math.pow(10, sigFigs) * r) / Math.pow(10, sigFigs))) + op;
        }

        if (pureReal) {
            right = "";
        } else if (imaginaryInt && Math.round(i) != 1 && Math.round(i) != -1) {
            right = "" + Math.round(i) + "i";
        } else if (imaginaryInt && Math.round(i) == 1) {
            right = "i";
        } else if (imaginaryInt && Math.round(i) == -1) {
            right = "-i";
        } else {
            right = "" + (Math.round(Math.pow(10, sigFigs) * i) / Math.pow(10, sigFigs)) + "i";
        }
        if (r == 0 && i == 0) {
            return "0";
        }
        return String.format("%s%s", left, right);
    }

    public static void transposeMatrix(Complex[][] m) {
        Complex[][] temp = new Complex[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                temp[j][i] = m[i][j];
            }
        }
        for (int i = 0; i < m.length; i++) {
            System.arraycopy(temp[i], 0, m[i], 0, m[0].length);
        }
    }

    public int getqBits() {
        return qBits;
    }

    public void setqBits(int qBits) {
        this.qBits = qBits;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Complex[][] getSystem() {
        return system;
    }

    public void setSystem(Complex[][] system) {
        this.system = system;
    }

    private double prob(Complex c) {
        return Math.pow(c.abs(), 2);
    }

    private static boolean isInt(double d) //checks if double is sufficiently close to an integer
    {
        double error = Math.pow(10, -8);
        return Math.abs(d - Math.round(d)) < error;
    }

    public static int bitsToRepresent(int i) {
        i += 1;
        i = (int) (Math.ceil(log2(i)));
        return i;
    }

    private static double logB(double d, double b) {
        return Math.log(d) / Math.log(b);
    }

    private static double log2(double d) {
        return logB(d, 2);
    }

}