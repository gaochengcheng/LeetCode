package contest22;

import org.junit.Test;

public class S531_LonelyPixelI {
	public int findLonelyPixel(char[][] picture) {
		
		int[] row = new int[picture.length];
		int[] col = new int[picture[0].length];
		
		for(int i=0; i<picture.length; i++){
			for(int j=0; j<picture[0].length; j++){
				if(picture[i][j]=='B'){
					++row[i];  //出现是B的位置，让B所在的行和列统统+1.
					++col[j];
				}
			}
		}
		int count=0;
		for(int i=0; i<picture.length; i++){
			for(int j=0; j<picture[0].length; j++ )
				if(picture[i][j] == 'B' && row[i] == 1 && col[j]==1)
					count++;
		}
        return count;
    }
	@Test
	public void test(){
		char[][] arr = {{'W','W','B'},{'W','W','B'},{'W','W','B'}};
	}
}
