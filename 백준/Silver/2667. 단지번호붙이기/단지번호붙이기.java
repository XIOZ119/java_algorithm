import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {
	static int arr[][];
	static boolean visited[][];
	static ArrayList<Integer> counts;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int count, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		counts = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		int house = 0; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					count = 0;
					house++;
					counts.add(dfs(i, j));
				}
			}
		}
		
		Collections.sort(counts);
		
		bw.write(house + "\n");
		for(int i=0; i<counts.size(); i++) {
			bw.write(counts.get(i) + "\n");
		}
		bw.flush();
		bw.close();
	}	
	
	private static int dfs(int x, int y) {
		visited[x][y] = true;
		count++;
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if( nextX > -1 && nextY > -1 && nextX < N && nextY < N ) {
				if(arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
					visited[nextX][nextY] = true;
					dfs(nextX, nextY);
				}
			}
		}
		
		return count;
	}
}