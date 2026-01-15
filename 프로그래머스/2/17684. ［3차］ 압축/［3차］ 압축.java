import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        // 사전 초기화
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        int i = 0;
        while (i < msg.length()) {
            String baseStr = "";
            String str = String.valueOf(msg.charAt(i));
            int count = 1;
            boolean flag = true;

            while (map.containsKey(str)) {
                baseStr = str;

                if (i + count == msg.length()) { // 끝까지 도달
                    flag = false;
                    break;
                }

                str += msg.charAt(i + count); // 다음 글자 붙여보기
                count++;
            }

            // 항상 baseStr의 인덱스 출력
            list.add(map.get(baseStr));

            // 끝이 아니라 "사전에 없는 w+c"를 만든 경우에만 사전에 추가
            if (flag) {
                map.put(str, map.size() + 1);
            }

            // 다음 시작 위치로 이동 (매칭된 길이만큼)
            i += baseStr.length();
        }

        int[] answer = new int[list.size()];
        for (int j = 0; j < list.size(); j++) answer[j] = list.get(j);
        return answer;
    }
}