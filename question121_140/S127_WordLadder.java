package question121_140;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月26日 下午7:10:35
 *
 */
public class S127_WordLadder {
	public int ladderLength(String start, String end, Set<String> dict) {
		if (start == null || end == null || start.length() == 0 || end.length() == 0 || start.length() != end.length())
			return 0;

		LinkedList<String> wordQueue = new LinkedList<String>();
		int level = 1; // the start string already count for 1
		int curnum = 1; // the candidate num on current level
		int nextnum = 0; // counter for next level

		wordQueue.add(start);
		while (!wordQueue.isEmpty()) {
			String word = wordQueue.poll();
			curnum--;

			for (int i = 0; i < word.length(); i++) {
				char[] wordunit = word.toCharArray();
				for (char j = 'a'; j <= 'z'; j++) {
					wordunit[i] = j;
					String temp = new String(wordunit);

					if (temp.equals(end))
						return level + 1; // if found, return the result.
					if (dict.contains(temp)) {

						wordQueue.add(temp);
						nextnum++;
						dict.remove(temp);
					}

				}
			}
			if (curnum == 0) {
				curnum = nextnum;
				nextnum = 0;
				level++;
			}
		}
		return 0;
	}

	@Test
	public void test() {
		String start = "hit";
		String end = "cog";
		Set<String> hashSet = new HashSet<String>();
		// {"hot","dot","dog","lot","log"}
		hashSet.add("hot");
		hashSet.add("dot");
		hashSet.add("dog");
		hashSet.add("lot");
		hashSet.add("log");
		// hashSet.add("hzt");
		System.out.println(ladderLength_2(start, end, hashSet));
	}

	public int ladderLength_2(String beginWord, String endWord, Set<String> wordList) {

		Queue<State> q = new LinkedList<>();
		HashSet<State> visited = new HashSet<>(); // 判重

		final Function<State, Boolean> stateIsValid = (State s) -> wordList.contains(s.word) || s.word.equals(endWord);
		final Function<State, Boolean> stateIsTarget = (State s) -> s.word.equals(endWord);
		
		//lambda expression is a another way to write a function.
		final Function<State, HashSet<State>> stateExtend = (State s) -> {
			HashSet<State> result = new HashSet<>();

			char[] array = s.word.toCharArray();
			for (int i = 0; i < array.length; ++i) {
				final char old = array[i];
				for (char c = 'a'; c <= 'z'; c++) {
					// 防止同字母替换
					if (c == array[i])
						continue;

					array[i] = c;
					State newState = new State(new String(array), s.level + 1);

					if (stateIsValid.apply(newState) && !visited.contains(newState)) {
						result.add(newState);
					}
					array[i] = old; // 恢复该单词
				}
			}

			return result;
		};

		State startState = new State(beginWord, 0);
        q.offer(startState);   //add startState to the queue.
        
        visited.add(startState);
        while (!q.isEmpty()) {
            State state = q.poll();

            if (stateIsTarget.apply(state)) {
                return state.level + 1;
            }


            HashSet<State> newStates = stateExtend.apply(state);
            for (State newState : newStates) {
                q.offer(newState);
                visited.add(newState);
            }
        }
        return 0;
	}

}

class State {
	String word;
	int level;

	public State(String word, int level) {
		this.word = word;
		this.level = level;
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.hashCode() != obj.hashCode())
			return false;
		if (!(obj instanceof State))
			return false;

		return this.word.equals(((State) obj).word);
	}
}
