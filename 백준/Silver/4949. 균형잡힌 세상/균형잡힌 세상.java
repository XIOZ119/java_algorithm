import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = br.readLine();
			boolean flag = true;
			
			if(str.equals(".")) {
				break;
			}
			
			Stack<Integer> stack = new Stack<>();
			
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '(' ) {
					stack.push(1);
				}
				else if(str.charAt(i) == ')') {
					if(stack.size() == 0) {
						flag = false; 
						break;
					}
					int a = stack.peek();
					if(a*-1 != -1)  {
						flag = false;
						break;
					}
					stack.pop();
				}
				else if(str.charAt(i) == '[') {
					stack.push(2);
				}
				else if(str.charAt(i) == ']') {
					if(stack.size() == 0) {
						flag = false; 
						break;
					}
					int a = stack.peek();
					if(a*-1 != -2) {
						flag = false;
						break;
					}
					stack.pop();
				}
			}
			if(stack.size() != 0) flag = false;
			
			if(flag) sb.append("yes" + "\n");
			else sb.append("no" + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
