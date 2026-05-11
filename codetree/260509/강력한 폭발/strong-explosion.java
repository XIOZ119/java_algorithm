import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] arr;
    static ArrayList<int[]> list = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) list.add(new int[] {i, j});
            }
        }

        perm(new ArrayList<Integer>());
        System.out.println(answer);
    }

    private static void perm(ArrayList<Integer> p) {
        boolean[][] visited = new boolean[n][n];
        if(p.size() == list.size()) {
            for(int i=0; i<p.size(); i++) {
                // 폭탄 위치
                int bx = list.get(i)[0];
                int by = list.get(i)[1];

                // 폭탄 모양
                int shape = p.get(i);

                visited[bx][by] = true;
                if(shape == 1) {
                    if(isValid(bx + 1, by)) visited[bx+1][by] = true;
                    if(isValid(bx + 2, by)) visited[bx+2][by] = true;
                    if(isValid(bx - 1, by)) visited[bx-1][by] = true;
                    if(isValid(bx - 2, by)) visited[bx-2][by] = true;
                } else if(shape == 2) {
                    if(isValid(bx + 1, by)) visited[bx+1][by] = true;
                    if(isValid(bx - 1, by)) visited[bx-1][by] = true;
                    if(isValid(bx, by - 1)) visited[bx][by-1] = true;
                    if(isValid(bx, by + 1)) visited[bx][by+1] = true;
                } else if(shape == 3) {
                    if(isValid(bx - 1, by - 1)) visited[bx-1][by-1] = true;
                    if(isValid(bx - 1, by + 1)) visited[bx-1][by+1] = true;
                    if(isValid(bx + 1, by - 1)) visited[bx+1][by-1] = true;
                    if(isValid(bx + 1, by + 1)) visited[bx+1][by+1] = true;
                }
            }

            int cnt = 0;
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(visited[i][j]) cnt++;
                }
            }

            answer = Math.max(answer, cnt);
            return;
        }

        for(int i=1; i<=3; i++) {
            p.add(i);
            perm(p);
            p.remove(p.size()-1);
        }
    }

    private static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < n && y < n;
    }
}