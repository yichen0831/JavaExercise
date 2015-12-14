package practice.sets;

public class Permutation {

    public static void permutate(String target, String computed) {
        if (target.length() <= 1) {
            System.out.println(computed + target);
            return;
        }
        
        for (int i = 0; i < target.length(); i++) {
            char[] op = target.toCharArray();
            char tmp = op[i];
            op[i] = op[0];
            op[0] = tmp;
            String result = computed + op[0];
            
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(op);
            
            permutate(stringBuilder.substring(1).toString(), result);
        }
    }
    
    public static void main(String[] args) {
        permutate("abcde", "");
    }
}
