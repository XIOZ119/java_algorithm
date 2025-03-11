import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] height = new int[N];
		int[] weight = new int[N];
		int[] score = new int[N];

        for(int i=0; i<N; i++) {
			score[i] = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			height[i] = x;
			weight[i] = y;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(height[i] < height[j] && weight[i] < weight[j]) {
					score[i]++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			bw.write(score[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
