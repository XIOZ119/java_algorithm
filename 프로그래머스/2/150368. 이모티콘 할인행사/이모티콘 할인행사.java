import java.util.*;

class Solution {
    int[] rates = {10, 20, 30, 40};
    int emoLength, userLength;
    int[][] decreEmo;
    int joinNum, sales = 0;
    int[][] users;
    
    public int[] solution(int[][] users, int[] emoticons) {
        emoLength = emoticons.length; 
        userLength = users.length;  
        this.users = users;
        
        decreEmo = new int[emoLength][4];
        
        // 이모티콘 할인율 계산
        calculateRate(emoticons);
        
        // dfs (조합 생성)
        dfs(new ArrayList<Integer>());
        
        int[] answer = new int[] {joinNum, sales};
        return answer;
    }
    
    void buyEmotion(ArrayList<Integer> arr) {
        // joinNum(서비스 가입 수) , sales(매출액) 
        
        int usersCost = 0;
        int usersJoin = 0;
        
        for(int i=0; i<userLength; i++) {
            int sum = 0;
            for(int j=0; j<arr.size(); j++) {
                int sale = rates[arr.get(j)];
                
                // 유저가 구매하지 않을 할인율
                if(sale < users[i][0]) continue;
                
                // 금액 확인 -> sum 추가 혹은 sum 이 유저 기준보다 높다면 가입자 수 증가
                sum += decreEmo[j][arr.get(j)];
            }
            
            if(sum >= users[i][1]) usersJoin++;
            else usersCost += sum;
        }

        if(usersJoin > joinNum) {
            joinNum = usersJoin;
            sales = usersCost;
        } else if (usersJoin == joinNum) {
            if (sales < usersCost) sales = usersCost;
        }
    }
    
    void dfs(ArrayList<Integer> arr) {
        
        if(arr.size() == emoLength) {
            buyEmotion(arr);
            return;
        }
        
        for(int i=0; i<4; i++) {
            arr.add(i);
            dfs(arr);
            arr.remove(arr.size() - 1);
        }
    }
    
    void calculateRate(int[] originEmo) {
        for(int i=0; i<originEmo.length; i++) {
            for(int j=0; j<rates.length; j++) {
                int emoticon = originEmo[i];
                int rate = rates[j];
                
                decreEmo[i][j] = emoticon * (100 - rate) / 100;
            }
        }
    }
    
}