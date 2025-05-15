import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
	
	static class Person {
		int num; // 사람 번호
		int selectNum; // 선택한 일의 번호
		Long needTime; // 필요한 시간
		
		Person(int num, int selectNum, long needTime) {
			this.num = num;
			this.selectNum = selectNum;
			this.needTime = needTime;
		}
	}
	
	static int N, K; // N : 사람 수, K : 일 
	static int[] selectedCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Person> pq = new PriorityQueue<Person>(new Comparator<Person>() {
				@Override
				public int compare(Person o1, Person o2) {
					return o2.needTime.intValue() - o1.needTime.intValue();
				}
			});
		
			selectedCount = new int[K+1];
			
			String[] selectWork = br.readLine().split(" ");
			String[] need = br.readLine().split(" ");
			
			for(int j=0; j<N; j++) {
				int a = Integer.parseInt(selectWork[j]);
				long b = Long.parseLong(need[j]);
				selectedCount[a]++;
				pq.add(new Person(j+1, a, b));
			}
			
			boolean visited[] = new boolean[K+1]; // 일 확정 
			PriorityQueue<Long> timeQ = new PriorityQueue<Long>(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return Long.compare(o1,  o2);
				}
			});
			
			int count = 0;
			while(!pq.isEmpty()) {
				Person p = pq.poll();
				
				int pSelect = p.selectNum;
				if(!visited[pSelect] || selectedCount[pSelect] == 1) {
					visited[pSelect] = true;
					count++;
				} else {
					timeQ.add(p.needTime);
				}
			}

			Long sumTime = 0L;
			while(!timeQ.isEmpty()) {
				if(count >= K) break;
				count++;
				long t = timeQ.poll();
				sumTime += t;
			}
		
			sb.append("#" + tc + " " + sumTime + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
}
