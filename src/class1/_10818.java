package class1;

import java.util.Scanner;

public class _10818 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        
        int testCase = sc.nextInt();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE; 
        
        for(int i=0; i<testCase; i++){
            int a = sc.nextInt();
            if(min > a) {
                min = a;
            }
            if(max < a) {
                max = a;
            }
        }
        System.out.println(min + " " + max);
    }

}
