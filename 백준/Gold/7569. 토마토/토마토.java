import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int N, M, H, depth; 
	static int[][][] arr;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0, 0, 0}; // 좌, 우, 앞, 뒤, 위, 아래
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		//  M(가로), N(세로),H(높이)
		// 3차원 배열 만들 때는 높이(z축), 세로(y축), 가로(x축) HxNxM 
		M = Integer.parseInt(str[0]); 
		N = Integer.parseInt(str[1]); 
		H = Integer.parseInt(str[2]);
		
		arr = new int[H][N][M];
		visited = new boolean[H][N][M];
		
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				String[] tom = br.readLine().split(" ");
				for(int m=0; m<M; m++) {
					arr[h][n][m] = Integer.parseInt(tom[m]);
				}
			}
		}
		
		queue = new LinkedList<>();
		boolean flag = false;
		
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					if(arr[h][n][m] == 1) {
						queue.add(new int[] {h, n, m, 0});
					}
					if(arr[h][n][m] == -1 && !visited[h][n][m]) visited[h][n][m] = true;
					if(arr[h][n][m] == 0) {
						flag = true;
					}
				}
			}
		}
		
		int result = -1; 
		depth =  0;

		if(!flag) {
			result = 0;
		} else {
			result = bfs();
		}

		bw.write(result + " ");
		bw.flush();
		bw.close();
	}
	
	
	
	private static int bfs() {
		while(!queue.isEmpty()) {

			int[] poll = queue.poll();
			
			int z = poll[0];
			int y = poll[1];
			int x = poll[2];
			int d = poll[3];

			visited[z][y][x] = true;
			
			depth = d;
			
			for(int i=0; i<6; i++) {
				int nextZ = z + dz[i];
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				
				if(isValidRange(nextZ, nextY, nextX)) {
					if(!visited[nextZ][nextY][nextX] && arr[nextZ][nextY][nextX] == 0) {
						
						visited[nextZ][nextY][nextX] = true;
						queue.add(new int[] {nextZ, nextY, nextX, d+1});
					}
				}
			}
		}
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(!visited[i][j][k] && arr[i][j][k] == 0) {
						return -1;
					}
				}
			}
		}
		
		return depth;
	}
	
	private static boolean isValidRange(int z, int y, int x) {
		return z >= 0 && z < H && y >= 0 && y < N &&  x >= 0 && x < M;
	}
}