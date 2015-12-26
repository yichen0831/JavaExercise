package practice.sorting;

public class RankSorting {

    int[] scores;
    int[] ranks;
    
    public RankSorting(int[] scores) {
        this.scores = scores;
        ranks = new int[scores.length];
        
        for (int i = 0; i < scores.length; i++) {
            ranks[i] = 1;
            
            for (int j = 0; j < scores.length; j++) {
                if (scores[i] < scores[j]) {
                    ranks[i]++;
                }
            }
        }
    }
    
    public void printResult() {
        for (int i = 0; i < scores.length; i++) {
            System.out.println("Score: " + scores[i] + " ranks " + ranks[i]);
        }
    }
    
    public static void main(String[] args) {
        int[] scores = new int[]{ 100, 99, 99, 97, 83, 88, 99, 74, 78, 89};
        RankSorting rankSorting = new RankSorting(scores);
        rankSorting.printResult();
    }
}
