import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
	
	// R(뒤집기), D(버리기)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<testCase; tc++) {
			String command = br.readLine();
			
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			String cleaned = input.replace("[", "")
                    .replace("]", "")
                    .trim();
			String[] parts = cleaned.split(",");
			
			int left = 0;
			int right = parts.length - 1; 
			
			int direction = 1; // 음수 -> 뒤집힌 상태
			boolean flag = true;
			
			for(int i=0; i<command.length(); i++) {
				char c = command.charAt(i);
				if(c == 'D') {
					if(left > right || parts[0] == "") {
						flag = false;
						break;
					}
					if(direction > 0) {
						left++;
					} else {
						right--;
					}
				}
				else if(c == 'R') {
					direction *= -1;
				}
			}
			
			StringBuilder result = new StringBuilder("[");
//			String result = "[";
			
			if(!flag) {
				result = new StringBuilder("error");
			} else {
//				System.out.println(left + " " + right + " " + direction);
				if(direction > 0) {
					for(int i=left; i<=right; i++) {
						result.append(parts[i]);
						if(i != right) {
							result.append(",");
						}
					}
				}
				else {
					for(int i=right; i>=left; i--) {
						result.append(parts[i]);
						if(i != left) {
							result.append(",");
						}
					}
				}
				result.append("]");
			}
			
			bw.write(result + "\n");
		}

		bw.flush();
		bw.close();
	}
}