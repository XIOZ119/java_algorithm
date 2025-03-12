import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();
	   
	   // 이분 탐색 : mid = (left + right) / 2 기준
	   
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   int K = Integer.parseInt(st.nextToken()); // 랜선 개수
	   int N = Integer.parseInt(st.nextToken()); // 만들어야 하는 갯수 
	   
	   int[] line = new int[K];
	   
	   long m = 0; 
	   
	   for(int i=0; i<K; i++) {
		   line[i] = Integer.parseInt(br.readLine());
		   
		   if(line[i] > m) m = line[i];
	   }
	   
	   long left = 1; 
	   long right = m;
	   
	   long max = 0;
	   
	   while(left <= right) {

		   int cnt = 0;

		   long mid = (left + right) / 2;
		   
		   for(int i=0; i<K; i++) {
			   cnt += line[i] / mid;
		   }
		   
		   // 만들어야 할 개수보다 적게 만들었다면, 길이를 줄여야함 ! 
		   if(cnt < N) {
			   right = mid-1;
		   } 
		   
		   else if(cnt >= N) {
			   max = Math.max(max, mid);
			   left = mid + 1;
		   }
	   }
	   
	   bw.write(max + "");
	   bw.flush();
	   bw.close();
	   
   }
}
