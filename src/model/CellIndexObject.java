package model;

import java.util.ArrayList;
import java.util.List;

public class CellIndexObject {
	
	private float interactionRadius;
	private int spaceDimension; // All particle's coordinates are contained inside spaceDimension x spaceDimension
	private int particlesAmount;
	private int M; // MxM is the total amount of cells
	private List<Particle> particles;
	
	private CellIndexObject() {
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
		
		public CellIndexObject build() {
			return cellIndexObject;
		}
	}
}
