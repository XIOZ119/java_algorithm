import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int babySize;
    static int eatCount;
    static int lastX, lastY;
    static int eatResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        
        int babyX = -1;
        int babyY = -1;
        babySize = 2;
        eatResult = 0;
        
        boolean flag = false;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                
                if(arr[i][j] == 9) {
                    babyX = i;
                    babyY = j;
                }
                
                if(arr[i][j] > 0 && arr[i][j] < 7) flag = true;
            }
        }
        
        lastX = babyX; 
        lastY = babyY;
        
        if(!flag) {
            bw.write("0");
        } else {
            eatCount = 0;
            
            int d = bfs(lastX, lastY);

            bw.write(d + "");
        }
        
        bw.flush();
        bw.close();
    }
    
    static int bfs(int x, int y) {
        boolean[][] visited = new boolean[N][N];
        // 크기가 같은 경우 -> x 좌표 작은 순서 -> y 좌표 작은 순서
        PriorityQueue<int []> queue = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                
                if(o1[2] == o2[2]) {
                    if(o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                }
                return o1[2] - o2[2];
            }
            
        });
        
        queue.add(new int[] {x, y, 0});
        visited[x][y] = true;
        arr[x][y] = 0;
        
        int distance = 0;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            
            if(arr[cx][cy] != 0 && arr[cx][cy] < babySize) {
            	if(arr[cx][cy] < babySize) {
            		eatResult = cd;
            		eatCount++;
            		arr[cx][cy] = 0;
            	}
                
                if(eatCount == babySize) {
                	eatCount = 0;
                	babySize++;
                }
                
                queue.clear();
                visited = new boolean[N][N];
                visited[cx][cy] = true;
            }
            
            if(!leftFish()) return eatResult;
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nd = cd + 1;
                
                if(isValid(nx, ny) && !visited[nx][ny]) {
                    if(arr[nx][ny] <= babySize) {
                        queue.add(new int[] {nx, ny, nd});
                        visited[nx][ny] = true;
                    }
                }
            }
            distance = eatResult;
        }
        return distance;
    }
    
    static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < N && y < N;
    }
    
    static boolean leftFish() {
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] > 0 && arr[i][j] < 7) {
                    if(arr[i][j] < babySize) 
                        return true;
                }
            }
        }
        return false;
    }
}