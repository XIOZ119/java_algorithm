package class2;

import java.io.*;

public class _15829 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int r = 31;
		int M = 1234567891;
		
		long nextMidMul = 1;
		long result = 0;
		
		for (int i=0; i<L; i++) {
			if (i == 0) {
				nextMidMul = 1;
			} else {
				nextMidMul = nextMidMul * r % M;				
			}
			
			int strNum = str.charAt(i) - 'a' + 1;
			
			long re = strNum * nextMidMul;
			
			result = (result+re) % M;
		}
		 System.out.println(result);
	}

}
