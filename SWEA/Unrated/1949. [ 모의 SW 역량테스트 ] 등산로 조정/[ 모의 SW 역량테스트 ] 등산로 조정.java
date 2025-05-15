import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {


	static int N, K;
	static int[][] arr; 
	static boolean[][] visited;
	static int result;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			visited = new boolean[N][N];
			int max = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(arr[i][j], max);
				}
			}
			
			result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(max == arr[i][j]) {
						visited[i][j] = true;
						dfs(i, j, 1, arr[i][j], 0);		
						visited[i][j] = false;
					}
				}
			}
			
			sb.append("#" + tc + " " + result + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, int y, int count, int value, int work) {
		
		System.out.println(x + " " + y + " " + count + " " + value + " " + work);
		
		if(result < count) {
//			System.out.println(x + " " + y + " " + count);
		}
		
		result = Math.max(result, count);
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isValid(nx, ny)) continue;
			
			for(int j=0; j<=K; j++) {
				if(arr[nx][ny]-j < value && arr[nx][ny] - j >= 0) {
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;
						if(j == 0) {
							dfs(nx, ny, count+1, arr[nx][ny], work);
						}
						else {
							if(work == 0) dfs(nx, ny, count+1, arr[nx][ny]-j, work+1);
						}
						visited[nx][ny] = false;	
					}
				}
			}
		}
	}
	
	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
