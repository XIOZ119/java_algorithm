import java.io.*;

class Main {
	static int N, totalPrice;
	static int[] T; 
	static int[] P;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		T = new int[N];
		P = new int[N];
		
		for(int i=0; i<N; i++) {
			String[] arr = br.readLine().split(" ");
			T[i] = Integer.parseInt(arr[0]);
			P[i] = Integer.parseInt(arr[1]);
		}
		
		totalPrice = 0;
		dfs(0, 0);

		bw.write(totalPrice + " ");
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int index, int price) {
		
		if(index >= N) {
			totalPrice = Math.max(totalPrice, price);
			return;
		}
		
		if(index + T[index] <= N) {
			dfs(index + T[index], price + P[index]);
		}
		
		// 선택하지 않은 경우 다음 날로 넘어감 
		dfs(index + 1, price);
	}
}