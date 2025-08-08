import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static int[][] d; // 짧은 거리 기록
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int count = 0;

        while(true){
            count++;
            N = Integer.parseInt(br.readLine());

            if(N == 0) break;
            arr = new int[N][N];
            d = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
            
            int result = escape();

            sb.append("Problem " + count + ": " + result + "\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int escape() {
        Queue<int []> queue = new LinkedList<>();
        
        queue.add(new int[] {0, 0, arr[0][0]});
        d[0][0] = arr[0][0];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];

            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isValid(nx, ny)) continue;
                
                int nd = cd + arr[nx][ny];
                if(d[nx][ny] > nd) {
                    queue.add(new int[] {nx, ny, nd});
                    d[nx][ny] = nd;
                }
            }
        }

        return d[N-1][N-1];
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}  