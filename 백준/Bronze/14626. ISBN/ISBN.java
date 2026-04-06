import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int div = 1;
        int sum = 0;
        for(int i=0; i<13; i++){
            char ch = str.charAt(i);
            if(ch == '*') {
                if(i % 2 == 1) div = 3;
                continue;
            }
            if(i % 2 == 1) {
                int num = Integer.parseInt(ch + "");
                sum += num * 3;
            } else {
                sum += Integer.parseInt(ch + "");
            }
        }
        int answer = 0;

        if(sum % 10 == 0) answer = 0;
        else if(div == 3) {
            for(int i=1; i<10; i++) {
                if((sum + 3 * i) % 10 != 0) continue;

                answer = i;
            }
        }
        else if(div == 1) {
            sum = sum % 10;
            answer = 10 - sum;
        }

        System.out.println(answer + "");
    }
}