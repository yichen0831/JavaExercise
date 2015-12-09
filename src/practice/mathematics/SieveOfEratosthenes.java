package practice.mathematics;

import java.util.ArrayList;
import java.util.List;


public class SieveOfEratosthenes {

    private boolean[] sieve;

    public SieveOfEratosthenes(int maxNumber) {
        sieve = new boolean[maxNumber + 1];
        createSieve();
    }
    
    public List<Integer> getPrimeList() {
    	List<Integer> primeList = new ArrayList<>();
    	for (int i = 0; i < sieve.length; i++) {
    		if (sieve[i]) {
				primeList.add(i);
			}
    	}
    	return primeList;
    }

    private void createSieve() {
    	if(sieve.length > 5 ) {
    		sieve[2] = sieve[3] = sieve[5] = true;
    	}
    	else if (sieve.length > 3) {
    		sieve[2] = sieve[3] = true;
    	}
    	else if (sieve.length > 2) {
    		sieve[2] = true;
    	}

        int i;
        for (i = 1; i * 6 + 5 < sieve.length; i++) {
            sieve[i * 6 + 1] = true;
            sieve[i * 6 + 5] = true;
        }
        if (i * 6 + 1 < sieve.length) {
            sieve[i * 6 + 1] = true;
        }
        
        for (i = 0; (i * 6 + 5) * (i * 6 + 5)  < sieve.length; i++) {
            filter(i * 6 + 1);
            filter(i * 6 + 5);
        }
        
        if ((i * 6 + 1) * (i * 6 + 1) < sieve.length) {
            filter(i * 6 + 1);
        }

    }

    private void filter(int i) {
        if (sieve[i]) {
            for (int j = 2; j * i < sieve.length; j++) {
                sieve[i * j] = false;
            }
        }
    }

    public void printResult() {
        int count = 0;
        System.out.println("Prime numbers: ");
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                System.out.print(String.format("%d ", i));
                count++;
                if (count % 10 == 0) {
                    System.out.println("");
                }
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        SieveOfEratosthenes soe = new SieveOfEratosthenes(5000);
        soe.printResult();
    }

}
