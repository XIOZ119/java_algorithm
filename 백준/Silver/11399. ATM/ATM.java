import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	private static ArrayList<Integer>[] graph;
	private static int[] degree;
	private static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();

	   int N = Integer.parseInt(br.readLine()); // 사람 수 
	   int[] arr = new int[N];
	   int[] sumArr = new int[N];
	   
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   
	   for(int i=0; i<N; i++) {
		   arr[i] = Integer.parseInt(st.nextToken());
	   }
	   
	   Arrays.sort(arr);
	   
	   int sum = 0;
	   
	   for(int i=0; i<N; i++) {
		  sum += arr[i];
		  sumArr[i] = sum;
	   }
	   
	   int result = 0;
	   for(int i=0; i<N; i++) {
		   result += sumArr[i];
	   }
	   
	   bw.write(result + "");
	   bw.flush();
	   bw.close();
	}
}
