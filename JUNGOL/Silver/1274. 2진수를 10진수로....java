import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int num = Integer.parseInt(s, 2); 

        if(s.charAt(0) == '1') {
            num -= 256;
        }

        System.out.println(num);
    }
}
