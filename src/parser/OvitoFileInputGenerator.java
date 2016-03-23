package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import model.Particle;
import model.SimulationData;

public class OvitoFileInputGenerator {

	private Particle selectedParticle;
	private SimulationData simulationData;

	private static final String Red = "255 0 0";
	private static final String Green = "0 255 0";
	private static final String Blue = "0 0 255";

	public OvitoFileInputGenerator(SimulationData simulationData) {
		this.simulationData = simulationData;
	}
	
	public OvitoFileInputGenerator(SimulationData simulationData, Particle selectedParticle) {
		this.selectedParticle = selectedParticle;
		this.simulationData = simulationData;
	}

	public void generateFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		writer.println(simulationData.getParticlesAmount());
		writer.println("ID X Y R G B r"); // The seconds line is ignored.
		for (Particle particle : simulationData.getParticles()) {
			if (particle.equals(selectedParticle)) {
				writer.println(generateLine(particle, Red));
			} else if (selectedParticle.getNeighbors().contains(particle)) {
				writer.println(generateLine(particle, Green));
			} else {
				writer.println(generateLine(particle, Blue));
			}
		}
		writer.close();
	}

	public void setSelectedParticle(Particle particle) {
		this.selectedParticle = particle;
	}

	private String generateLine(Particle particle, String RGB) {
		return particle.getId() + " " + particle.getX() + " " + particle.getY() + " " + RGB + " "
				+ particle.getRadius();
	}

}
