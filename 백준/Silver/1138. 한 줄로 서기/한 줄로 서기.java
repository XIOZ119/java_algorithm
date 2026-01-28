import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] result = new int[N];
        Arrays.fill(result, -1);

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            int current  = arr[i];
            int num = 0;
            for(int j=0; j<N; j++) {
                if(result[j] == -1) {
                    num++;
                    if(num > current) {
                        result[j] = i;
                        break;
                    }
                }
            }
        }

        for(int i=0; i<N; i++) {
            sb.append(result[i] + 1).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}