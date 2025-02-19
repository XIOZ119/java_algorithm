import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputArr = br.readLine().split(" ");

        int N = Integer.parseInt(inputArr[0]);
        int K = Integer.parseInt(inputArr[1]);

        int mul = 1;
        int div = 1; 
        
        for(int i=N; i>(N-K); i--) {
            mul *= i;
        }

        for(int i=K; i>=1; i--) {
            div *= i;
        }

        int result = mul / div;
        
        bw.write(result + " ");
        bw.flush();
        bw.close();
    }
}