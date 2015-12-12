package practice.gamble;

import java.util.Random;

public class Poker {

    private int[] pokers;
    
    public Poker() {
        pokers = new int[52];
        for (int i = 0; i < pokers.length; i++) {
            pokers[i] = i;
        }
    }
    
    public void shuffle() {
        Random random = new Random();
        int pos = 0;
        int tmp = 0;
        for(int i = 0; i < pokers.length; i++) {
            pos = random.nextInt(pokers.length);
            tmp = pokers[i];
            pokers[i] = pokers[pos];
            pokers[pos] = tmp;
        }
    }
    
    public void printCards() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < pokers.length; i++) {
            String kind = "";
            String value = "";
            
            switch(pokers[i] / 13) {
                case 0:
                    kind = "♠";
                    break;
                case 1:
                    kind = "♥";
                    break;
                case 2:
                    kind = "♣";
                    break;
                case 3:
                    kind = "♦";
                    break;
                default:
                    break;
            }
            
            int number = (pokers[i] % 13) + 1; 
            if (number < 11) {
                value = String.valueOf(number);
            }
            else {
                switch(number) {
                    case 11:
                        value = "J";
                        break;
                    case 12:
                        value = "Q";
                        break;
                    case 13:
                        value = "K";
                        break;
                    default:
                        break;
                }
            }
            
            stringBuilder.append(kind + value).append(" ");
            
            if ((i + 1) % 13 == 0) {
                stringBuilder.append("\n");
            }
        }
        
        System.out.println(stringBuilder.toString());
    }
    
    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle();
        poker.printCards();
        
    }
}
