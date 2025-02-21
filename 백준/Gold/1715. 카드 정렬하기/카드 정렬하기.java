import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); 
		
		for(int i=0; i<N; i++) {
			int card = Integer.parseInt(br.readLine());
			
			pq.add(card);
		}
		
		int result = 0;
		int addAB = 0;
		while(true) {
			if(pq.size() == 1) {
				result = 0;
				break;
			}
			
			int a = pq.poll();
			int b = pq.poll();
			addAB = a+b;
			result += addAB;
			
			if(pq.isEmpty()) {
				break;
			}
			
			pq.add(addAB);
		}
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}

}