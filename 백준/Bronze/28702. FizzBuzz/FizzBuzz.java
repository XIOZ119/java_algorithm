import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int number = 0;
        for(int i=0; i<3; i++) {
            number++;
            String str = br.readLine();
            boolean flag = true;
            for(int j=0; j<str.length(); j++) {
                if(!Character.isDigit(str.charAt(j))) {
                    flag = false;
                }
            }
            if(flag) number = Integer.parseInt(str);
        }
        
        int nextNumber = number+1;
        
        if(nextNumber % 3 == 0 && nextNumber % 5 == 0) {
            bw.write("FizzBuzz");
        } 
        else if(nextNumber % 3 == 0 && nextNumber % 5 != 0) {
        	bw.write("Fizz");
        } 
        else if(nextNumber % 3 != 0 && nextNumber % 5 == 0) {
        	bw.write("Buzz");
        }
        else {
        	bw.write(nextNumber + " ");
        }
        
        bw.flush();
        bw.close();
    }
}