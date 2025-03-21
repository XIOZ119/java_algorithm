import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	
	static int N;
	static int[][] room;
	static List<int []> person;
	static List<int []> stair;
	static boolean[] visited;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static boolean[][] stayingStair;
	static int resultTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			room = new int[N][N];
			
			person = new ArrayList<int[]>();
			stair = new ArrayList<int []>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
					
					if(room[i][j] == 1) person.add(new int[] {i, j});
					if(room[i][j] > 1) stair.add(new int[] {i, j});
				}
			}
			
			// 조합 
			visited = new boolean[person.size()];
			resultTime = Integer.MAX_VALUE;
			
			comb(new ArrayList<Integer>(), new ArrayList<Integer>(), 0);
			
			sb.append("#" + tc + " " + resultTime + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void comb(List<Integer> stair1, List<Integer> stair2, int start) {
		
		if(stair1.size() + stair2.size() == person.size()) {

			// 계단까지 거리 계산
			int time = gotoStair(stair1, stair2);
//			System.out.println("resultTime: " + time);
			resultTime = Math.min(resultTime, time);
		}
		
		for(int i=start; i<person.size(); i++) {
			if(!visited[i]) {
				
				visited[i] = true;
				
				stair1.add(i);
				comb(stair1, stair2, i+1);
				stair1.remove(stair1.size() - 1);
				
				stair2.add(i);
				comb(stair1, stair2, i+1);
				stair2.remove(stair2.size() - 1);

				visited[i] = false;
			}
		}
	}
	
	private static int gotoStair(List<Integer> stair1, List<Integer> stair2) {
		stayingStair = new boolean[100][100];
		int endTime = 0;
		List<int []> list = new ArrayList<>();
		
		if(!stair1.isEmpty()) {
			for(int index: stair1) {
				int[] pLoc = person.get(index);
				int[] sLoc = stair.get(0);
				
				int distance = Math.abs(pLoc[0] - sLoc[0]) + Math.abs(pLoc[1] - sLoc[1]);
				
				// 정렬 후 보내야함 
				list.add(new int[] {index, distance+1, room[sLoc[0]][sLoc[1]]});
			}
			
			Collections.sort(list, new Comparator<int []>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
				
			});
			
			for(int[] index: list) {
				// 계단 이동 체크 
				int time = downStair(index[0], index[1], index[2]);

				endTime = Math.max(endTime, time);
			}
		}

		list.clear();
		
		stayingStair = new boolean[100][100];
		if(!stair2.isEmpty()) {
			for(int index: stair2) {
				int[] pLoc = person.get(index);
				int[] sLoc = stair.get(1);
				
				int distance = Math.abs(pLoc[0] - sLoc[0]) + Math.abs(pLoc[1] - sLoc[1]);
				
				list.add(new int[] {index, distance+1, room[sLoc[0]][sLoc[1]]});
			}
			
			Collections.sort(list, new Comparator<int []>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
				
			});
			
			for(int[] index: list) {
				// 계단 이동 체크 
				int time = downStair(index[0], index[1], index[2]);

				endTime = Math.max(endTime, time);
			}
		}
		
		return endTime;
	}
	
	private static int downStair(int personNum, int distance, int downTime) {
		int time = 0;
		int start = 0;
		
		for(int i=distance; i<100; i++) {
			int stayingPersonNum = 0;
			for(int j=0; j<person.size(); j++) {
				if(stayingStair[i][j]) {
					stayingPersonNum++;
				}
			}
			if(stayingPersonNum < 3) {
				start = i;
				break;
			}
		}
		
		for(int k=start; k<=start+downTime-1; k++) {
			stayingStair[k][personNum] = true;
			time = k+1;
		}
		
		return time;
	}
}
