package class1;

import java.util.Scanner;

public class _2577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        int mul = a * b * c;
        String mulString = String.valueOf(mul);
        
        int[] counts = new int[10];
        
        for (char digit: mulString.toCharArray()) {
        	counts[digit - '0']++;  // digit - '0' : 문자->숫자 counts[숫자] 에 숫자가 저장됨
        }
       
        for (int i=0; i <counts.length; i++) {
        	System.out.println(counts[i]);

        }
    }
}
