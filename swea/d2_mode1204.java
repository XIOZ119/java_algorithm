package swea;

import java.io.*;

public class d2_mode1204 {
	 public static void main(String[] args) throws IOException {
	        // SW 배열 1 - (D2) 최빈수 구하기 
	        // 가장 여러 번 나타나는 값 -> 학생들의 평균 수준 짐작 
	        // 단, 최빈수가 여러 개일 때는 가장 큰 점수 출력 
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        // 입력 받기 
	        int testCase = Integer.parseInt(br.readLine());

	        // 100 개의 배열(점수), 학생 수 1000명
	        // 점수 = 배열의 인덱스 ( 나올 때마다 +1 ) 
	        for (int i=0; i<testCase; i++){
	            int testCaseNum = Integer.parseInt(br.readLine());

	            String[] arr = br.readLine().split(" ");
	            int[] scoreArr = new int[101];
	            
	            for(int j=0; j<1000; j++) {
	               scoreArr[Integer.parseInt(arr[j])] += 1;  
	            }

	            int max = 0; // 배열 안의 갯수 비교 변수
	            int result = 0; // 배열 인덱스 리턴
	            for (int j=0; j<=100; j++) {
	                max = Math.max(scoreArr[j], max);
	                if(scoreArr[j] == max) {
	                    result = j;
	                }
	            }

	            bw.write("#" + testCaseNum + " " + result);
	            bw.write("\n");
	        }   
	        bw.flush();
	        bw.close();
	    }
}
