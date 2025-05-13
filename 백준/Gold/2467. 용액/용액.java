import java.io.*;
import java.util.*;

public class Main {
	
	/* [백준] 2467. 용액 
	 * 
	 */
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		Long[] arr = new Long[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		
		Long mix = Long.MAX_VALUE;
		
		Long resultMin = 0L;
		Long resultMax = 0L;
		
		while(true) {
			
			if(left >= right) break;
			if(mix == 0) break;
			
			long l = arr[left];
			long r = arr[right]; 
			
			if(l + r > 0) {
				right--;
			} else if(l + r < 0) {
				left++;
			}
			
			if(Math.abs(l+r) < mix) {
				resultMin = l;
				resultMax = r;
				mix = Math.abs(l + r);
			}
			
		}
		
		if(resultMin == 0 && resultMax == 0) {
			bw.write(arr[left] + " " + arr[right]);
		} else {
			bw.write(resultMin + " " + resultMax);
		}
		bw.flush();
		bw.close();
	}
	

}