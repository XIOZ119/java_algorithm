import java.util.*;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                };
				return o1[1] - o2[1];
			}
		});
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {start, end});
		}
		
		int meeting[] = pq.poll();
		int count = 1;
		
		while(!pq.isEmpty()) {
			int nextMeeting[] = pq.poll(); 
			if(meeting[1] <= nextMeeting[0]) {
				count++;
				meeting = nextMeeting;
			}
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}

}