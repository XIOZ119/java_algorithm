import java.util.*;
import java.io.*;

public class Main {
	static int N, M, B, maxHeight;
	static int[][] arr;
	static List<int []> resultHeight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken()); // 인벤토리 
		
		arr = new int[N][M];
		
		maxHeight = Integer.MIN_VALUE;
		int minHeight = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, arr[i][j]);
				minHeight = Math.min(minHeight, arr[i][j]);
			}
		}
		
		resultHeight = new ArrayList<>();
		
		for(int i=maxHeight; i>=minHeight; i--) {
			grading(i);
		}
		
		Collections.sort(resultHeight, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}
				
				return o1[0] - o2[0];
			}
		});
		
		bw.write(resultHeight.get(0)[0] + " " + resultHeight.get(0)[1]);
		bw.flush();
		bw.close();
	}
	
	private static void grading(int height) {
		// 블록 제거 2초 
		// 블록 삽입 1초
		int buildCnt = 0;
		int removeCnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] > height) {
					removeCnt += arr[i][j]-height;
				}
				if(arr[i][j] < height) {
					buildCnt += height-arr[i][j];
				}
			}
		}
		
		// 블록 쌓기 
		if(height == maxHeight && buildCnt <= B) resultHeight.add(new int[] {buildCnt, height});
		if(height < maxHeight && buildCnt <= B + removeCnt) {
			int cnt = 0;
			cnt += buildCnt;
			cnt += removeCnt * 2;
			
			resultHeight.add(new int[] {cnt, height});
		}
	}
}  