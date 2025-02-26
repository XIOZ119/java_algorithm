import java.io.*;
import java.util.*;

public class Main {
	private static int[] arr;
	private static boolean[] visited;
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		arr = new int[M];
		
		perm(0);
		
	}
	
	private static void perm(int depth) {
		
		if(depth == M) {
			for(int val: arr) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<N; i++) {
			
			if(visited[i] == false) {
				visited[i] = true;
				arr[depth] = i+1;
				perm(depth+1);
				
				visited[i] = false;
			}
			
		}
	}

}