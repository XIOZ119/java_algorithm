import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();
            if (str == null || str.isEmpty()) break;

            int n = Integer.parseInt(str);

            int[] coin = new int[n];
            int[] count = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                coin[i] = Integer.parseInt(st.nextToken());
                count[i] = Integer.parseInt(st.nextToken());
                sum += coin[i] * count[i];
            }

            if(sum % 2 == 1) {
                sb.append("0\n");
                continue;
            }

            boolean[] dp = new boolean[sum / 2 + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                for (int j = dp.length - 1; j >= 0; j--) {
                    if (dp[j]) {
                        for (int k = 1; k <= count[i] && j + k * coin[i] < dp.length; k++) {
                            dp[j + k * coin[i]] = true;
                        }
                    }

                }
            }
            sb.append(dp[sum / 2] ? "1\n" : "0\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}