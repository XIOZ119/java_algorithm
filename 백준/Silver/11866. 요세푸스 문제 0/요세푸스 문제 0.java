import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		ArrayList<Integer> result = new ArrayList<>();

		int count = 0;
		while(!queue.isEmpty()) {
			count++;
			
			int poll = queue.poll();

			if(queue.isEmpty()) {
				result.add(poll);
				break;
			}
			
			if(count%M != 0) {
				queue.add(poll);
			} else {
				result.add(poll);
			}
		}
		
		bw.write("<");
		for(int i=0; i<result.size(); i++) {
			int poll = result.get(i);
			if(i == N-1) {
				bw.write(poll + "");
			} else {
				bw.write(poll + ", ");
			}
		}
		bw.write(">");
		
		bw.flush();
		bw.close();
	}
	
}
