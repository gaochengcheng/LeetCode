package contest20;

import java.util.Arrays;

/**
 * 
 * @author chengcheng
 * @time 2017年2月19日 上午10:33:05
 *
 */
public class S526_BeautifulArrangement {
	
	int answer = 0;
    private void dfs(int N, boolean[] available, int pos) {
        if (pos > N) {
            answer++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (available[i] && (i % pos == 0 || pos % i == 0)) {
                available[i] = false;
                dfs(N, available, pos + 1);
                available[i] = true;
            }
        }
    }

    public int countArrangement(int N) {
        answer = 0;
        boolean[] available = new boolean[N + 1];
        Arrays.fill(available, true);   //见到一个新的函数，fill函数

        dfs(N, available, 1);
        return answer;
    }
}
