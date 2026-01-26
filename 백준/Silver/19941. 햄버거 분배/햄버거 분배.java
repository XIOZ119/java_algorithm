import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        char[] table = new char[N];
        boolean[] eat = new boolean[N];

        int answer = 0;
        for(int i=0; i<N; i++) {
            table[i] = str.charAt(i);
        }

        for(int i=0; i<N; i++) {
            if(table[i] == 'H') continue;

            for(int j=i-K; j<=i+K; j++) {
                if(j < 0 || j >= N || eat[j] || table[j] == 'P') continue;

                eat[j] = true;
                answer++;
                break;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

}