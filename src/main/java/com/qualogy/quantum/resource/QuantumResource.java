package com.qualogy.quantum.resource;

import com.qualogy.quantum.core.Circuit;
import com.qualogy.quantum.core.gate.Gate;
import com.qualogy.quantum.core.gate.Measure;
import com.qualogy.quantum.core.vo.QuantumGate;
import org.apache.commons.math3.complex.Complex;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/emulator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuantumResource {


    @POST
    public String calculateEmulation(List<QuantumGate> quantumGates){
//        Complex[][] start = {{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))},{Complex.I.divide(Math.sqrt(4))}};
//        List<Gate> gates = new ArrayList<>();
//        gates.add(new Measure(0));
//        gates.add(new Measure(1));
//        circuit.setStart(start);
//        circuit.setGates(gates);
//        circuit.calculateAllSteps();
//        circuit.measureAll();
        return null;
    }
}
