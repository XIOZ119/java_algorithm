import java.io.*;
import java.util.ArrayList;

class Main {
	
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static int cnt; 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int number = Integer.parseInt(br.readLine());
		int line = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[number + 1];
		visited = new boolean[number + 1];
		
		for(int i=0; i<=number; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<line; i++) {
			String[] str = br.readLine().split(" ");
			
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		cnt = 0;
		int count = dfs(1);
	
		bw.write(count + " ");
		bw.flush();
		bw.close();
			
	}
	
	private static int dfs(int start) {
		visited[start] = true;
		
		for(int child: arr[start]) {
			if(!visited[child]) {
				visited[child] = true;
				cnt++;
				dfs(child); 
			}
		}
		
		return cnt;
	}
}