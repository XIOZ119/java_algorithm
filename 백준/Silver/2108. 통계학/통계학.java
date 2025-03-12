import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
   public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();
	   
	   int N = Integer.parseInt(br.readLine());
	   int[] arr = new int[N];
	   
	   for(int i=0; i<N; i++) {
		   int n = Integer.parseInt(br.readLine());
		   
		   arr[i] = n;
	   }
	   
	   //산술평균
	   int sum = 0;
	   for(int i=0; i<N; i++) {
		   sum += arr[i];
	   }
	   double avg = Math.round((double) sum / N);
	   
	   sb.append((int)avg + "\n");
	   
	   //중앙값 
	   Arrays.sort(arr);
	   int middle = N/2;
	   
	   int mResult = arr[middle];
	   
	   sb.append(mResult + "\n");
	   
	   //최빈값 중 두번째로 작은 값 
	   int cnt = 1;
	   
	   int[] number = new int[N];
	   int[] countt = new int[N];
	   List<Integer> climb = new ArrayList<>();
	   
	   for(int i=0; i<N; i++) {
		   number[i] = 5000;
		   countt[i] = 5000;
	   }
	   
	   if(arr.length > 1) {
		   for(int j=0; j<N-1; j++) {
			   cnt = 0;
			   for(int i=j; i<N; i++) {
				   if(arr[j] == arr[i]) {
					   if(i == N-1) {
						   cnt++;
						   number[i] = arr[j];
						   countt[i] = cnt;
					   } else {
						   cnt++;
					   }
				   }
				   else {
					   number[i] = arr[j];
					   countt[i] = cnt;
					   break;
				   }
			   }
			   j += cnt-1;
		   }
		   
		   for(int i=0; i<N; i++) {
			   if(number[i] != 5000) {
				   climb.add(countt[i]);
			   }
		   }
		   
		   Collections.sort(climb, Collections.reverseOrder());
		   int b = climb.get(0);
		   int re = 5000;
		   int cc = 0;
		   List<Integer> choi = new ArrayList<>();
		   
		   for(int i=0; i<N; i++) {
			   if(countt[i] == b) {
				   cc++;
				   re = number[i];
				   choi.add(re);
			   }
		   }
		   
		   if(cc != 1) {
			   Collections.sort(choi);
			   re = choi.get(1);
		   }
		   
		   sb.append(re + "\n");
		   
	   } else {
		   sb.append(arr[0] + "\n");
	   }
	   
	   
	   
	   // 범위 
	   int min = arr[0]; 
	   int max = arr[N-1];
	   
	   int arange = max - min;
	   
	   sb.append(arange + "\n");
	   
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	   
   }
}
