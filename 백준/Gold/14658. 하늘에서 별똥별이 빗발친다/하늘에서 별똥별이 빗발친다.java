import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L, K;
    static ArrayList<int[]> star;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        star = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            star.add(new int[]{x, y});
        }

        solve();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static void solve() {
        int maxCaught = 0;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x0 = star.get(i)[0];
                int y0 = star.get(j)[1];

                int x1 = x0 + L;
                int y1 = y0 + L;

                int caught = 0;

                for (int t = 0; t < K; t++) {
                    int sx = star.get(t)[0];
                    int sy = star.get(t)[1];
                    if (x0 <= sx && sx <= x1 && y0 <= sy && sy <= y1) caught++;
                }

                if (caught > maxCaught) maxCaught = caught;
            }
        }

        answer = K - maxCaught;
    }
}