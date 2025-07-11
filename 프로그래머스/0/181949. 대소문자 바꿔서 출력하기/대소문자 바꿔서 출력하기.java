import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        
        for(int i=0; i<a.length(); i++) {
            char ac = a.charAt(i);
            
            if(Character.isUpperCase(ac)){
                ac = Character.toLowerCase(ac);
            } else {
                ac = Character.toUpperCase(ac);
            }
            
            System.out.print(ac);
        }
    }
}