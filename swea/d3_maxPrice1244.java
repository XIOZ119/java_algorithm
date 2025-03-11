
import java.lang.*;
import java.io.*;
import java.util.*;

public class d3_maxPrice1244 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			
			// 입력 - 숫자 배열
			StringTokenizer st =new StringTokenizer(br.readLine());
			
			String str = st.nextToken();
			int[] arr = new int[str.length()];
			
			for(int i=0; i<str.length(); i++) {
				arr[i] = str.charAt(i) - '0';
			}
			
			int exchangeCount = Integer.parseInt(st.nextToken());
			
			int count = 0;
			int index = 0;
			
			while(true) {
				if(count == exchangeCount || index == arr.length) break;
				
				int max = 0;
				int maxIndex = -1;
				int maxCount = 1;
				
				for(int i=arr.length-1; i>=index; i--) {
					if(max < arr[i]) {
						maxCount = 1;
						max = arr[i];
						maxIndex = i;
//						System.out.println("max : " + max + " maxIndex : " + maxIndex);
					}
					else if(max == arr[i]) {
						maxCount++;
					}
				}
				// 만일 max == arr[i] 이면, maxCount 갯수를 세고, index 부터 maxCount만큼 확인하며 min 값을 찾음.
				// min 값과 교환을 해 
				int min = Integer.MAX_VALUE;
				int minIndex = -1;
				
				if(maxCount > 1) {
//					System.out.println("maxCount : " + maxCount);
					for(int j=index; j<index+maxCount; j++) {
						if(min > arr[j]) {
							min = arr[j];
							minIndex = j;
//							System.out.println("min: " + min + " minIndex : " + minIndex);
						}
					}
					int temp = arr[minIndex];
					arr[minIndex] = max;
					arr[maxIndex] = temp;
					count++;
				}
				else {
					if(arr[index] < max) {
						int temp = arr[index];
						arr[index] = max;
						arr[maxIndex] = temp;
						count++;
						index++;
					}
					else if(maxIndex == index) {
						index++;
					}
				}
				
//				System.out.println("count : " + count + " index : " + index);
//				
//				for(int i=0; i<arr.length; i++) {
//					System.out.print(arr[i]);
//				}
//				System.out.println();
			}
			
			if(count != exchangeCount) {
				for(int i=0; i<exchangeCount-count; i++) {
					int temp = arr[arr.length-1];
					arr[arr.length-1] = arr[arr.length-2];
					arr[arr.length-2] = temp;
				}
			}
			
			sb.append("#" + tc + " ");
			for(int i=0; i<arr.length; i++) {
				sb.append(arr[i]);
			}
			sb.append("\n");
			
			
			// 배열에서 가장 큰 숫자 - index 찾기 
			
			// index 0 과 가장 큰 숫자 비교했을 때 index 0 이 작다면 위치 교환 
			
			// 횟수 증가 
			// 만일 횟수가 도달했다면 배열 출력 
			
			// 아닐 경우, index 0을 제외하고 큰 숫자 찾고, 반복
			
			// while(true) 
			// if count == -> break;
			// for i = count i<배열크기 i++ 
			// int max = 0;	
			// int maxIndex = -1; 
			// 크기 비교 및 저장 
			// if(max < arr[i]) max = arr[i] , maxIndex = -1;  
			// if arr[count] < max => 
			// int temp = arr[count]
			// arr[count] = max
			// arr[maxIndex] = arr[count]
			// count++;
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
