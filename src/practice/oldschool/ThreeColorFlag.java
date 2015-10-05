package practice.oldschool;

/**
 * Created by yichen on 10/4/15.
 */
public class ThreeColorFlag {

    static char[] flags = {'b', 'w', 'b', 'b', 'r', 'w', 'w', 'b', 'r', 'r'};


    static void moveFlags(char[] flags) {
        int b = 0;
        int w = 0;
        int r = flags.length - 1;

        while (flags[b] == 'b' && b < flags.length-1) {
            b++;
            w++;
        }

        while (flags[r] == 'r' && r > 0) {
            r--;
        }

        while (w <= r) {
            if (flags[w] == 'b') {
                flags[w] = flags[b];
                flags[b] = 'b';
                w++;
                b++;
            }
            else if (flags[w] == 'r') {
                flags[w] = flags[r];
                flags[r] = 'r';
                r--;
            }
            else {
                w++;
            }
        }
    }

    public static void main(String[] args) {
        moveFlags(flags);

        for (int i = 0; i < flags.length; i++) {
            char flag = flags[i];
            System.out.print(flag);
        }
    }

}
