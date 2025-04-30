import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
	// (6, 5), (4, 60), (4, 40), (4, 10), (3, 30),  (2, 50), (1, 20)
	// 그리디 
	// D 높은 것으로 정렬, 현재 시간 6에서 --
	// 현재 시간 -> D 보다 크거나 같을 경우 고려 대상 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int time = 0;
		
		ArrayList<int []> arr = new ArrayList<>();
		boolean[] visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			time = Math.max(a, time);
			
			arr.add(new int[] {a, b});
		}
		
		Collections.sort(arr, (a, b) -> {
			if(a[0] == b[0]) {
				return b[1] - a[1]; // 점수 내림차순
			}
			
			return b[0] - a[0]; // 마감일 내림차순
		});
		
		while(time > 0) {
			int maxScore = 0;
			int index = -1;
			
			for(int i=0; i<arr.size(); i++) {
				if(arr.get(i)[0] >= time) {
					if(visited[i]) continue;
					
					if(maxScore < arr.get(i)[1]) {
						index = i;
						maxScore = arr.get(i)[1];
					}
				}
			}
			
			if(index != -1) {
				visited[index] = true;
			}
			time--;
		}
		
		int sum = 0;
		for(int i=0; i<visited.length; i++) {
			if(visited[i]) {
				sum += arr.get(i)[1];
			}
		}
			
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
	}
}