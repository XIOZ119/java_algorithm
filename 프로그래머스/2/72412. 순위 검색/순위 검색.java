import java.util.*;

class Solution {
    static Map<String, List<Integer>> map;
    
    // info를 미리 가능한 조건 조합으로 저장하기
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        map = new HashMap<>();
        
        String[][] arr = new String[info.length][];
        for (int i = 0; i < info.length; i++) {
            arr[i] = info[i].split(" ");
            dfs(0, "", arr[i]);
        }
        
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        String[][] q = new String[query.length][];
        for(int i=0; i<query.length; i++) {
            q[i] = query[i].replaceAll(" and ", " ").split(" "); // " and " -> " " 바꾸기
        }
        
        for(int i=0; i<q.length; i++) {
            String str = "";
            for(int k=0; k<q[i].length-1; k++) {
                str += q[i][k];
            }
            int score = Integer.parseInt(q[i][4]);
            
            List<Integer> list = map.getOrDefault(str, new ArrayList<>());
                
            answer[i] = list.size() - lowerBound(list, score);
        }
        
        return answer;
    }
    
    static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
    
    static void dfs(int start, String str, String[] arr) {
        if(start == 4) {
            if(!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            } 
            
            map.get(str).add(Integer.parseInt(arr[4]));
            return;
        }
        
        dfs(start + 1, str + arr[start], arr);
        dfs(start + 1, str + "-", arr);
    }
}