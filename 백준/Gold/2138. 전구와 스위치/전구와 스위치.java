import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	
	/* [백준] 2138. 전구와 스위치 
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int minResult = Integer.MAX_VALUE;
		
		boolean[] select = new boolean[N]; 
		boolean[] notSelect = new boolean[N];
		
		int[] last = new int[N];
		
		String[] first = br.readLine().split("");
		String[] end = br.readLine().split("");
		
		// 1 : true, 0 : false
		for(int i=0; i<N; i++) {
			if(first[i].equals("1")) {
				select[i] = true;
				notSelect[i] = true;
			} else {
				select[i] = false;
				notSelect[i] = false;
			}
			last[i] = Integer.parseInt(end[i]);
		}
		
		int selectCnt = 1;
		int notSelecteCnt = 0;
		
		// 1번 전구 선택했을 때 
		select[0] = !select[0];
		if(N > 0) {
			select[1] = !select[1];
		}
	
		for(int i=1; i<N; i++) {
			if((select[i-1] == true && last[i-1] != 1) || (select[i-1] == false && last[i-1] != 0)) {
				select[i] = !select[i];
				select[i-1] = !select[i-1];
				if(N > i+1) {
					select[i+1] = !select[i+1];
				}
				selectCnt++;
			}
			if(i == N-1) {
				if((select[i] == true && last[i] == 1) || (select[i] == false && last[i] == 0)) {
					minResult = Math.min(minResult, selectCnt);
				}
			}
		}
		
		// 1번 전구 선택하지 않았을 때
		for(int i=1; i<N; i++) {
			if((notSelect[i-1] == true && last[i-1] != 1) || (notSelect[i-1] == false && last[i-1] != 0)) {
				notSelect[i] = !notSelect[i];
				notSelect[i-1] = !notSelect[i-1];
				if(N > i+1) {
					notSelect[i+1] = !notSelect[i+1];
				}
				
				notSelecteCnt++;
			}
			if(i == N-1) {
				if((notSelect[i] == true && last[i] == 1) || (notSelect[i] == false && last[i] == 0)) {
					minResult = Math.min(minResult, notSelecteCnt);
				}
			}
		}
		
		if(minResult == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(minResult + "");
		}
		bw.flush();
		bw.close();
	}
}