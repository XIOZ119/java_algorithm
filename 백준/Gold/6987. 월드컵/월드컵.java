import java.io.*;
import java.util.*;

public class Main {
    static int[][] pairs = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
    static int[][] scores = new int[6][3];
    static boolean possible;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        /* =============== 초기 세팅 =============== */
        for(int testCase = 1; testCase <= 4; testCase++){
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 3; j++){
                    scores[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            possible = false;
            dfs(0);
            if(possible) sb.append("1 ");
            else sb.append("0 ");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int index) {
        if (possible) return; // 이미 찾았으면 중단

        if (index == 15) {
            // 모든 경기를 다 소화했는지 체크
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (scores[i][j] != 0) return;
                }
            }
            possible = true;
            return;
        }

        int a = pairs[index][0];
        int b = pairs[index][1];

        // a승 b패
        if (scores[a][0] > 0 && scores[b][2] > 0) {
            scores[a][0]--; scores[b][2]--;
            dfs(index + 1);
            scores[a][0]++; scores[b][2]++;
        }

        // 무승부
        if (scores[a][1] > 0 && scores[b][1] > 0) {
            scores[a][1]--; scores[b][1]--;
            dfs(index + 1);
            scores[a][1]++; scores[b][1]++;
        }

        // a패 b승
        if (scores[a][2] > 0 && scores[b][0] > 0) {
            scores[a][2]--; scores[b][0]--;
            dfs(index + 1);
            scores[a][2]++; scores[b][0]++;
        }
    }
}
