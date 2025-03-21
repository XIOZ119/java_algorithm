import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		long[] a = new long[1001];
		
		a[0] = 1; a[1] = 3;
		
		int N = Integer.parseInt(br.readLine());
		
		if(N > 1) {
			for(int i=2; i<N; i++) {
				a[i] = (a[i-2] * 2 + a[i-1]) % 10007;
			}
		}

		bw.write(a[N-1] + "");
		bw.flush();
		bw.close();
	}
} 