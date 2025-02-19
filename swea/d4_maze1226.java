import java.io.*;

public class d4_maze1226 {
	private static int[][] inputArr;
    private static int result;
    private static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int testCase = 1; testCase <=10; testCase++) {
            br.readLine();
            
            inputArr = new int[16][16];
            result = 0;
            visited = new boolean[16][16];
            
            for(int i=0; i<16; i++) {
                String row = br.readLine();
                for(int j=0; j<16; j++) {
                    inputArr[i][j] = row.charAt(j) - '0';
                }
            }

            dfs(1, 1);

            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int x, int y){
        int[] dx = {-1, 1, 0, 0}; // 좌, 우, 상, 하
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;

        for(int i=0; i<4; i++) {

            int moveX = x + dx[i];
            int moveY = y + dy[i];

            if(moveX > 0 && moveY > 0) {
                if(inputArr[moveX][moveY] == 0 && visited[moveX][moveY] != true) {
                    dfs(moveX, moveY);
                }
                if(inputArr[moveX][moveY] == 3) {
                    result = 1;
                    break;
                }
            }
        }
    } 
}
