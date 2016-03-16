package simulation;

import java.io.FileNotFoundException;

import model.CellIndexObject;
import model.Particle;
import parser.InformationParser;

public class Main {
	
	private static final int M = 10;
	private static final double INTERACTION_RADIUS = 6.0;
	
	public static void main(String[] args) throws FileNotFoundException {
		String dynamicFilePath = "doc/examples/Dynamic100.txt";
		String staticFilePath = "doc/examples/Static100.txt";
		
		CellIndexObject.Builder builder = InformationParser.generateCellIndexObject(dynamicFilePath, staticFilePath);
		
		builder = builder.withM(M)
				.withInteractionRadius(INTERACTION_RADIUS);
		CellIndexObject cellIndexObject = builder.build();
		new CIMSimulation(cellIndexObject).simulate();
		
		for (Particle particle: cellIndexObject.getParticles()) {
			System.out.print(particle.getId() + ": ");
			for (Particle neighbor: particle.getNeighbors()) {
				System.out.print(neighbor + ", ");
			}
			System.out.println();
		}
	}
}
