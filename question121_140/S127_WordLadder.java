package question121_140;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月26日 下午7:10:35
 *
 */
public class S127_WordLadder {
	public int ladderLength(String start, String end, Set<String> dict) {
		if(start == null || end == null 
				|| start.length() == 0 || end.length() == 0
				|| start.length()!=end.length())
			return 0;
		
		LinkedList<String> wordQueue = new LinkedList<String>();
		int level = 1;  //the start string 	already count for 1
		int curnum = 1; // the candidate num on current level
		int nextnum = 0; //counter for next level
		
		wordQueue.add(start);
		while(!wordQueue.isEmpty()){
			String word = wordQueue.poll();
			curnum--;
			
			for(int i = 0; i < word.length(); i++){
				char[] wordunit = word.toCharArray();
				for(char j = 'a'; j <= 'z'; j++){
					wordunit[i] = j;
					String temp = new String(wordunit);
					
					if(temp.equals(end))
						return level+1;   //  if found, return the result. 
					if(dict.contains(temp)){
						System.out.println(temp);
						wordQueue.add(temp);
						nextnum++;
						dict.remove(temp);
					}
					
				}
			}
			System.out.println(nextnum);
			if(curnum == 0){
				curnum = nextnum;
				nextnum = 0;
				level++;
			}
		}
		return 0;
    }
	@Test
	public void test(){
		String start = "hit";
		String end = "cog";
		Set<String> hashSet = new HashSet<String>();
		//{"hot","dot","dog","lot","log"}
		hashSet.add("hot");
		hashSet.add("dot");
		hashSet.add("dog");
		hashSet.add("lot");
		hashSet.add("log");
//		hashSet.add("hzt");
		System.out.println(ladderLength(start, end, hashSet));
	}
}
