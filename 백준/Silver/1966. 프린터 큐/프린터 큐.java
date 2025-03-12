import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();
	   
	   int testCase = Integer.parseInt(br.readLine());
	   
	   for(int tc=1; tc<=testCase; tc++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   
		   int N = Integer.parseInt(st.nextToken());
		   int M = Integer.parseInt(st.nextToken());
		   
		   Queue<Integer> queue = new LinkedList<>(); // 중요도 
		   Queue<Integer> location = new LinkedList<>(); // 위치 

		   st = new StringTokenizer(br.readLine());
		   
		   for(int i=0; i<N; i++) {
			   location.add(i);
		   }
		   
		   for(int i=0; i<N; i++) {
			   int a = Integer.parseInt(st.nextToken());
			   
			   queue.add(a);
		   }
		   
		   int count = 0;
		   
		   while(true) {
			   
			   int top = queue.poll();
			   int t = location.poll();
			   
			   boolean flag = true;
			   
			   for(int c: queue) {
				   if( top < c ) flag = false;
			   }
			   
			   if(!flag) {
				   queue.add(top);
				   location.add(t);
			   } else {
				   if(t == M) {
					   count++;
					   break;
				   } else {
					   count++;
				   }
			   }
		   }
		   sb.append(count + "\n");
	   }
	   
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	   
   }
}
