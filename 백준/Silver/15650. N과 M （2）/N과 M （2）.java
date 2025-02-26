import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int M;
	private static int[] arr;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		visited = new boolean[N];
		
		dfs(0);
	}
	
	private static void dfs(int depth) {
		
		if(depth == M) {
			for(int result: arr) {
				System.out.print(result + " ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			
			if(!visited[i]) {
				int d = 0;
				if(depth > 0) {
					d = depth - 1; 					
				}
				if(arr[d] < i+1){				
					visited[i] = true;
					arr[depth] = i + 1;
					dfs(depth+1);
					
					visited[i] = false;
				}
			}
		}
	}

}