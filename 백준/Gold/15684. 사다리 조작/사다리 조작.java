import java.io.*;
import java.util.*;

public class Main {
	
	/* [백준] 15684. 사다리 조작
	 * N : 세로선, M : 가로선, H : 세로선마다 가로선 놓을 수 있는 위치 개수
	 * 가로선 정보 : a, b : b번 세로선과 b+1번 세로선을 a번 점선 위치에 연결
	 * 조건 : 사다리는 최대 3개까지 추가할 수 있음.
	 */
	
	static int N, M, H;
	static boolean[][] ladder;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		M = Integer.parseInt(st.nextToken()); // 가로선 개수 
		H = Integer.parseInt(st.nextToken()); // 세로선 마다 가로선을 놓을 수 있는 위치 개수
		
		ladder = new boolean[H][N-1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			ladder[a][b] = true;
		}
		
		result = Integer.MAX_VALUE;
		
		// 사다리 선택
		if(M == 0) {
			result = 0;
		} else {
			choice(0, 0);	
		}

		if(result == Integer.MAX_VALUE) {
			bw.write(-1 + "");
		} else {
			bw.write(result + "");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void choice(int count, int start) { // 조...합...?
		
		if(count > 3 || result <= count) return;
		
		if(gameStart()) {
			result = Math.min(result, count);
			return;
		}
		
		// H*(N-1) : 2차원 -> 1차원 인덱스로 처리
		for(int idx= start; idx<H*(N-1); idx++) {
			int i = idx / (N-1); // 1차 인덱스를 행 인덱스로 변환
			int j = idx % (N-1); // 열 인덱스로 변환
			
			if(ladder[i][j]) continue;
			if(j > 0 && ladder[i][j-1]) continue;
			if(j < N-2 && ladder[i][j+1]) continue;
			
			ladder[i][j] = true;
			choice(count + 1, idx + 1);
			ladder[i][j] = false;
		}
		
		return;
	}
	
	static boolean gameStart() {

		boolean flag = true;
		
		for(int line=0; line<N; line++) {
			int loc = line;
			for(int i=0; i<H; i++) {
				if(loc == 0) {
					if(ladder[i][loc]) {
						loc++;
					}
				}
				else if(loc == N-1) {
					if(ladder[i][loc-1]) {
						loc--;
					}
				}
				else {
					if(ladder[i][loc]) {
						loc++;
					}
					else if(ladder[i][loc-1]) {
						loc--;
					}
				}
			}
			
			if(line != loc) {
				flag = false;
			}
		}
		
		return flag;
	}
}