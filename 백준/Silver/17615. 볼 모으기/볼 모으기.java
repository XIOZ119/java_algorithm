import java.io.*;

public class Main {
    static int N;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        int answer = Integer.MAX_VALUE;
        answer = Math.min(check('R', 0), check('R', N-1));
        answer = Math.min(answer, check('B', 0));
        answer = Math.min(answer, check('B', N-1));

        System.out.println(answer);
    }

    static int check(char color, int loc){

        boolean flag = true;
        int answer = 0;
        if(loc == 0) {
            for(int i=0; i<N; i++){
                if(flag && arr[i] != color) flag = false;
                if(!flag && arr[i] == color) answer++;
            }
        } else {
            for(int i=loc; i>=0; i--) {
                if(flag && arr[i] != color) flag = false;
                if(!flag && arr[i] == color) answer++;
            }
        }

        return answer;
    }
}