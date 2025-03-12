import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();
	   
	   int n = Integer.parseInt(br.readLine());
	   
	   int[] arr = new int[n];
	   
	   for(int i=0; i<n; i++) {
		   int input = Integer.parseInt(br.readLine());
		   arr[i] = input;
	   }
	   
	   int num = 1;
	   
	   Stack<Integer> stack = new Stack<>();
	   List<Character> list = new ArrayList<>(); 
	   
	   boolean flag = true;
	   
	   for(int i=0; i<n; i++) {
		   int current = arr[i];
		   
		   if(current > num) {
			   for(int j=num; j<=current; j++) {
				   stack.push(j);
				   list.add('+');
			   }
			   stack.pop();
			   list.add('-');
			   num = current+1;
		   }
		   else if(current < num) {
			   if(!stack.isEmpty()) {
				   int pk = stack.peek();
				   if(pk == current) {
					   stack.pop();
					   list.add('-');
				   }
				   else {
					   flag = false;
					   break;
				   }
			   }
		   }
		   else {
			   list.add('+');
			   list.add('-');
			   num++;
		   }
	   }
	   
	   if(!flag) bw.write("NO");
	   else {
		   for(char l: list) {
			   bw.write(l + "\n");
		   }
	   }
	   
	   bw.flush();
	   bw.close();
	   
   }
}
