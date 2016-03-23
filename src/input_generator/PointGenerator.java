package input_generator;

import java.awt.Point;
import java.awt.geom.Point2D;

public class PointGenerator {
	
	public static MyPoint randomPointBetween(int x, int y) {
		return new MyPoint(randomBetween(x, y), randomBetween(x, y));
		
	}
	
	public static float randomBetween(int x, int y) {
		return (float)(Math.random()*(y - x) + x);
	}
}
