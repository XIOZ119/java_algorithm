package swea;

import java.io.*;

public class d4_maze2 {
	class Solution {
		private static int[][] maze;
		private static boolean result;
		private static boolean[][] visited; 
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			for(int testCase=1; testCase<=1; testCase++) {
				maze = new int[16][16];
				visited = new boolean[16][16];
				result = false;
				
				int testNum = Integer.parseInt(br.readLine());
				
				for(int i=0; i<16; i++) {
					String[] row = br.readLine().split(" ");
					for(int j=0; j<16; j++) {
						maze[i][j] = Integer.parseInt(row[j]);
					}
				}
				
				bfs(1, 1);
				
				
				bw.write("#" + testNum + " " + result);
			}
			bw.flush();
			bw.close();
		}
		
		private static void bfs(int x, int y) {
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			
			visited[x][y] = true;
					
			for(int i=0; i<4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX>0 && nextY>0 && nextX<16 && nextY<16) {
					if(maze[nextX][nextY] == 0 && visited[nextX][nextY] == false) {
						bfs(nextX, nextY);
					} else if(maze[nextX][nextY] == 3) {
						result = true;
						break;
					}
				}
			}
		}
	}
}
