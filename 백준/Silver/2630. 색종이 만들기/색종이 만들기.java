import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	private static int N;
	private static int[][] arr;
	private static int blue, white;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		// 0 : white, 1 : blue
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		blue = 0;
		white = 0;
		
		partition(0, 0, N);
		
		bw.write(white + "\n");
		bw.write(blue + "\n");
		bw.flush();
		bw.close();

	}
	
	private static void partition(int x, int y, int size) {
		
		// 색 확인 
		boolean check = colorCheck(x, y, size);
		
		// 색 같을 경우 1추가
		if(check) {
			int color = arr[x][y];
			if(color == 1) blue++;
			else white++;
			
			return;
		}
		
		else {
			// 색 다를 경우
			size = size/2;
			partition(x, y, size); // 제1사분면
			partition(x, y+size, size); // 제2사분면
			partition(x+size, y, size); // 제3사분면
			partition(x+size, y+size, size); // 제4사분면
		}
	}
	
	private static boolean colorCheck(int x, int y, int size) {
		
		// 첫 색상 
		int color = arr[x][y];

		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(arr[i][j] != color) return false;
			}
		}
		
		return true;
	}
}