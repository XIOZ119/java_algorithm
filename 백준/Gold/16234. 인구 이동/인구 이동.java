import java.util.*;
import java.io.*;

public class Main {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = {0, 0, -1, 1 };
	static int N, L, R;
	static boolean moveFlag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		int ct = 0;
		
		while(true) {
			visited = new boolean[N][N];
			moveFlag = false;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			if(!moveFlag) break;
			ct++;
		}
		
		bw.write(ct + " ");
		bw.flush();
		bw.close();
		
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		List<int[]> move = new LinkedList<>();
		
		visited[x][y] = true;
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int cx = poll[0]; 
			int cy = poll[1]; 
			
			move.add(new int[] {cx, cy});
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(isPossible(nx, ny) && !visited[nx][ny]) {
					int a = Math.abs(arr[nx][ny] - arr[cx][cy]);
					
					if(L <= a && a <= R) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
			}
		}
		
		int countryCount = move.size();
		int sum = 0;
		if(countryCount > 1 ) {
			moveFlag = true;
			for(int[] m: move) {
				int mx = m[0];
				int my = m[1];
				
				sum += arr[mx][my];
			}
			
			int avg = sum / countryCount;
			
			for(int[] m: move) {
				int mx = m[0];
				int my = m[1];
				
				arr[mx][my] = avg;
			}
		}
	}
	
	private static boolean isPossible(int x, int y) {
		return x > -1 && y > -1 && x < N && y < N;
	}
		
}