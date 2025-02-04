import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb;
        
        String[] arr = br.readLine().split(" ");

        sb = new StringBuffer(arr[0]);
        String first = sb.reverse().toString();
        sb = new StringBuffer(arr[1]);
        String second = sb.reverse().toString();
       
        int sum = Integer.parseInt(first) + Integer.parseInt(second);
        String sumStr = sum + "";

        sb = new StringBuffer(sumStr);
        String reverseSum = sb.reverse().toString();

        System.out.println(Integer.parseInt(reverseSum));
        
        
    }
}