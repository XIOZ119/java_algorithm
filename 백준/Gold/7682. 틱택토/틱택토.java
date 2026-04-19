import java.io.*;

public class Main {
    /*
    XXXOO.XXX
    XOXOXOXOX
    OXOXOXOXO
    XXOOOXXOX
    XO.OX...X
    .XXX.XOOO
    X.OO..X..
    OOXXXOOXO
    end
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        while(!str.equals("end")){
            char[][] arr = new char[3][3];

            int xCount = 0;
            int oCount = 0;
            int emptyCount = 0;
            int count = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    char c = str.charAt(count);
                    if(c == 'X') xCount++;
                    else if(c == 'O') oCount++;
                    else emptyCount++;

                    arr[i][j] = c;
                    count++;
                }
            }
            str = br.readLine();

            if(xCount > oCount + 1 || xCount < oCount) {
                // 최종상태가 아닌 경우
                sb.append("invalid \n");
                continue;
            }

            // 최종상태일 경우
            boolean flag = true;
            char ans = '.';
            if(arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && flag && arr[0][0] != '.') {
                char check = arr[0][0];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && flag && arr[0][2] != '.') {
                char check = arr[0][2];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[0][0] == arr[0][1] && arr[0][1] == arr[0][2] && flag && arr[0][0] != '.') {
                char check = arr[0][0];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[1][0] == arr[1][1] && arr[1][1] == arr[1][2] && flag && arr[1][0] != '.') {
                char check = arr[1][0];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[2][0] == arr[2][1] && arr[2][1] == arr[2][2] && flag && arr[2][0] != '.') {
                char check = arr[2][0];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[0][0] == arr[1][0] && arr[1][0] == arr[2][0] && flag && arr[0][0] != '.') {
                char check = arr[0][0];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[0][1] == arr[1][1] && arr[1][1] == arr[2][1] && flag && arr[0][1] != '.') {
                char check = arr[0][1];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }
            if(arr[0][2] == arr[1][2] && arr[1][2] == arr[2][2] && flag && arr[0][2] != '.') {
                char check = arr[0][2];
                if(ans != '.' && ans != check) flag = false;
                else ans = check;
            }

            if(!flag) sb.append("invalid \n");
            else if(ans == 'O') {
                if(xCount == oCount) sb.append("valid \n");
                else  sb.append("invalid \n");
            }
            else if(ans == 'X') {
                if(xCount > oCount) sb.append("valid \n");
                else sb.append("invalid \n");
            }
            else if(emptyCount == 0) sb.append("valid \n");
            else sb.append("invalid \n");
        }

        System.out.println(sb.toString());
    }
}