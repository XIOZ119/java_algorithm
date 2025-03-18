import java.util.*;
import java.io.*;

public class d3_햄버거다이어트5215 {
	
	private static int N, L, maxScore;
	private static int[] score, calory;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			score = new int[N];
			calory = new int[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				score[i] = Integer.parseInt(st.nextToken());
				calory[i] = Integer.parseInt(st.nextToken());
			}
			
			maxScore = 0;
			
			comb(0, new ArrayList<Integer>());
			
			sb.append("#" + tc + " " + maxScore + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void comb(int start, List<Integer> list) {
		
		if(!list.isEmpty()) {
			int sumC = 0;
			int sumS = 0;
			
			for(int l: list) {
				sumC += calory[l];
				sumS += score[l];
			}
			
			if(sumC <= L) {
				maxScore = Math.max(maxScore, sumS);
			} else return;
		}
		
		for(int i=start; i<N; i++) {
			list.add(i);
			comb(i+1, list);
			list.remove(list.size() - 1);
		}
	}
}