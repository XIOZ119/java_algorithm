package class1;

import java.util.Scanner;

public class _2920 {
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int firstInt = sc.nextInt();
        boolean result = true;
        
        if(firstInt == 1) {
        	 for (int i=2; i < 9; i++) {
        		 int in = sc.nextInt();
        		 if(in == i) {
        			 continue;
        		 } else {
        			 System.out.println("mixed");
        			 result = false;
        			 break;
        		 }
             }
         	if(result != false) System.out.println("ascending");
        } else if (firstInt == 8) { 
        	for (int i = 7; i > 0; i--) {
        		int in = sc.nextInt();
        		if (in == i) {
        			continue;
        		} else {
        			System.out.println("mixed");
        			result = false;
        			break;
        		}
        	}
        	if(result != false) System.out.println("descending");
        } else {
        	System.out.println("mixed");
        }
    }
}
