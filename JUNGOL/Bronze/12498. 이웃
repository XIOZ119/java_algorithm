import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K1 = Integer.parseInt(st.nextToken());
        int K2 = Integer.parseInt(st.nextToken());

        int[] school = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            school[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        for(int i=0; i<N-1; i++) {
            int a = school[i];
            for(int j=i+1; j<N; j++) {
                if(a != school[j] && (j - i) > K2) continue;
                if(a == school[j] && (j - i) > K1) continue;

                answer[i]++;
                answer[j]++;
            }
        }

        for(int i=0; i<N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
