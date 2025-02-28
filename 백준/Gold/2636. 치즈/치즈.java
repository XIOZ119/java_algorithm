import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int[][] arr;
	static boolean[][] visited;
	static int count, depth, x, y;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		x = Integer.parseInt(str[0]);
		y = Integer.parseInt(str[1]);
		
		arr = new int[x][y];
		visited = new boolean[x][y];
		
		for(int i=0; i<x; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<y; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		depth = 0;
		int ch = 0;
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if(arr[i][j] == 1) ch++;
			}
		}
		
		while(true) {
			if(ch > 0) {
				
				visited = new boolean[x][y];
				ch = bfs(0, 0);
				for(int i=0; i<x; i++) {
					for(int j=0; j<y; j++) {
					}
				}
				if(ch!=0) {
					depth++;
					count = ch;
				} else break;
			}
			
		}
		
		bw.write(depth + "\n");
		bw.write(count + "");
		bw.flush();
		bw.close();
	}
	
	private static int bfs(int ax, int ay) {
		Queue<int[]> queue = new LinkedList<>();
		
		int c = 0;
		
		queue.add(new int[] {ax, ay});
		visited[ax][ay] = true;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll(); 
			
			int cx = poll[0];
			int cy = poll[1];
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i]; 
				int ny = cy + dy[i];
								
				if(isValid(nx, ny)) {
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;
						if(arr[nx][ny] == 0) {
							queue.add(new int[] {nx, ny});							
						} else {
							arr[nx][ny] = 0;
							c++;
						}
					} 
				}
			}
		}
		
		return c;
	}
	
	
	private static boolean isValid(int ax, int ay) {
		return ax >= 0 && ay >= 0 && ax < x && ay < y;
	}
}