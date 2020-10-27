package com.qualogy.quantum.core;

import com.qualogy.quantum.core.gate.Gate;
import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MasterGate extends Gate {
    Complex[][] gate;

    MasterGate(int size) {
        gate = new Complex[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    gate[i][j] = new Complex(1.0);
                } else {
                    gate[i][j] = Complex.ZERO;
                }
            }
        }
    }

    MasterGate(Gate gate, int[] phase, int size) {
        this.gate = new Complex[(int) Math.pow(2, size)][(int) Math.pow(2, size)];
        for (int r = 0; r < this.gate.length; r++) {
            for (int c = 0; c < this.gate.length; c++) {
                boolean place = false;
                StringBuilder temp = new StringBuilder();
                String from = num(r, size);
                String from2 = from;
                String to = num(c, size);
                String to2 = to;
                for (int i : phase) {
                    temp.append(from, i, i + 1);
                }
                from = temp.toString();
                temp = new StringBuilder();
                for (int i : phase) {
                    temp.append(to, i, i + 1);
                }
                to = temp.toString();
                to = new StringBuilder(to).reverse().toString();
                from = new StringBuilder(from).reverse().toString();
                int c1 = Integer.parseInt(to, 2);
                int r1 = Integer.parseInt(from, 2);

                sortGtoL(phase);

                for (int i : phase) {
                    from2 = remove(from2, i);
                    to2 = remove(to2, i);
                }

                if (from2.equals(to2)) {
                    place = true;
                }
                if (place) {
                    this.gate[r][c] = gate.matrix[r1][c1];
                } else {
                    this.gate[r][c] = Complex.ZERO;
                }
            }
        }
    }

    public static String binaryRepresentation(Integer a, int len) {
        String temp = Integer.toBinaryString(a);
        int temp2 = len - temp.length();
        while (temp2 > 0) {
            temp = String.format("0%s", temp);
            temp2 = len - temp.length();
        }
        return temp;
    }

    public static boolean contains(int[] i, int j) {
        for (int k : i) {
            System.out.println(k + "=" + j + "?");
            if (k == j) {
                return true;
            }
        }
        return false;
    }

    public String num(int num, int len) {
        String temp = Integer.toBinaryString(num);
        while (temp.length() < len) {
            temp = String.format("0%s", temp);
        }
        return temp;
    }

    public static String remove(String s, int i) {
        if (s.length() == 1)
            return "";
        if (i == 0)
            return s.substring(1);
        if (i == s.length() - 1)
            return s.substring(0, s.length() - 1);
        return s.substring(0, i) + s.substring(i + 1);
    }

    public static void sortGtoL(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        for (int i : nums) {
            temp.add(i);
        }
        Collections.sort(temp);
        Collections.reverse(temp);
        for (int i = 0; i < temp.size(); i++) {
            nums[i] = temp.get(i);
        }
    }
}