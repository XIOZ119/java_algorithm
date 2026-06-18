import java.util.*;
import java.io.*;

public class Main {
    // A ~ Z (65 ~ 90)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String msg = br.readLine();
        String key = br.readLine();

        boolean[] flag = new boolean[91];
        char[][] arr = new char[5][5];

        // key -> 5x5 표 변환
        int row = 0; int col = 0;
        for(int i=0; i<key.length(); i++) {
            if(col >= 5) {
                col = 0;
                row++;
            } 

            char c = key.charAt(i);
            int ic = (int) c;

            if(flag[ic]) continue; 

            arr[row][col++] = c;
            flag[ic] = true;
        }

        for(int i=65; i<=90; i++) {
            if(col >= 5) {
                col = 0;
                row++;
            }

            char c = (char) i;
            if(c == 'J') continue;

            if(flag[i]) continue;

            arr[row][col++] = c;
            flag[i] = true;
        }

        // 메시지 두 글자씩 나누기
        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<msg.length();) {
            if(i == msg.length() - 1) {
                String str = msg.charAt(i) + "X";
                
                list.add(str);
                break;
            }

            char a = msg.charAt(i);
            char b = msg.charAt(i+1);

            if(a != b) {
                String str = a + "" + b;
                list.add(str);
                i += 2;
                continue;
            }

            if(a == b) {
                String str = "";
                if(a != 'X') {
                    str = a + "X";
                } else {
                    str = a + "Q";
                }

                list.add(str);
                i++;
                continue;
            }
        }

        // 암호화
        String answer = "";

        for(String l: list) {
            char a = l.charAt(0);
            char b = l.charAt(1);

            int ax = 0; int ay = 0;
            int bx = 0; int by = 0;
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(arr[i][j] == a) {
                        ax = i;
                        ay = j;
                    }          
                    if(arr[i][j] == b) {
                        bx = i;
                        by = j;
                    }          
                }
            }

            if(ax == bx) { // 같은 행일 경우
                ay += 1;
                by += 1;

                if(by == 5) by = 0;
                if(ay == 5) ay = 0;
            }
            else if(ay == by) { // 같은 열일 경우
                ax += 1;
                bx += 1;

                if(ax == 5) ax = 0;
                if(bx == 5) bx = 0;
            }
            else { // 다른 행과 열일 경우
                int cy = ay;

                ay = by;
                by = cy;
            }

            char newA = arr[ax][ay];
            char newB = arr[bx][by];

            answer += newA + "" + newB;
        }

        System.out.println(answer);
    }
}