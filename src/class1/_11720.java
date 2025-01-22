package class1;

import java.util.Scanner;

public class _11720 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        int sum = 0;
        
        for(int i=0; i < 1; i++){
            String a = sc.next();
            for(int j=0; j <testCase; j++) {
            	int b = a.charAt(j) - '0';
            	sum += b;
            }
        }
        System.out.println(sum);
        sc.close();
    }

}
