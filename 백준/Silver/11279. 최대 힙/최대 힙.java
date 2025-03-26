import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
				
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(br.readLine());
			
			if(a > 0) pq.add(a);
			else if (a == 0) {
				if(!pq.isEmpty()) {
					int re = pq.poll();
					sb.append(re + "\n");
				} else {
					sb.append(0 + "\n");
				}
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}