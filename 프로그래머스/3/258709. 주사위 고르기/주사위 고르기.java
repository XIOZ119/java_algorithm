import java.util.*;

class Solution {
    int n;
    int[][] dice;
    boolean[] selected;

    long maxWinCount = -1;
    int[] answer;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        this.selected = new boolean[n];
        this.answer = new int[n / 2];

        selectDice(0, 0);

        return answer;
    }

    // A가 가져갈 주사위 n / 2개 선택
    private void selectDice(int start, int count) {
        if (count == n / 2) {
            calculateWinCount();
            return;
        }

        for (int i = start; i < n; i++) {
            selected[i] = true;
            selectDice(i + 1, count + 1);
            selected[i] = false;
        }
    }

    // 현재 선택한 주사위 조합의 승리 횟수 계산
    private void calculateWinCount() {
        List<Integer> aDice = new ArrayList<>();
        List<Integer> bDice = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                aDice.add(i);
            } else {
                bDice.add(i);
            }
        }

        List<Integer> aSums = new ArrayList<>();
        List<Integer> bSums = new ArrayList<>();

        makeSums(0, 0, aDice, aSums);
        makeSums(0, 0, bDice, bSums);

        Collections.sort(bSums);

        long winCount = 0;

        for (int aSum : aSums) {
            // aSum보다 작은 B의 점수 개수
            winCount += lowerBound(bSums, aSum);
        }

        if (winCount > maxWinCount) {
            maxWinCount = winCount;

            int index = 0;

            for (int i = 0; i < n; i++) {
                if (selected[i]) {
                    // 문제의 주사위 번호는 1부터 시작
                    answer[index++] = i + 1;
                }
            }
        }
    }

    // 선택한 주사위들로 만들 수 있는 모든 합 생성
    private void makeSums(
            int depth,
            int sum,
            List<Integer> diceNumbers,
            List<Integer> sums
    ) {
        if (depth == diceNumbers.size()) {
            sums.add(sum);
            return;
        }

        int diceNumber = diceNumbers.get(depth);

        for (int face = 0; face < 6; face++) {
            makeSums(
                    depth + 1,
                    sum + dice[diceNumber][face],
                    diceNumbers,
                    sums
            );
        }
    }

    // 정렬된 배열에서 target 이상인 첫 번째 위치
    // 즉, target보다 작은 원소의 개수
    private int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}