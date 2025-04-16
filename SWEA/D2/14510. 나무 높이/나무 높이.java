import java.util.*;
import java.io.*;

public class Solution {
	
	// 하루에 한 나무에 물을 줄 수 있음.
	// 홀수 날 : +1 
	// 짝수 날 : -1
	// 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 하기
	// 나무 정렬 -> 
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int i=1; i<testCase+1; i++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N];
			int max = 0;
			int result = 0;
			int[] count = new int[3];
			
			for(int j=0; j<N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[j]);
			}
			
			for(int j=0; j<N; j++) {
				if(arr[j] != max) {
					int d = max - arr[j];
					int two = d/2;
					int one = d%2;
					
					count[1] += one;
					count[2] += two;
				}
			}
			
			while(true)  {
				if(Math.abs(count[1] - count[2]) == 1) break;
				if(count[1] == count[2]) break;
				if(count[1] > count[2]) break;
				
				count[2] += -1;
				count[1] += 2;
			}
			
			if(count[1] > count[2]) {
				result = count[1] * 2 - 1;
			} else {
				result = count[2] * 2;
			}
			
			sb.append("#" + i + " " + result);
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}