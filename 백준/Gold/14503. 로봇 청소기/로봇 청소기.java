import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static int result; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // x좌표
		int c = Integer.parseInt(st.nextToken()); // y좌표
		int d = Integer.parseInt(st.nextToken()); // 방향
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		int re = simul(r, c, d);
		
		bw.write(re + "");
		bw.flush();
		bw.close();
	}
	
	private static int simul(int x, int y, int d) {
		
		if(arr[x][y] == 0) { // 청소 완료
			arr[x][y] = 2;
			result++;
		}

		int count = 0;
		
		while(count < 4) {
			count++;
			d--;
			if(d == -1) d = 3;
			int nnx = x + dx[d];
			int nny = y + dy[d];
			if(arr[nnx][nny] == 0) return simul(nnx, nny, d);
		}
		
		int bx = x - dx[d];
		int by = y - dy[d];
		
		if(arr[bx][by] == 1) {
			return result;
		} else {
			return simul(bx, by, d);
		}
	}
}
