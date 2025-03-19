import java.util.*;
import java.io.*;

public class Main {
	
	static class Problem{
		int p; //문제번호 
		int l; //난이도 
		
		public Problem(int p, int l) {
			this.p = p;
			this.l = l;
		}
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        // 난이도별 정렬을 위한 TreeSet : 난이도(오름차순) 같을 경우 문제번호 오름차순 
        TreeSet<Problem> set = new TreeSet<>(new Comparator<Problem>() {
			@Override
			public int compare(Problem o1, Problem o2) {
				if(o1.l == o2.l) {
					return o1.p - o2.p;
				}
				return o1.l - o2.l;
			}
        	
        });
        
        // 빠른 검색과 삭제를 위한 HashMap ( TreeSet 의 경우, 이진 탐색으로 O(logN), HashMap - O(1) )
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int problemNum = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
        
            Problem problem = new Problem(problemNum, level);
            set.add(problem); 
            map.put(problemNum, level);
        }
        
        int M = Integer.parseInt(br.readLine());
        
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            String command = st.nextToken();
            
            if(command.equals("add")) {
                int problemNum = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                
                Problem problem = new Problem(problemNum, level);
                set.add(problem); 
                map.put(problemNum, level);
            }
            
            else if(command.equals("recommend")) {
                int num = Integer.parseInt(st.nextToken());
                
                if(num == 1) {
                	// TreeSet의 마지막 값 중 p(문제번호) 가져오기 
                    sb.append(set.last().p+ "\n");
                }
                else if (num == -1) {
                	// TreeSet의 첫번째 값 중 p(문제번호) 가져오기
                    sb.append(set.first().p + "\n");
                }
                
            }
            
            else if(command.equals("solved")){
                int problemNum = Integer.parseInt(st.nextToken());
                int level = map.get(problemNum);
                
                set.remove(new Problem(problemNum, level));
                map.remove(problemNum);
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}