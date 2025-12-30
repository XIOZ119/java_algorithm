import java.io.*;
import java.util.*;

public class Main {
    static int N, a, b;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        arr = new int[N];

        if(a + b - 1 > N) bw.write("-1");
        else {
            greedy();

            for(int i = 0; i < N; i++) {
                sb.append(arr[i] + " ");
            }

            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
    }

    private static void greedy() {
        int peek = Math.max(a, b);

        Arrays.fill(arr, 1);

        // peek 위치 : a와 b가 1이 아닐 때, 오른쪽으로 밀릴 수록 빠른 수 -> b 기준으로 배치
        int loc;

        if (a == 1) loc = 0;
        else if (b == 1) loc = N - 1;
        else loc = N - b;

        arr[loc] = peek;

        // peek 왼쪽부터 a-1 만큼 채움 !!
        int start = loc - (a - 1);
        for (int i = 0; i < a - 1; i++) {
            arr[start + i] = i + 1;
        }

        // 배열 맨 끝에 붙이기 (b-1..1)
        for (int i = 0; i < b - 1; i++) arr[N - 1 - i] = i + 1;
    }
}