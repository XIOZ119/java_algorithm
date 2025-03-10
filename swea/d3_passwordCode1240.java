import java.util.*;
import java.io.*;

public class d3_passwordCode1240 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String[] pattern = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"}; 
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			String test[] = br.readLine().split(" ");
			
			int N = Integer.parseInt(test[0]);
			int M = Integer.parseInt(test[1]);
			
			int x = -1;
			int y = -1; 
			
			int[][] arr = new int[N][M];
			
			for(int i=0; i<N; i++) {

//				if(x != -1 && y != -1) {
//					break;
//				}
				
				String str = br.readLine();
				for(int j=0; j<M; j++) {
					arr[i][j] = str.charAt(j) - '0';
					if(arr[i][j] == 1) {
						x = i;
						y = j;
					}
				}
			}
			
			int[] code = new int[56];
			
			for(int i=0; i<56; i++) {
				code[55-i] = arr[x][y-i];
			}
			
			String[] codeArr = new String[8];
			
			for(int i=0; i<8; i++) {
				StringBuilder strr = new StringBuilder();
				for(int j=0; j<7; j++) {
					if(j>0) {
						strr.append(code[i*7+j]);
						codeArr[i] = strr.toString();
					}
					else {
						strr.append(code[j]);
						codeArr[i] = strr.toString();
					}
				}
			}
			
			int[] number = new int[8];
			
			for(int i=0; i<8; i++) {
				for(int j=0; j<pattern.length; j++) {
					if(codeArr[i].equals(pattern[j])) {
						number[i] = j;
					} 
				}
			}
			
			int odd = 0;
			int even = 0;
			int sum = 0;
			 
			for(int i=0; i<8; i++) {
				
				if(i%2 == 0) {
					odd += number[i];
					sum += number[i];
				} else {
					even += number[i];
					sum += number[i];
				}				
			}
			
			int result = (odd*3) + even;
			
			if(result % 10 == 0) {
				sb.append("#" + tc + " " + sum + "\n");
			} else {
				sb.append("#" + tc + " " + 0 + "\n");
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
