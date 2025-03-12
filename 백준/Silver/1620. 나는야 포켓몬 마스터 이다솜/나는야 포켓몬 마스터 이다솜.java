import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();

	   // HashMap 사용
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   
	   int N = Integer.parseInt(st.nextToken());
	   int M = Integer.parseInt(st.nextToken());
	   
	   HashMap<String, Integer> map = new HashMap<>();
	   HashMap<Integer, String> reverseMap = new HashMap<>();
	   
	   for(int i=1; i<N+1; i++) {
		   String str = br.readLine();
		   map.put(str, i);
		   reverseMap.put(i, str);
	   }
	   
	   for(int i=0; i<M; i++) { 
		   String str = br.readLine();
		   
		   boolean flag = true;
		   for(char c: str.toCharArray()) {
			   if(!Character.isDigit(c)) {
				   flag = false;
				   break;
			   }
		   }
		   
		   if(flag) {
			   int index = Integer.parseInt(str);
			   sb.append(reverseMap.get(index) + "\n");
		   }
		   else {
			   sb.append(map.get(str) + "\n");
		   }
	   
	   }
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	   
   }
}
