import java.io.*;
import java.util.*;

public class Main {
    
    // 공격 <-> 수비 
    // N 이닝 게임 진행, 한 이닝에 3아웃 발생 -> 종료 -> 공격 수비 변경 
    // 타순(타자가 타석에 서는 순서) 변경 불가 
    // 공격 팀 선수 1루 -> 2루 -> 3루 -> 홈 : 1점 득점
    // 이닝 시작 시 주자 X 
    // 타자가 공을 쳐서 얻을 수 있는 결과 
        // 안타 : 타자와 모든 주자 한 루씩 진루 (1)
        // 2루타 :             두 루씩 진루 (2) 
        // 3루타 :             세 루씩 진루 (3)
        // 홈런 :             홈까지 진루 (4)
        // 아웃 :             진루 X, 공격 팀에 아웃 하나 증가 (0)
    // 1번 선수 : 4번 타자
    
    // 짝수번째 이닝 or 홀수번째 이닝 선택 
    // 순열
    
    /* 
     *  2 ( 이닝 수 )
     * (1)(2)(3)(4)(5)(6)(7)(8)(9)
    (1) 4  3  2  1  0  4  3  2  1
    (2) 1  2  3  4  1  2  3  4  0
    
    1 1 1 4
    
    2 1 0 0
    3 2 1 0
    7 6 5 4 => 4점 
 */
    static int[][] arr;
    static int N;
    static boolean[] visited;
    static int maxScore;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 입력
        N = Integer.parseInt(br.readLine());
        arr = new int[N][9];
        visited = new boolean[9];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        maxScore = 0;
        // 순열
        perm(visited, new ArrayList<Integer>());
        
        bw.write(maxScore + "");
        bw.flush();
        bw.close();
    }
    
    static void goGame(ArrayList<Integer> selected, int inning) {
        int nextInning = 0;
        int start = 0;
        int out = 0;
        int score = 0;
        boolean[] runner = new boolean[3]; // 1루, 2루, 3루
    
        while(true) {
            // 종료 조건
            if(nextInning >= N){
                maxScore = Math.max(maxScore, score); 
                break;
            }
            
            if(start == 9) {
                start = 0;
            }
        
            // 현재 타자 
            int cur = selected.get(start);

            // 타자가 쳤을 때
            int hit = arr[nextInning][cur];
            
            switch (hit) {
            case 0:
                out++;
                break;
            case 4:
                for(int i=0; i<3; i++) {
                    if(runner[i]) {
                        score++;
                        runner[i] = false;
                    }
                }
                score++;
                break;
            case 3:
                for(int i=0; i<3; i++) {
                    if(runner[i]){
                        score++;
                        runner[i] = false;
                    }
                }
                runner[2] = true;
                break;
            case 2:
                for(int i=2; i>=0; i--) {
                    if(runner[i]){
                        if(i+2 > 2) {
                            score++;
                            runner[i] = false;
                        } else{
                            runner[i] = false;
                            runner[i+2] = true;
                        }
                    }
                }
                runner[1] = true;
                break;
            default:
                for(int i=2; i>=0; i--) {
                    if(runner[i]){
                        if(i+1 > 2) {
                            score++;
                            runner[i] = false;
                        } else{
                            runner[i] = false;
                            runner[i+1] = true;
                        }
                    }
                }
                runner[0] = true;
                break;
            }   
            // 아웃 3번일 때는 다음 턴
            if(out == 3) {
                nextInning += 1;
                Arrays.fill(runner, false);
                out = 0;
            }
            start++;
        }
    }
    
    static void perm(boolean[] visited, ArrayList<Integer> selected) {
        // 1번 선수는 4번 타자로 지정
        if(selected.size() == 9 && selected.get(3) == 0) {
            goGame(selected, 1);
            goGame(selected, 2);

            return;
        }
        
        for(int i=0; i<9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected.add(i);

                perm(visited, selected);
                
                visited[i] = false;
                selected.remove(selected.size() - 1);
            }
        }
    }
}