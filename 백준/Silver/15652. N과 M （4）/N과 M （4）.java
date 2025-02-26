import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	private static int N, M;
	private static int[] arr;
	private static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		dfs(0);
		
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int depth) throws IOException {
		if(depth == M) {
			for(int result: arr) {
				bw.write(result + " ");
			}
			bw.write("\n");
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			int d = 0;
			
			if(depth > 0) {
				d = arr[depth - 1];
			}
			
			if(d <= i+1) {
				arr[depth] = i+1;
				dfs(depth + 1);				
			}
			
		}
	}

}