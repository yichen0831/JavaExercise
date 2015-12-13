package practice.gamble;

public class Craps {

	public static void game() {
		
		int result = (int)(Math.random() * 6 + Math.random() + 2);
		System.out.println("Roll No.1: " + result);

		if (result == 7 || result == 11) {
			System.out.println("Player win!");
			return;
		}
		else if (result == 2 || result == 3 || result == 12) {
			System.out.println("Computer win!");
			return;
		}
		
		int count = 2;
		int roll = 0;
		boolean keepRolling = true;
		while(keepRolling) {
			roll = (int)(Math.random() * 6 + Math.random() + 2);
			System.out.println("Roll No." + count++ + ": " + roll);
			
			if (roll == result) {
				System.out.println("Player win!");
				keepRolling = false;
			}
			else if(roll == 7) {
				System.out.println("Computer win!");
				keepRolling = false;
			}
		}
		
	}
	
	public static void main(String[] args) {
		game();
	}
}
