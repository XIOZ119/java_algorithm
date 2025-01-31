package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _1546 {
	
	/* 
	 * 1. 입력 받은 수를 새로운 수로 계산하여 추가
	 * 2. 추가한 후, 평균 구하기 
	 */

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		String[] strArr = br.readLine().split(" ");
			
		double max = Integer.MIN_VALUE;
		double sum = 0;
		
		// max 찾기 
		for ( int i = 0; i < count; i++ ) {
			max = Math.max(max, Integer.parseInt(strArr[i]));
		}
		
		// 새로운 값으로 바꿔 더하기
		for( int i = 0; i < count; i++ ) {
			sum += Integer.parseInt(strArr[i]) / max * 100;
		}
		
		// 평균 구하기 
		double result = sum / count;
		System.out.println(result);
	}

}
