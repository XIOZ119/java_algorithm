import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i=1; i<=N; i++){
            int div = 1;
            
            if(i == 1_000_000) div = 1_000_000;
            else if(i >= 100_000) div = 100_000;
            else if(i >= 10_000) div = 10_000;
            else if(i >= 1_000) div = 1000;
            else if(i >= 100) div = 100;
            else if(i >= 10) div = 10;

            int l = i;
            while(l > 0) {
                int a = l / div;
                l %= div;

                if(a != 0 && a % 3 == 0) answer++;

                div /= 10;
            }
        }

        System.out.println(answer);
    } 
}
