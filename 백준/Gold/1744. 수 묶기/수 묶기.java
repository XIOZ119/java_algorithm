import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		PriorityQueue<Integer> minusPq = new PriorityQueue<>();
		int result = 0;
		
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if(input > 1) {
				pq.add(input);
			} else if (input == 1) {
				result += 1;
			} else {
				minusPq.add(input);
			}
		}
		
		while(!pq.isEmpty()) {
			if(pq.size() > 1) {
				int a = pq.poll(); 
				int b = pq.poll(); 
				int mul = a*b;
				
				result += mul;
			} else if (pq.size() == 1) {
				result += pq.poll();
			}
		}
		
		while(!minusPq.isEmpty()) {
			if(minusPq.size() > 1) {
				int a = minusPq.poll(); 
				int b = minusPq.poll(); 
				int mul = a*b;
				
				result += mul;
			} else if (minusPq.size() == 1) {
				result += minusPq.poll();
			}
		}
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}
}