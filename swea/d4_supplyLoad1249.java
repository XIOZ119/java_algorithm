import java.util.*;
import java.io.*;
import java.lang.*;

public class d4_supplyLoad1249 {
	private static int size, min;
	private static int[][] arr;
	private static int[][] shortD;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			size = Integer.parseInt(br.readLine());
			
			arr = new int[size][size];
			shortD = new int[size][size];
			
			for(int i=0; i<size; i++) {
				String str = br.readLine();
				for(int j=0; j<size; j++) {
					arr[i][j] = str.charAt(j) - '0';
					shortD[i][j] = Integer.MAX_VALUE;
				}
			}
			
			shortD[0][0] = 0;
			min = 0;
			dfs(0, 0, size-1, size-1, 0);
			
			sb.append("#" + tc + " " + min + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int startX, int startY, int endX, int endY, int distance) {
		
		if(startX == endX && startY == endY) {
			min = distance;
		}
		
		for(int i=0; i<4; i++) {
			int nx = startX + dx[i];
			int ny = startY + dy[i];
			
			if(isPossible(nx, ny)) {
				if(distance + arr[nx][ny] < shortD[nx][ny]) {
					shortD[nx][ny] = distance + arr[nx][ny];
					dfs(nx, ny, endX, endY, shortD[nx][ny]);
				}
			}
		}

	}
	
	private static boolean isPossible(int x, int y) {
		return x > -1 && y > -1 && x < size && y < size;
	}
}