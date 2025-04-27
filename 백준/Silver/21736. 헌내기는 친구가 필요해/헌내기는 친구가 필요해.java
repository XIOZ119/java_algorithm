import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    /*
     * 3 5  
     * OOOPO
     * OIOOX
     * OOOXP
     * O는 빈 공간, X는 벽, I는 도연이, P는 사람
     */

    static int r, c;
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        int startX = 0; 
        int startY = 0;

        for(int i=0; i<r; i++) {
            String str = br.readLine();
            for(int j=0; j<c; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'I') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int result = bfs(startX, startY);

        if(result == 0 ){
            bw.write("TT");
        } else {
            bw.write(result + "");
        }
        bw.flush();
        bw.close();
    }

    static int bfs(int x, int y){
        boolean[][] visited = new boolean[r][c];
        Queue<int[]> queue = new LinkedList<>();
        int meet = 0;

        visited[x][y] = true;
        queue.add(new int[] {x, y});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!isValid(nx, ny) || visited[nx][ny]) continue;
                if(arr[nx][ny] == 'X') continue;
            
                if(arr[nx][ny] == 'P') meet++;
                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }
        }
        
        return meet;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < r && y < c;
    }
}  