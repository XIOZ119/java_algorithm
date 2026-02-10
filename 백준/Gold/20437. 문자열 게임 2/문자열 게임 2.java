import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append("1 1\n");
                continue;
            }

            List<Integer>[] idx = new ArrayList[26];
            for (int i = 0; i < 26; i++) idx[i] = new ArrayList<>();

            for (int i = 0; i < str.length(); i++) {
                idx[str.charAt(i) - 'a'].add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int ch = 0; ch < 26; ch++) {
                List<Integer> list = idx[ch];
                if (list.size() < K) continue;

                int left = 0;
                int right = K - 1;

                while (right < list.size()) {
                    int len = list.get(right) - list.get(left) + 1;
                    min = Math.min(min, len);
                    max = Math.max(max, len);

                    left++;
                    right++;
                }
            }

            if (min == Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(min).append(' ').append(max).append('\n');
        }

        System.out.print(sb);
    }
}