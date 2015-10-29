package practice.mathematics;

public class MonteCarloPI {

    public static double findPIv2(int number) {
        double x;
        double y;
        int count = 0;
        
        double increament = 1 / (double) number;
        
        for (int i = 0; i < number; i++) {
            x = increament * i;
            for (int j = 0; j < number; j++) {
                y = increament * j;
                if (x * x + y * y < 1.0) {
                    count++;
                }
            }
        }

        return (double) count * 4 / (number * number);
    }

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
        System.out.println("V1 Pi is " + findPI(8000000));
        System.out.println("V2 Pi is " + findPIv2(10000));
    }
}
