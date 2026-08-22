import java.util.*;

class Solution {
    static HashSet<int[]> set;
    static int lockNum;
    static int x, y;
    static int keyX, keyY;
    static boolean answer;
    static int[][] lock;
    
    public boolean solution(int[][] key, int[][] lock) {
        set = new HashSet<>(); 
        lockNum = 0;
        x = lock.length; y = lock[0].length; 
        keyX = key.length; keyY = key[0].length;
        answer = false; 
        this.lock = lock; 
        
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key[i].length; j++) {
                if(key[i][j] != 1) continue;
                
                set.add(new int[]{i, j});
            }
        }
        
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock[i].length; j++) {
                if(lock[i][j] == 1) continue;
                
                lockNum++;
            }
        }
        
        rotate();
        
        return answer;
    }
    
    static void compare(ArrayList<int[]> list) {
        int cnt = 0; 
        for(int[] l: list) {
            int lx = l[0]; 
            int ly = l[1]; 
            
            if(lx < 0 || ly < 0 || lx >= x || ly >= y) continue;
            
            if(lock[lx][ly] == 0) cnt++;
            else return;
        }
        
        if(cnt == lockNum) answer = true;      
        return; 
    }
    
    static void dfsY(ArrayList<int[]> list) {
        for(int i=-(keyY - 1); i<y; i++) {
            if(answer) return;
            
            ArrayList<int[]> newList = new ArrayList<>();
            
            for(int[] l: list) {
                int lx = l[0];
                int ly = l[1];
                
                newList.add(new int[] {lx, ly+i}); 
            }
            
            compare(newList);
        }
    }
    
    static void dfsX(ArrayList<int[]> list) {
        for(int i= -(keyX - 1); i<x; i++) {
            if(answer) return;
            
            ArrayList<int[]> newList = new ArrayList<>();
            
            for(int[] l: list) {
                int lx = l[0];
                int ly = l[1];
                
                newList.add(new int[] {lx+i, ly});
            }
            
            dfsY(newList);
        }
    }
    
    static void rotate() {
        ArrayList<int[]> list = new ArrayList<>();
        
        for(int i=0; i<4; i++) {
            if(answer) return;
            
            if(i == 0) {
                for(int[] s: set) 
                    list.add(new int[] {s[0], s[1]});
                    
                dfsX(list);
                continue;
            }
            
            ArrayList<int[]> newList = new ArrayList<>();
            for(int[] l: list) {
                int lx = l[0];
                int ly = l[1];
                
                int nx = ly;
                int ny = keyY - lx - 1;
                
                newList.add(new int[] {nx, ny}); 
            }
            
            dfsX(newList);
            
            list = new ArrayList<>();
            for(int[] nl: newList) {
                list.add(nl);
            }
        }
    }
}