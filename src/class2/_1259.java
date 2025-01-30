package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			String str = br.readLine();
			int[] numArr = new int[str.length()];
			
			for(int i=0; i<str.length(); i++) {
				numArr[i] = str.charAt(i) - '0';
			}
			
			if(numArr[0] == 0) {
				break;
			}
			
			int loopCount = numArr.length / 2;
			
			int max = numArr.length - 1;
			int min = 0;
			boolean result = true;
			
			for(int i=0; i < loopCount; i++) {
				if(numArr[min] != numArr[max]) {
					System.out.println("no");
					result = false;
					break;
				}
				max--;
				min++;
			}
			
			if(result == true) {
				System.out.println("yes");
			}
		}
	}
}
