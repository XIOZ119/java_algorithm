import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			
			if(str[0].equals("push")) {
				stack.add(Integer.parseInt(str[1]));
			}
			else if(str[0].equals("top")) {
				if(stack.size() == 0) {
					sb.append(-1 + "\n");
				}
				else {
					sb.append(stack.peek() + "\n");
				}
			}
			else if(str[0].equals("size")) {
				sb.append(stack.size() + "\n");
			}
			else if(str[0].equals("empty")) {
				if(stack.size() == 0) sb.append(1 + "\n");
				else sb.append(0 + "\n");
			}
			else if(str[0].equals("pop")) {
				if(stack.size() == 0) {
					sb.append(-1 + "\n");
				}
				else {
					int a = stack.pop();
					sb.append(a + "\n");
				}
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}