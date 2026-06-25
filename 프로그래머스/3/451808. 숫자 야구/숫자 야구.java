import java.util.function.Function;
import java.util.*;

class Solution {
    private static ArrayList<Integer> list;
    private static int[][] score;

    public int solution(int n, Function<Integer, String> submit) {
        list = new ArrayList<>();
        comb("", new boolean[10], 0);

        int size = list.size();
        score = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                score[i][j] = getScore(list.get(i), list.get(j));
            }
        }

        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            candidates.add(i);
        }

        while (true) {
            int questionIdx = pick(candidates);
            int question = list.get(questionIdx);

            String result = submit.apply(question);

            if (result.equals("4S 0B")) return question;

            int s = result.charAt(0) - '0';
            int b = result.charAt(3) - '0';
            int resultScore = s * 5 + b;

            ArrayList<Integer> next = new ArrayList<>();

            for (int candIdx : candidates) {
                if (score[questionIdx][candIdx] == resultScore) {
                    next.add(candIdx);
                }
            }

            candidates = next;
        }
    }

    private static int pick(ArrayList<Integer> candidates) {
        if (candidates.size() == 1) {
            return candidates.get(0);
        }

        int bestQuestion = candidates.get(0);
        int bestWorst = Integer.MAX_VALUE;

        if (candidates.size() > 200) {
            for (int question : candidates) {
                int[] count = new int[25];

                for (int cand : candidates) {
                    count[score[question][cand]]++;
                }

                int worst = 0;
                for (int i = 0; i < 25; i++) {
                    worst = Math.max(worst, count[i]);
                }

                if (worst < bestWorst) {
                    bestWorst = worst;
                    bestQuestion = question;
                }
            }
        } else {
            for (int question = 0; question < list.size(); question++) {
                int[] count = new int[25];

                for (int cand : candidates) {
                    count[score[question][cand]]++;
                }

                int worst = 0;
                for (int i = 0; i < 25; i++) {
                    worst = Math.max(worst, count[i]);
                }

                if (worst < bestWorst) {
                    bestWorst = worst;
                    bestQuestion = question;
                }
            }
        }

        return bestQuestion;
    }

    private static int getScore(int guess, int answer) {
        String g = String.valueOf(guess);
        String a = String.valueOf(answer);

        int s = 0;
        int b = 0;

        for (int i = 0; i < 4; i++) {
            if (g.charAt(i) == a.charAt(i)) s++;
            else if (a.indexOf(g.charAt(i)) != -1) b++;
        }

        return s * 5 + b;
    }

    private static void comb(String number, boolean[] visited, int cnt) {
        if (cnt == 4) {
            list.add(Integer.parseInt(number));
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            comb(number + i, visited, cnt + 1);
            visited[i] = false;
        }
    }
}