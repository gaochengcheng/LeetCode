package contest22;
/**
 * 
 * @author chengcheng
 * @time 2017年3月6日 下午7:54:00
 *
 */
public class S533_LonelyPixel_II {
	public int findBlackPixel(char[][] picture, int N) {
		
		int[] row = new int[picture.length];
		int[] col = new int[picture[0].length];
		int count = 0;
		for(int i=0; i<picture.length; i++){
			for(int j=0; j<picture[0].length; j++){
				if(picture[i][j]=='B'){
					++row[i];     //对出现B的行和列分别求和
					++col[j];
				}
			}
		}
		
		for(int i=0; i<picture.length; i++){
			if(row[i] == N){
				for(int j=0; j<picture[0].length; j++){
					//i行，j列都是N的B。  条件1.
					if(col[j]==N){
						
						boolean flag = true;
						for(int k=0; k<picture.length; k++){
							if(picture[k][j]=='B'){
								//比较i行和k行的所有元素是否相同   条件2.
								
								for(int index=0; index<picture[0].length; index++){
									if(picture[i][index]!=picture[k][index]){
										flag = false;
										break;
									}
								}
								
							}
						}
						if(flag == true)
							count++;
					}
				}
			}
		}
		
		
        return count;
    }
}
