import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static char[][] maze;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	// BFS 중복 방문 -> 무한 루프 => visited[x][y][keyMask] 형태로 방문 여부 체크 해야함 
	// 같은 위치라도 소유한 키가 다르면 새로운 상태이기에, 동일한 상태일 때만 방문 막아야함.
	
	private static class State {
		int x, y, distance, keyMask;
		
		State(int x, int y, int distance, int keyMask){
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.keyMask = keyMask;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		visited = new boolean[N][M][64];
		
		int startX = 0, startY = 0; // 시작점 
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = str.charAt(j);
				if(maze[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}
		
		int result = bfs(startX, startY);
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}
	
	private static int bfs(int cx, int cy) {
		Queue<State> queue = new LinkedList<>();
		queue.add(new State(cx, cy, 0, 0));
		visited[cx][cy][0] = true;
		
		while(!queue.isEmpty()) {
			State current = queue.poll();
			
			int curX = current.x;
			int curY = current.y;
			int curDist = current.distance;
			int keyMask = current.keyMask;
			
			for(int i=0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				int nextKeyMask = keyMask;
				
				if (!isValid(nx, ny) || maze[nx][ny] == '#') continue;

				if(maze[nx][ny] == '1') { // 출구일 때
					return curDist + 1;
				}
				
				if('a' <= maze[nx][ny] && maze[nx][ny] <= 'f'){ // 키를 가졌을 때
					nextKeyMask |= (1 << (maze[nx][ny] - 'a'));
				}
				
				if('A' <= maze[nx][ny] && maze[nx][ny] <= 'F'){ // 키를 가졌을 때
					if((keyMask & (1 << (maze[nx][ny] - 'A'))) == 0) continue; // 키가 없는 경우
				}
				
				if(!visited[nx][ny][nextKeyMask]) {
					visited[nx][ny][nextKeyMask] = true;
					queue.add(new State(nx, ny, curDist+1, nextKeyMask));
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < N && y < M;
	}
}