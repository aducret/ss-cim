package input_generator;

import java.util.Locale;

public class MyPoint {
	public float x;
	public float y;
	
	public MyPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return String.format(Locale.US, "%1.7e %1.7e", x, y);
	}
}
