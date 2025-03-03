import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String s;
		
		while((s = br.readLine()) != null && !s.equals("")) {
			sb.append(s);
		}
		
		String[] arr = sb.toString().split(",");
		
		int sum = 0;
		for(String a: arr) {
			sum += Integer.parseInt(a);
		}
		
		bw.write(sum + "");
		bw.flush();
		bw.close();
	}
}