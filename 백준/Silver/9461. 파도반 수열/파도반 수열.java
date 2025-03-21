import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());

		long[] a = new long[101];
		
		a[0] = 1; a[1] = 1; a[2] = 1;
		
		for(int i=3; i<=100; i++) {
			a[i] = a[i-3] + a[i-2];
		}
		
		for(int tc=0; tc<testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			sb.append(a[N-1] + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
} 