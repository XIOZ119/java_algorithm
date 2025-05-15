import java.io.*;
import java.util.*;

public class Main {
    
    /*
     * 1
		4 4
		0 0 0 0
		1 0 0 0
		0 0 1 0
		0 1 0 0
     */
	
	static int K, W, H; // 말처럼 움직일 수 있는 횟수, 가로길이 , 세로 길이 
	static int[][] arr;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[] hx = {-2, -1, -2, -1, 1, 1, 2, 2}, hy = {-1, -2, 1, 2, -2, 2, -1, 1};
	static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 입력
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        arr = new int[H][W];
        
        for(int i=0; i<H; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<W; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited = new boolean[H][W][K+1];
        
        result = Integer.MAX_VALUE;
        bfs(0, 0);
        
        if(result == Integer.MAX_VALUE) {
        	bw.write("-1");
        } else {
        	bw.write(result + "");
        }
        
        bw.flush();
        bw.close();
    }
    
    static void bfs(int x, int y) {
    	Queue<int []> queue = new LinkedList<>();
    	
    	queue.add(new int[] {x, y, 0, 0});
    	for(int i=0; i<K; i++) {
    		visited[0][0][i] = true;
    	}
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		
    		int cx = cur[0];
    		int cy = cur[1];
    		int cc = cur[2]; // 진행된 말 행동 횟수
    		int cd = cur[3]; // 이동 횟수 
    		
    		if(cx == H-1 && cy == W-1) {
    			result = Math.min(result, cd);
    		}
    		
    		if(cc != K) {
    			for(int i=0; i<8; i++) {
    				int nx = cx + hx[i];
    				int ny = cy + hy[i];
    				
    				if(!isValid(nx, ny)) continue;
    				
    				if(!visited[nx][ny][cc+1] && arr[nx][ny] != 1) {
    					visited[nx][ny][cc+1] = true;
    					queue.add(new int[] {nx, ny, cc+1, cd+1});
    				}
    			}
    			for(int i=0; i<4; i++) {
    				int nx = cx + dx[i];
    				int ny = cy + dy[i];
    				
    				if(!isValid(nx, ny)) continue;
    				
    				if(!visited[nx][ny][cc] && arr[nx][ny] != 1) {
    					visited[nx][ny][cc] = true;
    					queue.add(new int[] {nx, ny, cc, cd+1});
    				}
    			}
    		} else {
    			for(int i=0; i<4; i++) {
    				int nx = cx + dx[i];
    				int ny = cy + dy[i];
    				
    				if(!isValid(nx, ny)) continue;
    				
    				if(!visited[nx][ny][cc] && arr[nx][ny] != 1) {
    					visited[nx][ny][cc] = true;
    					queue.add(new int[] {nx, ny, cc, cd+1});
    				}
    			}
    		}
    	}
    }
    
    static boolean isValid(int x, int y) {
    	return x >= 0 && y >= 0 && x < H && y < W;
    }
}