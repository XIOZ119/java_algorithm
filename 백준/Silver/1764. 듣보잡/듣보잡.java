import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();

	   StringTokenizer st = new StringTokenizer(br.readLine());
	   
	   int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람 수
	   int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람 수
	   
	   HashSet<String> set = new HashSet<>();
	   ArrayList<String> list = new ArrayList<>();
	   
	   for(int i=0; i<N+M; i++) {
		   String str = br.readLine();
		   
		   if(set.contains(str)) {
			   list.add(str);
		   }
		   
		   set.add(str);
	   }
	   
	   Collections.sort(list);
	   
	   bw.write(list.size()+"\n");
	   
	   for(String s: list) {
		   bw.write(s + "\n");
	   }
	   
	   bw.flush();
	   bw.close();
	   
   }
}
