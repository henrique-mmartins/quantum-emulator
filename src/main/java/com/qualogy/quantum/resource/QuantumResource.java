package com.qualogy.quantum.resource;

import com.qualogy.quantum.core.Circuit;
import com.qualogy.quantum.core.gate.*;
import org.apache.commons.math3.complex.Complex;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/emulator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuantumResource {


    @POST
    public String calculateEmulation(List<Type> quantumGates){
        Circuit circuit = new Circuit(1);
        Complex[][] start = {{Complex.ZERO}, {Complex.ONE}};

        for (Type gate: quantumGates) {
            switch (gate){
                case C:
                    circuit.addGate(new ControlledNOT());
                    break;
                case Fourier:
                    circuit.addGate(new Fourier(1));
                    break;
                case H:
                    circuit.addGate(new Hadamard());
                    break;
                case M:
                    circuit.addGate(new Measure());
                    break;
                case X:
                    circuit.addGate(new NotGate());
                    break;
                case Y:
                    circuit.addGate(new PauliY());
                    break;
                case Z:
                    circuit.addGate(new PauliZ());
                    break;
                case SWAP:
                    circuit.addGate(new Swap());
                    break;
            }
        }

        circuit.setStart(start);
        return circuit.calculateAllSteps();
    }
}
