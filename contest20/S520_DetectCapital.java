package contest20;

import org.junit.Test;

/**
 * 
 * author:jzjsuper
 * 
 * @author chengcheng
 * @time 2017年2月19日 上午10:51:15
 *
 */
public class S520_DetectCapital {
		//这个方法太笨了
	public boolean detectCapitalUse(String word) {
		if(word == null || word.length() == 0)
			return false;
		
		char[] charArr = word.toCharArray();
		String newWord = word.toUpperCase();
		char[] newWordArr = newWord.toCharArray();
		if(charArr.length > 1){
			char c1 = charArr[0];
			char c2 = charArr[charArr.length-1];
			if(c1 >= 65 && c1 <= 90 && c2 >= 65 && c2 <=90){  //USA
				for(int i = 0; i<charArr.length; i++)
					if(charArr[i] - newWordArr[i] != 0)
						return false;
				
			}
			else if(c1 >= 65 && c1 <= 90 && c2 >= 97 && c2 <= 122){ //Google
				for(int i = 1; i<charArr.length; i++)
					if(charArr[i] < 97)
						return false;
			}
			else{
				for(int i=0; i<charArr.length; i++)
					if(charArr[i] <97)
						return false;
			}
				
		}
		
        return true;
    }
	
	public boolean detectCapitalUse2(String word) {
		int capitalCount = 0;
		for(char c : word.toCharArray()){
			if(Character.isUpperCase(c)){
				capitalCount++;
			}
		}
		
		if(capitalCount == word.length())
			return true;
		if(capitalCount == 0)
			return true;
		if(capitalCount == 1 && Character.isUpperCase(word.charAt(0)) && word.length() >1 )
			return true;
		
		return  false;
	}
	
	@Test
	public void test(){
		System.out.println(detectCapitalUse2("Ua"));
	}
}
