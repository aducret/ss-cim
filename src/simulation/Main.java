package simulation;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import model.Particle;
import model.SimulationData;
import parser.InformationParser;
import parser.OutputFileGenerator;
import parser.OvitoFileInputGenerator;

public class Main {
	private static final String CELL_INDEX_METHOD = "cellIndexMethod";
	private static final String BRUTE_FORCE = "bruteForce";
	private static final int M = 13;
	private static final boolean HAS_PERIODIC_BOUNDARIES = true;
	private static final double INTERACTION_RADIUS = 6.0;
	private static final String SIMULATION_METHOD = BRUTE_FORCE;
	private static final int SELECTED_PARTICLE_ID = 1;

	public static void main(String[] args) throws FileNotFoundException {
		String dynamicFilePath = "doc/examples/Dynamic500.txt";
		String staticFilePath = "doc/examples/Static500.txt";
		Simulation simulation = null;

		SimulationData.Builder builder = InformationParser
				.generateCellIndexObject(dynamicFilePath, staticFilePath);

		builder = builder.withInteractionRadius(INTERACTION_RADIUS);
		SimulationData simulationData = builder.build();

		if (!isAValidMValue(simulationData)) {
			System.out.println("Not a valid M value.");
			return;
		}

		switch (SIMULATION_METHOD) {
		case BRUTE_FORCE:
			simulation = new BruteForceSimulation();
			break;
		case CELL_INDEX_METHOD:
			simulation = new CellIndexMethodSimulation(M,
					HAS_PERIODIC_BOUNDARIES);
			break;
		default:
			System.out.println("No such type of simulation.");
			return;
		}

		long simulationStartTime = System.currentTimeMillis();
		simulation.simulate(simulationData);
		long simulationEndTime = System.currentTimeMillis();

		try {
			new OvitoFileInputGenerator(simulationData, SELECTED_PARTICLE_ID)
					.generateFile("doc/examples/results/particle_"
							+ SELECTED_PARTICLE_ID);
			new OutputFileGenerator(simulationData)
					.generateOutputFile("doc/examples/results/result.txt");
		} catch (UnsupportedEncodingException e) {
			System.out
					.println("There was a problem generating one of the result files");
		}

		System.out.println("Simulation time: "
				+ (simulationEndTime - simulationStartTime));
	}

	private static Boolean isAValidMValue(SimulationData simulationData) {
		return simulationData.getSpaceDimension() / M > simulationData
				.getInteractionRadius() + 2 * getMaximumRadius(simulationData);
	}

	private static Double getMaximumRadius(SimulationData simulationData) {
		Double max = 0.0;
		for (Particle particle : simulationData.getParticles()) {
			if (max < particle.getRadius()) {
				max = particle.getRadius();
			}
		}
		return max;
	}
}
