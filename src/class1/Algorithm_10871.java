package class1;

import java.util.Scanner;

public class Algorithm_10871 {

	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int X = sc.nextInt();
        String result = ""; 
        
        for(int i=0; i<N; i++){
            int a = sc.nextInt();
            if (a < X) {
                result = result + (a + " ");
            }
        }
        System.out.println(result);
	}
}
