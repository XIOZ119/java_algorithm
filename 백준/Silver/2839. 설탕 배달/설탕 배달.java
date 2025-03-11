import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int count = -1;
		
		int five = N/5;
		int fiveLeft = N%5;
		
		if(fiveLeft == 0) count = five;
		else {
 			for(int i=five; i>0; i--) {
 				int l = i;
				int left = N - (5*i);
				
				if(left%3 == 0) {
					count = l + (left/3);
					break;
				}
			}
 			if(count == -1) {
 				if(N%3 == 0) count = N/3;
 			}
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}
}
