package simulation;

import java.util.List;

import model.CellIndexObject;
import model.Particle;
import model.ParticlesContainer;

import com.sun.istack.internal.NotNull;

public class CIMSimulation {

	private CellIndexObject cellIndexObject;
	private ParticlesContainer[][] cells;

	public CIMSimulation(@NotNull CellIndexObject cellIndexObject) {
		this.cellIndexObject = cellIndexObject;
		initializeParticlesContainer();
		distributeParticles();
		calculateDistances();
	}
	
	private void calculateDistances() {
		int M = cellIndexObject.getM();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
//				List<ParticlesContainer> containers;
			}
		}
	}

	private void distributeParticles() {
		for (Particle particle : cellIndexObject.getParticles()) {
			int row = (int) Math.floor(particle.getY()
					/ cellIndexObject.getCellDimension());
			int column = (int) Math.floor(particle.getX()
					/ cellIndexObject.getCellDimension());
			cells[row][column].getParticles().add(particle);
		}
	}

	private void initializeParticlesContainer() {
		Integer M = cellIndexObject.getM();
		if (M == null)
			throw new IllegalArgumentException("fuck everything");
		cells = new ParticlesContainer[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cells[i][j] = new ParticlesContainer();
			}
		}
	}
}
