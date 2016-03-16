package model;

import java.util.ArrayList;
import java.util.List;

public class CellIndexObject {

	private Float interactionRadius;
	private Integer spaceDimension; // All particle's coordinates are contained
									// inside spaceDimension x spaceDimension
	private Integer particlesAmount;
	private Integer M; // MxM is the total amount of cells
	private List<Particle> particles;
	private boolean hasPeriodicBoundaries;

	private CellIndexObject() {
		this.hasPeriodicBoundaries = false;
	}

	public Float getInteractionRadius() {
		return interactionRadius;
	}

	public Integer getSpaceDimension() {
		return spaceDimension;
	}

	public Integer getParticlesAmount() {
		return particlesAmount;
	}

	public Integer getM() {
		return M;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public Float getCellDimension() {
		if (M == null || spaceDimension == null)
			return null;
		return spaceDimension / ((float) M);
	}

	public static class Builder {
		private CellIndexObject cellIndexObject;

		private Builder() {
			cellIndexObject = new CellIndexObject();
			cellIndexObject.particles = new ArrayList<>();
		}

		public static Builder create() {
			return new Builder();
		}

		public Builder withInteractionRadius(float interactionRadius) {
			cellIndexObject.interactionRadius = interactionRadius;
			return this;
		}

		public Builder withM(int M) {
			this.cellIndexObject.M = M;
			return this;
		}

		public Builder withParticlesAmount(int particlesAmount) {
			this.cellIndexObject.particlesAmount = particlesAmount;
			return this;
		}

		public Builder withSpaceDimension(int spaceDimension) {
			this.cellIndexObject.spaceDimension = spaceDimension;
			return this;
		}

		public Builder withParticle(Particle particle) {
			cellIndexObject.particles.add(particle);
			return this;
		}
		
		public Builder withPeriodicBoundaries(boolean hasPeriodicBoundaries) {
			cellIndexObject.hasPeriodicBoundaries = hasPeriodicBoundaries;
			return this;
		}

		public CellIndexObject build() {
			return cellIndexObject;
		}
	}
}
