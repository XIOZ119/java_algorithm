import java.util.*;
import java.io.*;

public class d3_GNS1221 {
	private static int[] person;
	private static boolean[] visited;
	private static ArrayList<Integer>[] arr;
	private static int[] color;
	private static int N;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		long beforeTime = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		HashMap<String, Integer> map = new HashMap<>();
		HashMap<Integer, String> map2 = new HashMap<>();
		
		map.put("ZRO", 0);
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THR", 3);
		map.put("FOR", 4);
		map.put("FIV", 5);
		map.put("SIX", 6);
		map.put("SVN", 7);
		map.put("EGT", 8);
		map.put("NIN", 9);
		
		map2.put(0, "ZRO");
		map2.put(1, "ONE");
		map2.put(2, "TWO");
		map2.put(3, "THR");
		map2.put(4, "FOR");
		map2.put(5, "FIV");
		map2.put(6, "SIX");
		map2.put(7, "SVN");
		map2.put(8, "EGT");
		map2.put(9, "NIN");
		
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[10];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				String str = st.nextToken();
			
				int index = map.get(str);
				arr[index]++;
			}
			
			
			sb.append("#" + tc + "\n");
			for(int i=0; i<=9; i++) {
				int num = arr[i];
				if(num > 0) {
					for(int j=1; j<=num; j++) {
						sb.append(map2.get(i) + " ");
					}
				}
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

		long afterTime = System.currentTimeMillis();
		long diffTime = (afterTime - beforeTime);
		System.out.println("실행 시간(ms): " + diffTime);
	}
}