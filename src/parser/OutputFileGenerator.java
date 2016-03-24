package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import model.Particle;
import model.SimulationData;

public class OutputFileGenerator {
	private static final String ENCODING = "UTF-8";
	
	private SimulationData simulatonData;
	
	public OutputFileGenerator(SimulationData simulationData) {
		this.simulatonData = simulationData;
	}
	
	//	The expected output can be found in file doc/examples/FormatoOutput.txt
	public void generateOutputFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filePath, ENCODING);
		for (Particle particle: simulatonData.getParticles()) {
			writer.print(particle.getId() + "\t");
			for (Particle neighbor: particle.getNeighbors()) {
				writer.print(neighbor.getId() + " ");
			}
			writer.println();
		}
		writer.close();
	}
}