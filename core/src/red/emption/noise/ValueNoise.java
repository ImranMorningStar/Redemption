package red.emption.noise;


import java.util.Random;

public class ValueNoise {
    private int seed;
    private Random random;

    public ValueNoise(int seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    // Smoothstep function for interpolation
    private double smoothstep(double t) {
        return t * t * (3 - 2 * t);
    }

    // Linear interpolation
    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    // Generate random value at given coordinates
    private double randomValue(int x, int y) {
        random.setSeed(x * 49632 + y * 325176 + seed);
        return random.nextDouble();
    }

    // Interpolate between surrounding random values
    private double interpolate(double x, double y, double tx, double ty) {
        int x0 = (int) x;
        int y0 = (int) y;
        double sx = smoothstep(tx);
        double sy = smoothstep(ty);

        double v00 = randomValue(x0, y0);
        double v01 = randomValue(x0, y0 + 1);
        double v10 = randomValue(x0 + 1, y0);
        double v11 = randomValue(x0 + 1, y0 + 1);

        double ix0 = lerp(v00, v10, sx);
        double ix1 = lerp(v01, v11, sx);

        return lerp(ix0, ix1, sy);
    }

    // Generate value noise at given coordinates
    public double generateNoise(double x, double y) {
        int intX = (int) x;
        int intY = (int) y;
        double fracX = x - intX;
        double fracY = y - intY;

        return interpolate(intX, intY, fracX, fracY);
    }

//    public static void main(String[] args) {
//        ValueNoise valueNoise = new ValueNoise(123); // Seed = 123
//
//        // Example usage:
//        double noiseValue = valueNoise.generateNoise(1.5, 2.3);
//        System.out.println("Value noise at (1.5, 2.3): " + noiseValue);
//    }
}