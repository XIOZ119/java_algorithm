import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        long rowMin = Long.MAX_VALUE;
        long rowMax = Long.MIN_VALUE;
        long colMin = Long.MAX_VALUE;
        long colMax = Long.MIN_VALUE;
        
        ArrayList<long[]> list = new ArrayList<>();
        
        for(int i=0; i<line.length; i++) {
            for(int j=i+1; j<line.length; j++) {
                int a = line[i][0];
                int b = line[i][1];
                int e = line[i][2];
                
                int c = line[j][0];
                int d = line[j][1];
                int f = line[j][2];
                
                long mother = (long) a*d - (long) b*c;
                if(mother == 0) continue; 
                
                long xChild = (long) b*f - (long) e*d;
                long yChild = (long) e*c - (long) a*f;
                
                if(xChild % mother != 0 || yChild % mother != 0) continue;
                
                long x = (long) xChild / mother;
                long y = (long) yChild / mother;
                
                rowMin = Math.min(rowMin, y);
                rowMax = Math.max(rowMax, y);
                colMin = Math.min(colMin, x);
                colMax = Math.max(colMax, x);
                
                list.add(new long[] {x, y});
            }
        }
        
        int width = (int) (colMax - colMin + 1);
        int height = (int) (rowMax - rowMin + 1);
        char[][] board = new char[height][width];
        
        for(int i=0; i<height; i++) {
            Arrays.fill(board[i], '.');
        }
        
        for(long[] l: list) {
            long x = l[0];
            long y = l[1];
            
            int row = (int) (rowMax - y);
            int col = (int) (x - colMin);
            
            board[row][col] = '*';
        }
        
        String[] answer = new String[height];
        
        for(int i=0; i<height; i++) {
            answer[i] = new String(board[i]);
        }
        
        return answer;
    }
}