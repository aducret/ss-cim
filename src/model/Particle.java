package model;

import java.util.ArrayList;
import java.util.List;

public class Particle {
	private int id;
	private double x;
	private double y;
	private double radius;
	private double color;
	private List<Particle> neighbors;
	
	public Particle(int id, double x, double y, double radius, double color) {
		if (radius < 0 || color < 0 || color > 1 || id <= 0) throw new IllegalArgumentException("particle wrong mthr fckr");
		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.neighbors = new ArrayList<Particle>();
	}

	public int getId() {
		return id;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getColor() {
		return color;
	}
	
	public List<Particle> getNeighbors() {
		return neighbors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("(" + x + ", " + y + ")")
				.toString();
	}
}
