package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import model.Particle;
import model.SimulationData;

public class InformationParser {
	public static SimulationData.Builder generateCellIndexObject(String dynamicFilePath, String staticFilePath) throws FileNotFoundException {
		SimulationData.Builder builder = SimulationData.Builder.create();
		
		InputStream dynamicIS = new FileInputStream(dynamicFilePath);
		Scanner dynamicScanner = new Scanner(dynamicIS).useLocale(Locale.US);
		InputStream staticIS = new FileInputStream(staticFilePath);
		Scanner staticScanner = new Scanner(staticIS).useLocale(Locale.US);
		
		int particlesAmount = staticScanner.nextInt();
		int spaceDimension = staticScanner.nextInt();
		
		builder = builder.withParticlesAmount(particlesAmount)
				.withSpaceDimension(spaceDimension);
		
		int instant = dynamicScanner.nextInt();
		
		for (int i = 1; i <= particlesAmount; i++) {
			double radius = staticScanner.nextDouble();
			double color = staticScanner.nextDouble();
			double x = dynamicScanner.nextDouble();
			double y = dynamicScanner.nextDouble();
			Particle particle = new Particle(i, x, y, radius, color);
			builder = builder.withParticle(particle);	
		}
		
		return builder;
	}
}