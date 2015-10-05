package practice.oldschool;

/**
 * Created by yichen on 10/4/15.
 */
public class Fibonacci {


    static int recursiveFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        else {
            return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
        }
    }

    static int nonRecursiveFibonacci(int n) {
        if(n == 0 || n == 1) {
            return n;
        }

        int f0 = 0;
        int f1 = 1;

        int fn = 0;

        for (int i = 2; i <= n; i++) {
            fn = f0 + f1;
            f0 = f1;
            f1 = fn;
        }

        return fn;

    }


    public static void main(String[] args) {
//        int ans = recursiveFibonacci(8);

        int ans = nonRecursiveFibonacci(8);
        System.out.println("ans = " + ans);


    }
}
