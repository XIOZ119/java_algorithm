import java.util.*;
import java.io.*;

public class Main {
	private static int R, C;
	private static char[][] arr;
	private static boolean[][] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		max = 0;
		backTrack(new HashSet<Character>(), 0, 0);
		
		bw.write(max + "");
		bw.flush();
		bw.close();
	}
	
	private static void backTrack(HashSet<Character> set, int x, int y) {
		
		set.add(arr[x][y]);
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i];
			
			if(isValid(nx, ny) && !visited[nx][ny]) {
				if(!set.contains(arr[nx][ny])) {
					set.add(arr[nx][ny]);
					visited[nx][ny] = true;
					
					backTrack(set, nx, ny);
					
					set.remove(arr[nx][ny]);
					visited[nx][ny] = false;
					
				}
			}
		}
		
		if(!set.isEmpty()) {
			max = Math.max(max, set.size());
		}
	}
	
	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < R && y < C;
	}
}