
import java.io.*;
import java.lang.*;
import java.util.*;

public class d5_shortRoad1247 {
	private static ArrayList<int[]> customer;
	private static boolean[][] visited;
	private static int shortD, N;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// 회사, 집, 고객의 좌표
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			int company[] = new int[2];
			int home[] = new int[2]; 

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			customer = new ArrayList<>();
			visited = new boolean[101][101];
			
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			
			for(int i=0; i<N; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				customer.add(new int[] {a,b});
			}
			
			shortD = Integer.MAX_VALUE;
			backTrack(company[0], company[1], 0, home[0], home[1], 0);
			
			sb.append("#" + tc + " " + shortD + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void backTrack(int cx, int cy, int d, int endX, int endY, int count) {
		
		visited[cx][cy] = true;
		
		if(count == N) {
//			System.out.println("끝");
			
			int x = Math.abs(endX - cx);
			int y = Math.abs(endY - cy);
			int sum = x + y;
			
			shortD = Math.min(shortD, sum + d);
		}
		
//		System.out.println("cx " + cx + " cy " + cy + " d " + d);
		
		for(int[] child: customer) {
			int nx = child[0];
			int ny = child[1];
			
			if(!visited[nx][ny]) {
				visited[nx][ny] = true;
				count++;
				
//				System.out.println("nx " + nx + " ny " + ny + " count " + count);
				
				int dx = Math.abs(cx - nx); 
				int dy = Math.abs(cy - ny);
				int addD = dx + dy;
				
				backTrack(nx, ny, d+addD, endX, endY, count);
				
				visited[nx][ny] = false;
				count--;
			}

		} 
		
		
	}

}
