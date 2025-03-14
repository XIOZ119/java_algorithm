import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			
			int a = Integer.parseInt(br.readLine());
			
			if(a > 0) {
				pq.add(a);
			} 
			else if (a == 0) { 
				if(pq.isEmpty()) sb.append(0 + "\n");
				else {
					int poll = pq.poll();
					sb.append(poll + "\n");
				}
			}
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}