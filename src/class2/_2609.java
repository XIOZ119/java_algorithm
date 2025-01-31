package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2609 {
	
	/*
	 * 1. 최대공약수(GCD) : 유클리도 호제법 
	 * 		GCD(a, b) = GCD(b, a%b). a%b == 0 일 때까지 반복
	 * 2. 최소공배수(LCM) : 최대공약수 이용
	 * 		LCM(a, b) = a * b / GCD(a, b)
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arr = br.readLine().split(" ");
		int a = Integer.parseInt(arr[0]);
		int b = Integer.parseInt(arr[1]);
		
		int gcd = 0;
		int lcm = a * b;
		
		// 최대공약수 구하기
		while (b != 0) {
			if( a % b != 0 ) {
				int c = a % b;
				a = b;
				b = c;
			} else {
				gcd = b;
				break;
			}
		}
		
		// 최소공배수 구하기
		lcm = lcm / gcd;
		
		System.out.println(gcd);
		System.out.println(lcm);

	}

}
