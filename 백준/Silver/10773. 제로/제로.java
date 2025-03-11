import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int K = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<K; i++) {
			int a = Integer.parseInt(br.readLine());
			
			if(a == 0) stack.pop();
			else stack.push(a);
		}
		
		if(stack.isEmpty()) {
			bw.write(0 + "");
		}
		else {
			int sum = 0;
			
			while(!stack.isEmpty()) {
				int a = stack.peek(); 
				sum += a;
				stack.pop();
			}

			bw.write(sum + "");
		}
		
		bw.flush();
		bw.close();
	}
}


