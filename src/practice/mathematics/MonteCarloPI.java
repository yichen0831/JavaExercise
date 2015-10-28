package practice.mathematics;

public class MonteCarloPI {

    public static double findPI(int number) {

        double x;
        double y;
        int count = 0;

        for (int i = 0; i < number; i++) {
            x = Math.random();
            y = Math.random();

            if (x * x + y * y < 1.0) {
                count++;
            }
        }

        return (double) count * 4 / number;
    }

    public static void main(String[] args) {
        System.out.println("Pi is " + findPI(5000001));
    }
}
