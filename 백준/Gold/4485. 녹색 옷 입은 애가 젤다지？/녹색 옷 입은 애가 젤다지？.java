import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static int[][] visited;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int start = 0;
        
        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n==0) break;

            arr = new int[n][n];
            visited = new int[n][n];

            for(int i=0; i<n; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int d = dijkstra();
            sb.append("Problem " + ++start + ": " + d + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dijkstra(){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[] {0, 0, arr[0][0]});
        visited[0][0] = arr[0][0];

        while(!pq.isEmpty()){
            int cur[] = pq.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];

            if(visited[cx][cy] < cd) continue;
            
            if(cx == n-1 && cy == n-1) {
                return cd;
            }

            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isValid(nx, ny)) continue;
                if(cd + arr[nx][ny] < visited[nx][ny]) {
                    visited[nx][ny] = cd + arr[nx][ny];
                    pq.add(new int[] {nx, ny, cd + arr[nx][ny]});
                }
            }
        }
        
        return -1;
    }

    static boolean isValid(int x, int y){
        return x > -1 && y > -1 && x < n && y < n;
    }
}