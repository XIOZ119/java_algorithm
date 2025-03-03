import java.util.*;
import java.io.*;

public class Main {
	static int[][] city;
	static List<int []> chickenHouse;
	static List<int []> home;
	static int N, M;
	static int chickenDistance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 최대 치킨 집 개
		
		city = new int[N+1][N+1];
		home = new ArrayList<>();
		chickenHouse = new ArrayList<>();
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j] == 1) home.add(new int[] {i, j});
				if(city[i][j] == 2) chickenHouse.add(new int[] {i, j}); 
			}
		}
						
		List<int []> selected = new ArrayList<>();
		combination(0, selected);
		
		bw.write(chickenDistance + " ");
		bw.flush();
		bw.close();
	}
	
	private static void combination(int start, List<int[]> selected) {
		if(selected.size() == M) {
			int sumDistance = distance(selected);
			chickenDistance = Math.min(chickenDistance, sumDistance);
			return;
		}
		for(int i=start; i<chickenHouse.size(); i++) {
			selected.add(chickenHouse.get(i));
			combination(i+1, selected);
			selected.remove(selected.size()-1);
		}
	}
	
	private static int distance(List<int[]> selected) {
		int totalDistance = 0;
		
		for(int[] h: home) {
			int minDist = Integer.MAX_VALUE;
			for(int[] c: selected) {
				int distance = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
				minDist = Math.min(distance, minDist);
			}
			totalDistance += minDist;
		}
		return totalDistance;
	}
}