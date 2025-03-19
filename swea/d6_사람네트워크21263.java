
import java.io.*;
import java.lang.*;
import java.util.*;

public class d6_사람네트워크21263 {
	
	static int N;
	static List<Integer>[] net;
	static int[] arr;
	static int[] cc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			net = new ArrayList[N+1]; // 입력으로 받은 그래프
			cc = new int[N+1]; // 최소 결과 저장
			
			for(int i=0; i<N+1; i++) {
				net[i] = new ArrayList<>();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(Integer.parseInt(st.nextToken()) != 0) {
						net[i+1].add(j+1);
					}
				}
			}
			
			for(int i=1; i<N+1; i++) {
				arr = new int[N+1];
				findDist(i);
			}
			
			int min = Integer.MAX_VALUE;
			
			for(int i=1; i<N+1; i++) {
				min = Math.min(min, cc[i]);
			}
			
			sb.append("#" + tc + " " + min + "\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void findDist(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int child: net[cur]) {
				if(start != child) {
					if(arr[child] > arr[cur]+1 || arr[child] == 0) {
						arr[child] = arr[cur]+1;
						queue.add(child);
					}
				}
			}
		}
		
		int sum = 0;
		for(int a: arr) {
			sum += a;
		}
		
		cc[start] = sum;
	}
}
