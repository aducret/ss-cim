package input_generator;

public class PointGenerator {
	public static MyPoint randomPointBetween(int x, int y) {
		return new MyPoint(randomBetween(x, y), randomBetween(x, y));
	}
	
	public static float randomBetween(int x, int y) {
		return (float)(Math.random()*(y - x) + x);
	}
}
