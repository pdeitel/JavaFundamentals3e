public class MathOperations {
    // Method to multiply two double values
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Method to multiply two int values
    public static int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        // Testing the multiply methods
        double result1 = multiply(7, 2.2);
        int result2 = multiply(3, 5);

        System.out.printf("Result of multiplying doubles: %.2f%n", result1);
        System.out.printf("Result of multiplying ints: %d%n", result2);
    }
}
