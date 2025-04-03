import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	
	static List<int []> student;
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 스위치 개수
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int sNum = Integer.parseInt(br.readLine()); // 학생 수
		
		student = new ArrayList<>();

		for(int i=0; i<sNum; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			student.add(new int[] {a, b});
		}
		
		for(int i=0; i<student.size(); i++) {
			changeStatus(i); 
		}
		
		int index = 1;
		
		Loop1:
		while(true) {
			for(int i=index; i<index+20; i++) {
				if(i > N) break Loop1; 
				bw.write(arr[i] + " ");
			}
			index = index+20;
			bw.write("\n");
		}
		
		bw.flush(); 
		bw.close();
	}
	
	private static void changeStatus(int index) {
		int gen = student.get(index)[0]; // 성별
		int num = student.get(index)[1]; // 스위치 번호
		
		if(gen == 1) { // 받은 수의 배수 -> 스위치 상태 변경
			for(int i=1; i<=N; i++) {
				int j = num*i;

				if(j > N) break;
				
				
				if(arr[j] == 0) arr[j] = 1;
				else arr[j] = 0;
			}
		}
		
		if(gen == 2) { // 좌우 대칭으로 같은 수 -> 스위치 상태 변경
			if(arr[num] == 1) arr[num] = 0;
			else arr[num] = 1;
			
			for(int i=1; i<N; i++) {
				if(num-i < 1 || num+i > N) break;
				if(arr[num-i] == arr[num+i]) {
					if(arr[num-i] == 0) {
						arr[num-i] = 1;
						arr[num+i] = 1;
					} else {
						arr[num-i] = 0;
						arr[num+i] = 0;
					}
				}
				if(arr[num-i] != arr[num+i]) break;
			}
		}
	}
}
