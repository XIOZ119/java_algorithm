package class1;

import java.util.Scanner;

public class _1152 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String str = sc.nextLine();
        int count = 0;
        
        if(str.trim().isEmpty()) {
        	count = 0;
        } else {
            // 공백 제거하고 문자열 나누어서 저장 
        	String[] words = str.trim().split("\\s+"); 
        	count = words.length;
        }
        System.out.println(count);
    }
}
