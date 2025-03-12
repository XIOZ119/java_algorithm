import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();
	   
	   int M = Integer.parseInt(br.readLine());
	   ArrayList<String> list = new ArrayList<>();
	   
	   for(int i=0; i<M; i++) {
		   String[] arr = br.readLine().split(" ");
		   
		   if(arr[0].equals("add")) {
			   if(!list.contains(arr[1])) list.add(arr[1]);
		   }
		   else if(arr[0].equals("check")) {
			   if(list.contains(arr[1])) sb.append(1 + "\n");
			   else sb.append(0 + "\n");
		   }
		   else if(arr[0].equals("remove")) {
			   if(list.contains(arr[1])) list.remove(arr[1]);
		   }
		   else if(arr[0].equals("toggle")) {
			   if(list.contains(arr[1])) list.remove(arr[1]);
			   else list.add(arr[1]);
		   }
		   else if(arr[0].equals("all")) {
			   list.clear();
			   for(int j=1; j<=20; j++) {
				   list.add(j + "");
			   }
		   }
		   else if(arr[0].equals("empty")) {
			   list.clear();
		   }
		   
	   }
	   
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	   
   }
}
