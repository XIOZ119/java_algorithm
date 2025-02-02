package study01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week01_Problem01_11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		int count = Integer.parseInt(st.nextToken());
		
		String[] strArr = br.readLine().split(" ");
		
		int[] prefixSum = new int[num + 1];
		prefixSum[0] = Integer.parseInt(strArr[0]);
		
		for (int i=1; i<num; i++) {
			prefixSum[i] = prefixSum[i-1] + Integer.parseInt(strArr[i]);
		}
		
		for (int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			int sum = 0;
			
			if (start == 0) {
				sum = prefixSum[end];
			} else {
				sum = prefixSum[end] - prefixSum[start - 1];
			}

			sb.append(sum);
			sb.append('\n');
		}
		System.out.println(sb);
	}
};