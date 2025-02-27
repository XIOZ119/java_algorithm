import java.io.*;
import java.util.ArrayList;

class Main {
	static int[] arr;
	static int[][] S;
	static int N, M;
	static boolean[] visited;
	static ArrayList<Integer> t1;
	static ArrayList<Integer> t2;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = N / 2;
		
		arr = new int[N+1];
		S = new int[N+1][N+1];
		visited = new boolean[N+1];
		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		
		for(int i=1; i<N+1; i++) {
			arr[i] = i;
		}
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=1; j<N+1; j++) {
				S[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		result = Integer.MAX_VALUE;
		
		Combination(1, 0);
		
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}
	
	private static void Combination(int start, int depth) {

		if(depth == M) {
			t1.clear();
			t2.clear();
			
			for(int i=1; i<N+1; i++) {
				if(visited[i] == true) t1.add(i);
				else t2.add(i);
			}
			
			int t1Power = 0, t2Power = 0;
			
			for(int i=0; i<M; i++) {
				for(int j=i+1; j<M; j++) {
					t1Power += S[t1.get(i)][t1.get(j)] + S[t1.get(j)][t1.get(i)];
					t2Power += S[t2.get(i)][t2.get(j)] + S[t2.get(j)][t2.get(i)];
				}
			}
			
			result = Math.min(result, Math.abs(t1Power - t2Power));
		}
		
		for(int i=start; i<N+1; i++) {
			visited[i] = true;
			Combination(i+1, depth+1);
			visited[i] = false;
		}
	}
}