package model;

import java.util.HashSet;
import java.util.Set;

public class ParticlesContainer {
	private Set<Particle> particles;
	
	public ParticlesContainer() {
		this.particles = new HashSet<Particle>();
	}
	
	public Set<Particle> getParticles() {
		return particles;
	}
}
