import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<t; tc++) {
			int a = Integer.parseInt(br.readLine());
			
			int[] dp = new int[12];
			
			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4; 

			int sum = 7; 
			
			for(int i=4; i<=a; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			sb.append(dp[a] + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}