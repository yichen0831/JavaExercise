package practice.oldschool;

/**
 * Created by yichen on 10/4/15.
 */
public class TowersOfHanoi {

    static void move(int n, char a, char b, char c) {

        if (n == 1) {
            System.out.println("Move from " + a + " to " + c );
        }
        else {
            move(n-1, a, c, b);
            move(1, a, b, c);
            move(n-1, b, a, c);
        }
    }

    public static void main(String[] args) {
        move(3, 'a', 'b', 'c');

    }
}
