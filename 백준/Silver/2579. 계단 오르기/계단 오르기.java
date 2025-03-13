import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	private static int[] stair;
	private static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		stair = new int[N+1];
		dp = new int[N+1];
		stair[0] = 0;
		
		for(int i=1; i<N+1; i++) {
			stair[i] = Integer.parseInt(br.readLine()); 
		}
		
		dp[1] = stair[1];
		if(N > 1) {
			dp[2] = stair[1] + stair[2];
		}
		
		if(N >= 3) {
			for(int i=3; i<=N; i++) {
				back(i);
			}
		}

		bw.write(dp[N] + "");
		bw.flush();
		bw.close();
	}
	
	private static void back(int n) {
		
		// 직전칸에서 올라왔을 경우 
		int a = stair[n-1] + dp[n-3] + stair[n];
		// 두 칸 전에서 올라왔을 경우 
		int b = dp[n-2] + stair[n];
		
		int max = Math.max(a, b);
		
		dp[n] = max;
	}
}