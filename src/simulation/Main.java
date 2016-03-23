package simulation;

import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;

import model.Particle;
import model.SimulationData;
import parser.InformationParser;
import parser.OvitoFileInputGenerator;

public class Main {

	private static final int M = 1000;
	private static final boolean HAS_PERIODIC_BOUNDARIES = false;
	private static final double INTERACTION_RADIUS = 6.0;

	public static void main(String[] args) throws FileNotFoundException {
		String dynamicFilePath = "doc/examples/Dynamic25000.txt";
		String staticFilePath = "doc/examples/Static25000.txt";

		SimulationData.Builder builder = InformationParser.generateCellIndexObject(dynamicFilePath, staticFilePath);

		builder = builder.withInteractionRadius(INTERACTION_RADIUS);
		SimulationData simulationData = builder.build();

		Simulation simulation = new CellIndexMethodSimulation(M, HAS_PERIODIC_BOUNDARIES);
//		 Simulation simulation = new BruteForceSimulation();

		long startTime = System.currentTimeMillis();
		simulation.simulate(simulationData);
		long endTime = System.currentTimeMillis();

		OvitoFileInputGenerator fileGenerator = new OvitoFileInputGenerator(simulationData);
		ThreadLocalRandom
			.current()
			.ints(0, simulationData.getParticlesAmount()-1)
			.distinct()
			.limit(10)
			.forEach((index) -> {
				Particle randomParticle = simulationData.getParticles().get(index);
				fileGenerator.setSelectedParticle(randomParticle);
				String filePath = "doc/results/particle_" + index + ".txt";
				try {
					fileGenerator.generateFile(filePath);
				} catch (Exception exception) {
					System.out.println("There was a problem generating the next result: " + filePath);
				}
			});

		System.out.println("simulation time: " + (endTime - startTime));
	}
}
