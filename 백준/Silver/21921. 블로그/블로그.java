import java.io.*;
import java.util.*;

public class Main {
    static int N, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i = 0; i < X; i++) {
            sum += arr[i];
        }

        int answer = sum;
        int count = 1;

        for(int i = 1; i <= N - X; i++) {
            sum -= arr[i - 1];
            sum += arr[i + X - 1];

            if(sum > answer) {
                answer = sum;
                count = 1;
            }
            else if(sum == answer) {
                count++;
            }
        }

        if(answer == 0) {
            bw.write("SAD");
        } else{
            bw.write(answer + "\n" + count);
        }
        bw.flush();
        bw.close();
    }
}