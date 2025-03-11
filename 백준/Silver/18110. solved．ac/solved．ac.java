import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n]; 
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		double a = (double) n*15/100;
		int percent = (int) Math.round(a);
		
		int sum = 0;
		
		for(int i=percent; i<arr.length-percent; i++) {
			sum += arr[i];
		}
		
		int person = n-(percent*2);
		
		int result = (int) Math.round((double) sum/person);
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}
}


