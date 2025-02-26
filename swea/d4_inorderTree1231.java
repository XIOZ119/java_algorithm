package swea;

import java.io.*;

public class d4_inorderTree1231 {
	private static String[] alpha;
	private static int N;
	private static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			N = Integer.parseInt(br.readLine());
			alpha = new String[N+1];
			
			for(int t=1; t<N+1; t++) {
				String[] str = br.readLine().split(" ");
				alpha[t] = str[1]; 
			}
			
			bw.write("#" + testCase +" ");
			inorderTraversal(1);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void inorderTraversal(int node) throws IOException {
		if(node <= N) {
			inorderTraversal(node*2);
			bw.write(alpha[node]);
			inorderTraversal(node*2+1);
		}
	} 
}
