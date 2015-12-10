package practice.mathematics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Armstrong {
    
    public static Map<Integer, Integer> getPowerMap(int power) {
        Map<Integer, Integer> powerMap = new HashMap<>();
        
        for (int i = 0; i <=9; i++) {
            int pow = (int) Math.pow(i, power);
            powerMap.put(i, pow);
        }
        
        return powerMap;
    }

    public static List<Integer> getArmstrong(int num) {
        List<Integer> armstrongList = new ArrayList<>();
        Map<Integer, Integer> powerMap = getPowerMap(num);
        
        int start = (int) (1 * Math.pow(10, num - 1));
        int end = (int) (1 * Math.pow(10, num) - 1);
        
        System.out.println("start:" + start + ", end:" +end);
        
        int n[] = new int[num];
        
        for (int i = start; i <= end; i++) {
            int nn = i;
            for (int j = 0; j < n.length; j++) {
                n[j] = nn % 10;
                nn /= 10;
            }
            
            int sum = 0;
            for (int j = 0; j < n.length; j++) {
                sum += powerMap.get(n[j]);
            }
            if (sum == i) {
                armstrongList.add(i);
            }
        }
        
        return armstrongList;
    }
    
    public static void main(String[] args) {
        List<Integer> armstrongLisg = getArmstrong(3);
        System.out.println(armstrongLisg.toString());
    }
}
