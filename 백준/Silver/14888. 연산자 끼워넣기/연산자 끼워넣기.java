import java.util.*;
import java.io.*;

public class Main {
	static int[] operation = new int[4];
	static int[] number;
	static char[] Soperation = {'+', '-', '*', '/'};
	static int N;
	static int max, min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 수의 개수 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		number = new int[N];
		
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<4; i++) {
			operation[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		dfs(number[0], 0);
		
		bw.write(max + "\n" + min);
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int re, int num) {
		if(num == N-1) {
			max = Math.max(max, re);
			min = Math.min(min, re);
			return;
		}
		
		for(int i=0; i<4; i++) {

			if(operation[i] != 0) {
				operation[i]--; 
				
				int cal;
				cal = calculation(re, Soperation[i], number[num+1]);
				
				dfs(cal, (num+1));
				operation[i]++;
			}
		}
	}
	
	private static int calculation(int a, char operation, int b) {
		if(operation == '+') return a + b;
		else if(operation == '-') return a - b;
		else if(operation == '*') return a * b;
		else if(operation == '/') return a / b;
		
		return 0;
	}
}