package bishi;

import java.util.Arrays;
import java.util.Scanner;

public class RemoveNums {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String origNum = scanner.nextLine();
        String remove = scanner.nextLine();
        System.out.println(remainNum(origNum,Integer.parseInt(remove)));
    }
    public static String remainNum(String origNum,int remove) {
        if(origNum == null || origNum.length() <= 0 || remove >= origNum.length()) {
            return "";
        }
        char[] numbers = origNum.toCharArray();
        Arrays.sort(numbers);
        int index = origNum.indexOf(numbers[numbers.length-1]);
        if(remove <=0 ) {
            return origNum;
        }
        if(origNum.length() == 1 && remove == 1) {
            return "";
        }
        //当需要删除的位数大于等于最大数的下标时，此时index左边的数需要全部删除
        if(index <= remove) {
            if(index == origNum.length()-1){   //remove == origNum.length()-1也是可以的
            	//这种情况下，最大数的左边正好有remove位，此时需要把这些位全部移出，只保留最后一位
            	return String.valueOf(numbers[numbers.length-1]);
            	
            }
            else {
            	//最大数左边的位数小于remove的值，index左边的全部删掉还不够，还需要在index的右边继续删除remove-index位数字
                return String.valueOf(numbers[numbers.length-1])+remainNum(origNum.substring(index+1),remove -index);
            }
        } else {
        	//index左边的数需要有选择的删除，此时index右边的数全部保留
        	return remainNum(origNum.substring(0,index),remove)+ origNum.substring(index);
        }
    }

    
    public void test(){
    	/**
    	 * 第一组测试用例，输入123和1
    	 * 返回2+3的拼接字符串，也就是23.
    	 */
    	
    }
}
