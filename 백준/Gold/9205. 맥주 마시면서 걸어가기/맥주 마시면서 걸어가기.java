import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static ArrayList<int[]> places;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<testCase; tc++) {
			int storeNum = Integer.parseInt(br.readLine());
			places = new ArrayList<>();
			
			for(int i=0; i<storeNum+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				places.add(new int[] {x, y});
			}
			
			visited = new boolean[storeNum+2];
			
			if(bfs(0)) {
				bw.write("happy \n");
			} else {
				bw.write("sad \n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	private static boolean bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			int[] currentPos = places.get(current);
			
			if(current == places.size() -1) {
				return true;
			}
			
			for(int i=0; i<places.size(); i++) {
				if(!visited[i]) {
					int[] nextPos = places.get(i);
					int distance = Math.abs(currentPos[0] - nextPos[0]) + Math.abs(currentPos[1] - nextPos[1]);
					
					if(distance <= 1000) {
						visited[i] = true;
						queue.add(i);
					}
				}
			}
		}
		
		return false;
	}
}