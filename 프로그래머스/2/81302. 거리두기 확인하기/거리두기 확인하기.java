import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public int[] solution(String[][] places) {
        int length = 5; 
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        
        for(int i=0; i<5; i++) {
            List<int[]> list = new ArrayList<>();
            char[][] p = new char[5][5];
            
            for(int j=0; j<5; j++) {
                String str = places[i][j];
                for(int k=0; k<5; k++){
                    p[j][k] = str.charAt(k);
                    if(str.charAt(k) == 'P') {
                        list.add(new int[] {j, k});
                    }
                }
            }
            
            for(int j=0; j<list.size()-1; j++) {
                if(answer[i] == 0) break;
                
                int[] loc = list.get(j);
                int x = loc[0]; int y = loc[1];
                
                for(int k=j+1; k<list.size(); k++) {
                    int[] next = list.get(k);
                    int nx = next[0]; int ny = next[1];
                    
                    if(Math.abs(nx - x) + Math.abs(ny - y) == 2) {
                        if(ny == y) {
                            if(nx > x && p[x+1][y] != 'X') answer[i] = 0;
                            else if(nx < x && p[x-1][y] != 'X') answer[i] = 0;
                        }
                        else if(nx == x) {
                            if(ny > y && p[x][y+1] != 'X') answer[i] = 0;
                            else if(ny < y && p[x][y-1] != 'X') answer[i] = 0;
                        }
                        else if (nx != x && ny != y) {
                            if((nx > x && ny > y) && (p[x+1][y] != 'X' || p[x][y+1] != 'X')) answer[i] = 0;
                            else if((nx > x && ny < y) && (p[x+1][y] != 'X' || p[x][y-1] != 'X')) answer[i] = 0;
                            else if((nx < x && ny > y) && (p[x-1][y] != 'X' || p[x][y+1] != 'X')) answer[i] = 0;
                            else if((nx < x && ny < y) && (p[x-1][y] != 'X' || p[x][y-1] != 'X')) answer[i] = 0;
                        }
                    } else if(Math.abs(nx - x) + Math.abs(ny - y) <= 1) {
                        answer[i] = 0;
                        break;
                    }
                }
            }
            
        }
        
        return answer;
    }
}