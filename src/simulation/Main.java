package simulation;

import java.io.FileNotFoundException;

import model.Particle;
import model.SimulationData;
import parser.InformationParser;

public class Main {
	
	private static final int M = 10;
	private static final boolean HAS_PERIODIC_BOUNDARIES = false;
	private static final double INTERACTION_RADIUS = 6.0;
	
	public static void main(String[] args) throws FileNotFoundException {
		String dynamicFilePath = "doc/examples/Dynamic100.txt";
		String staticFilePath = "doc/examples/Static100.txt";
		
		SimulationData.Builder builder = InformationParser.generateCellIndexObject(dynamicFilePath, staticFilePath);
		
		builder = builder.withInteractionRadius(INTERACTION_RADIUS);
		SimulationData simulationData = builder.build();
		
		
//		Simulation simulation = new CellIndexMethodSimulation(M, HAS_PERIODIC_BOUNDARIES);
		Simulation simulation = new BruteForceSimulation();
		
		simulation.simulate(simulationData);
		
		for (Particle particle: simulationData.getParticles()) {
			System.out.print(particle.getId() + ": ");
			for (Particle neighbor: particle.getNeighbors()) {
				System.out.print(neighbor.getId() + ", ");
			}
			System.out.println();
		}
	}
}
