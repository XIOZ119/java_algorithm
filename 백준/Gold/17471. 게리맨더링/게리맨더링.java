import java.util.*;
import java.io.*;

public class Main {
	private static int[] person;
	private static boolean[] visited;
	private static ArrayList<Integer>[] arr;
	private static int[] color;
	private static int N;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		person = new int[N+1];
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		color = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<N+1; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<a; j++) {
				int b = Integer.parseInt(st.nextToken());
				arr[i].add(b);
				arr[b].add(i);
			}
		}
		
		result = Integer.MAX_VALUE;
		// combine(빨, 파, 전체 크기) 1 : red, 2 : blue
		combine(0, 0, 0);
		
		if(result == Integer.MAX_VALUE) result = -1;
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}
	
	private static void combine(int red, int blue, int start) {
		
		if(red >= N || blue >= N) return;
		
		if(red+blue == N) {
			// bfs 보내기 ( 연결 확인하러 )
			int dist = bfs();
			if(dist != -1) result = Math.min(dist, result);
			
			return;
		}
		
		for(int i=start; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				
				color[i] = 1; // red
				combine(red+1, blue, i+1);
				
				color[i] = 2; // blue
				combine(red, blue+1, i+1);
				
				visited[i] = false;
				color[i] = 0; //취소 
			}
		}
	}
	
	private static int bfs() {
		Queue<Integer> redQ = new LinkedList<>();
		Queue<Integer> blueQ = new LinkedList<>();
		
		boolean[] v = new boolean[N+1];
		
		int redSum = 0; 
		int blueSum = 0;
		
		for(int i=1; i<N+1; i++) {
			if(color[i] == 1) {
				redQ.add(i);
				v[i] = true;
				redSum += person[i];
				break;
			}
		}
		
		for(int i=1; i<N+1; i++) {
			if(color[i] == 2) {
				blueQ.add(i);
				v[i] = true;
				blueSum += person[i];
				break;
			}
		}
		
		while(!redQ.isEmpty()) {
			int redIndex = redQ.poll();
			
			for(int child: arr[redIndex]) {
				if(!v[child] && color[child] == 1) {
					v[child] = true;
					redSum += person[child];
					redQ.add(child);
				}
			}
		}
		
		while(!blueQ.isEmpty()) {
			int blueIndex = blueQ.poll();
			
			for(int child: arr[blueIndex]) {
				if(!v[child] && color[child] == 2) {
					v[child] = true;
					blueSum += person[child];
					blueQ.add(child);
				}
			}
		}
		
		for(int i=1; i<N+1; i++) {
			if(!v[i]) return -1;
		}
		
		return Math.abs(blueSum-redSum);
	}
}