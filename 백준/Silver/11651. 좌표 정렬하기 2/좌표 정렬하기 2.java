import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		ArrayList<int[]> arr = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr.add(new int[] {x, y});
		}
		
		Collections.sort(arr, (a, b) -> {
			if(a[1] == b[1]) return Integer.compare(a[0], b[0]);
			
			return Integer.compare(a[1], b[1]);
		});
		
		for(int i=0; i<arr.size(); i++) {
			int[] r = arr.get(i);
			
			bw.write(r[0] + " " + r[1] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
