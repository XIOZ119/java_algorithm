import java.util.*;
import java.io.*;

//5 7
//WLLWWWL
//LLLWLLL
//LWLWLWW
//LWLWLLL
//WLLWLWW

public class Main {
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int depth, minDepth;
	static int longX, longY, startX, startY; 
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		arr = new char[X][Y];
		visited = new boolean[X][Y];
		
		for(int i=0; i<X; i++) {
			String input = br.readLine();
			for(int j=0; j<Y; j++) {
				arr[i][j] = input.charAt(j);
			}
		}
		
		depth = 0;
		minDepth = 0;
		
		longX = longY = startX = startY = -1;
		
		for(int i=0; i<X; i++) {
			for(int j=0; j<Y; j++) {
				if(arr[i][j] == 'L') {
					visited = new boolean[X][Y];
					bfs(i, j);
					
				}
			}
		}
		
		visited = new boolean[X][Y];
		int mind = bfs2(startX, startY, longX, longY); 
		
//		bw.write(startX + " " + startY + " " + longX + " " + longY + "\n");
		bw.write(mind + " ");
		bw.flush();
		bw.close();
		
	}
	
	private static void bfs(int x, int y) {
		
		Queue<int[]> queue = new LinkedList<>();
		
		visited[x][y] = true;
		queue.add(new int[] {x, y, 0});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int currentX = poll[0];
			int currentY = poll[1];
			int currentDepth = poll[2];
			
			for(int i=0; i<4; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				int nextDepth = currentDepth + 1;
				
				if(nextX > -1 && nextY > -1 && nextX < arr.length && nextY < arr[0].length) {
					if(!visited[nextX][nextY]) {
						if(arr[nextX][nextY] == 'L') {
							visited[nextX][nextY] = true;
//							System.out.println("Queue: " + nextX + " " + nextY + " " + nextDepth + "");
							queue.add(new int[] { nextX, nextY, nextDepth });
						}
					}
				}
			}
			
			if(depth < currentDepth) {
				startX = x;
				startY = y;
				longX = currentX;
				longY = currentY;
				depth = currentDepth;
//				System.out.println("depth 변동 : " + startX + " " + startY + " " + longX + " " + longY + " " + depth);
			}
		}
	}
	
	private static int bfs2(int startX, int startY, int longX, int longY) {
		Queue<int[]> queue = new LinkedList<>();
				
		queue.add(new int[] {startX, startY, 0});
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int currentX = poll[0];
			int currentY = poll[1];
			int currentDepth = poll[2];
			
			if(longX == currentX && longY == currentY) {
				return currentDepth;
			}
			
			for(int i=0; i<4; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				int nextDepth = currentDepth + 1;
				
				if(nextX > -1 && nextY > -1 && nextX < arr.length && nextY < arr[0].length) {
					if(arr[nextX][nextY] == 'L') {
						if(!visited[nextX][nextY]) {
							visited[nextX][nextY] = true;
//							System.out.println("Queue: " + nextX + " " + nextY + " " + nextDepth + ""); 
							queue.add(new int[] { nextX, nextY, nextDepth });
						}
					}
				}
			}
		}
		return -1;
	}
}