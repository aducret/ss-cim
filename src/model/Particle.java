package model;

import java.util.ArrayList;
import java.util.List;

public class Particle {
	private int id;
	private float x;
	private float y;
	private float radio;
	private float color;
	private List<Particle> neighbors;
	
	public Particle(int id, float x, float y, float radio, float color) {
		if (radio < 0 || color < 0 || color > 1 || id <= 0) throw new IllegalArgumentException("particle wrong mthr fckr");
		this.id = id;
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.color = color;
		this.neighbors = new ArrayList<Particle>();
	}

	public int getId() {
		return id;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getRadio() {
		return radio;
	}
	
	public float getColor() {
		return color;
	}
	
	public List<Particle> getNeighbors() {
		return neighbors;
	}
}
