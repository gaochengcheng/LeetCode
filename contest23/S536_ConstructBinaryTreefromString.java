package contest23;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2017年3月12日 下午8:33:09
 *
 */
public class S536_ConstructBinaryTreefromString {
	//"4(2(3)(1))(6(5))"
	public TreeNode str2tree(String s) {
		if (s == null || s.length() == 0) {
            return null;
        }
        boolean negative = false;
        int num = 0;
        TreeNode res = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';  //string --> int 
            } else if (c == '(') {
                int start = i + 1;
                int open = 1;   //记录左右括号是否匹配的，出现一个'('，open+1。出现一个')'，open-1.
                i++;
                while (i < s.length() && open > 0) {
                    if (s.charAt(i) == '(') {
                        open++;
                    } else if (s.charAt(i) == ')') {
                        open--;
                    }
                    i++;
                }
                TreeNode child = str2tree(s.substring(start, i-1));
                if (res == null) {
                    // left child
                    res = new TreeNode(negative ? -num : num);
                    res.left = child;
                } else {
                    res.right = child;
                }
                i--;
            }
        }
        if (res == null) {  //构造叶子节点
            res = new TreeNode(negative ? -num : num);
        }
        return res;
    }
	@Test
	public void test(){
		String s = "4(2(3)(1))(6(5))";
		System.out.println(str2tree(s));
	}
}
