package simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Cell;
import model.CellIndexObject;
import model.CellWrapper;
import model.Particle;

import com.sun.istack.internal.NotNull;

public class CIMSimulation {

	private CellIndexObject cellIndexObject;
	private Cell[][] cells;

	public CIMSimulation(@NotNull CellIndexObject cellIndexObject) {
		this.cellIndexObject = cellIndexObject;
	}
	
	public void simulate() {
		initializeParticlesContainer();
		distributeParticles();
		calculateDistances();
		
		printCells();
	}
	
	private void printCells() {
		int M = cellIndexObject.getM();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				System.out.println("cell: (" + i + ", " + j + "): " + cells[i][j]);
			}
		}
	}

	private void calculateDistances() {
		int M = cellIndexObject.getM();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				Cell cell = cells[i][j];
				List<Point> directions = generateDirections(i, j);
				List<CellWrapper> cellWrappers = calculateCellWrappers(directions);
				calculateDistances(cell, cellWrappers);
			}
		}
	}

	private void calculateDistances(Cell cell, List<CellWrapper> cellWrappers) {
		for (Particle particle: cell.getParticles()) {
			for (CellWrapper cellWrapper: cellWrappers) {
				Cell neighborCell = cellWrapper.getCell();
				for (Particle neighborParticle: neighborCell.getParticles()) {
					if (particle.equals(neighborParticle)) continue;
					if (!satisfiesDistance(particle, neighborParticle, cellWrapper)) continue;
					particle.getNeighbors().add(neighborParticle);
					neighborParticle.getNeighbors().add(particle);
				}
			}
		}
	}
	
	private boolean satisfiesDistance(Particle particleA, Particle particleB, CellWrapper particleBWrapper) {
		return distanceBetween(particleA, particleB, particleBWrapper) < cellIndexObject.getInteractionRadius();
	}
	
	private double distanceBetween(Particle particleA, Particle particleB, CellWrapper particleBWrapper) {
		double ax = particleA.getX();
		double ay = particleA.getY();
		double bx = particleB.getX() + particleBWrapper.getxOffset();
		double by = particleB.getY() + particleBWrapper.getyOffset();
		if (particleA.getId() == 22 || particleB.getId() == 22) {
			System.out.println(ax + ", " + ay + "  -  " + bx + ", " + by);
		}
		return distanceBetween(ax, ay, bx, by) - particleA.getRadius() - particleB.getRadius();
	}
	
	private double distanceBetween(double x1, double y1, double x2, double y2) {
		double a = x1 - x2;
		double b = y1 - y2;
		return Math.sqrt(a * a + b * b);
	}

	private List<CellWrapper> calculateCellWrappers(List<Point> directions) {
		List<CellWrapper> cellWrappers = new ArrayList<CellWrapper>();
		for (Point direction : directions) {
			if (isOutOfBounds(direction.x, direction.y)
					&& !cellIndexObject.hasPeriodicBoundaries())
				continue;
			cellWrappers.add(generateCellWrapper(direction.x, direction.y));
		}
		return cellWrappers;
	}

	private List<Point> generateDirections(int i, int j) {
		List<Point> directions = new ArrayList<Point>();
		directions.add(new Point(i, j));
		directions.add(new Point(i - 1, j + 1));
		directions.add(new Point(i, j + 1));
		directions.add(new Point(i + 1, j + 1));
		directions.add(new Point(i + 1, j));
		return directions;
	}

	private boolean isOutOfBounds(int i, int j) {
		return i >= 0 && j >= 0 && i < cellIndexObject.getM()
				&& j < cellIndexObject.getM();
	}

	private CellWrapper generateCellWrapper(int i, int j) {
		int M = cellIndexObject.getM();

		int xOffset = calculateOffset(j);
		int yOffset = calculateOffset(i);
		int realI = (i + M) % M;
		int realJ = (j + M) % M;
		Cell cell = cells[realI][realJ];
		return new CellWrapper(cell, xOffset, yOffset);
	}

	private int calculateOffset(int x) {
		if (x < 0)
			return -cellIndexObject.getSpaceDimension();
		if (x >= cellIndexObject.getM())
			return cellIndexObject.getSpaceDimension();
		return 0;
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
		cells = new Cell[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
}
