import java.io.*;
import java.util.ArrayList;

class Main {
	
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		int start = Integer.parseInt(str[0]);
		int end = Integer.parseInt(str[1]);
		
		int m = Integer.parseInt(br.readLine());
		arr = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for(int i=0; i<n+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		int depth = dfs(start, end, 0);
		
		bw.write(depth + " ");
		bw.flush();
		bw.close();
	}
	
	private static int dfs(int start, int end, int depth) {
		visited[start] = true;
		
		if(start == end) {
			return depth;
		}
		
		for(int child: arr[start]) {
			if(!visited[child]) {
				visited[child] = true;
				int result = dfs(child, end, depth+1);
				visited[child] = false;
				
				if(result != -1) {
					return result;
				}
			}
		}
		
		return -1;
	}
}