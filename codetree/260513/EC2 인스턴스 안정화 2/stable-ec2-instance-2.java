import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] base;
    static int baseScore = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        base = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(i == 0) continue;
            base[i] = Math.abs(arr[i-1] - arr[i]);
            baseScore += base[i];
        }

        for (int a = 0; a < N; a++) {
            arr[a] *= 2;

            for (int b = a + 1; b < N; b++) {
                arr[b] *= 2;

                count(a, b);

                arr[b] /= 2;
            }

            arr[a] /= 2;
        }

        System.out.println(answer);
    }

    static void count(int a, int b) {
        int ans = baseScore;

        ans = update(ans, a);
        ans = update(ans, a + 1);

        if (b != a && b != a + 1) ans = update(ans, b);
        if (b + 1 != a && b + 1 != a + 1 && b + 1 != b) ans = update(ans, b + 1);

        answer = Math.max(answer, ans);
    }

    static int update(int ans, int idx) {
        if (idx <= 0 || idx >= N) return ans;

        ans -= base[idx];
        ans += Math.abs(arr[idx - 1] - arr[idx]);

        return ans;
    }
}
