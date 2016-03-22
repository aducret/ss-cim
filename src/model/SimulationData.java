package model;

import java.util.ArrayList;
import java.util.List;

public class SimulationData {

	private Double interactionRadius;
	private Integer spaceDimension; // All particle's coordinates are contained
									// inside spaceDimension x spaceDimension
	private Integer particlesAmount;
	private List<Particle> particles;

	private SimulationData() {
	}

	public Double getInteractionRadius() {
		return interactionRadius;
	}

	public Integer getSpaceDimension() {
		return spaceDimension;
	}

	public Integer getParticlesAmount() {
		return particlesAmount;
	}
	
	public List<Particle> getParticles() {
		return particles;
	}

	public static class Builder {
		private SimulationData cellIndexObject;

		private Builder() {
			cellIndexObject = new SimulationData();
			cellIndexObject.particles = new ArrayList<>();
		}

		public static Builder create() {
			return new Builder();
		}

		public Builder withInteractionRadius(double interactionRadius) {
			cellIndexObject.interactionRadius = interactionRadius;
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

		public SimulationData build() {
			return cellIndexObject;
		}
	}
}
