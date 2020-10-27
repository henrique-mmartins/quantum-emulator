package com.qualogy.quantum.core.gate;

import org.apache.commons.math3.complex.*;

public abstract class Gate {

    public Complex[][] matrix;
    private int qubits;
    private int[] rows;
    private int size;
    private String type;

    public Gate(int size, Complex[][] representation, int[] rows, String type) {
        qubits = size;
        matrix = new Complex[representation.length][representation[0].length];
        set(matrix, representation);
        this.rows = rows;
        this.type = type;
        this.size = (int) (Math.pow(2, qubits));
    }

    public Gate() {
    }

    public int getQubits() {
        return qubits;
    }

    public void setQubits(int qubits) {
        this.qubits = qubits;
    }

    public int[] getRows() {
        return rows;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void set(Complex[][] a, Complex[][] representation)  {
        int i = 0;
        int j = 0;
        for (Complex[] row : representation) {
            for (Complex c : row) {
                a[i][j] = c;
                j++;
            }
            j = 0;
            i++;
        }
    }
}