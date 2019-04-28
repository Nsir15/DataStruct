package 栈;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * 输入: ["2", "1", "+", "3", "*"]
   输出: 9
   解释: ((2 + 1) * 3) = 9
 * 
 * 
 * @author nancy
 *
 */
public class _150_逆波兰表达式求值 {

	public static int evalRPN(String[] tokens) {
		
		//应该是先将中缀表达式转换成逆波兰表达式，然后根据规则入栈，进行计算。
		//不过leetCode这里直接传入的是逆波兰表达式，不需要转换的步骤，只需要执行计算的逻辑
		// 计算的逻辑可以参考这里的： https://juejin.im/entry/5af9b59b6fb9a07aa83edcdd
		
		//规则：遇到数字放入栈中，遇到符号则计算，计算过程pop出两个数字，第一个数字放在符号的右面，第二个放在左面。
		
		Set<String> operatorSet = new HashSet<String>();
		operatorSet.add("+");
		operatorSet.add("-");
        operatorSet.add("*");
        operatorSet.add("/");
        
		Stack<Integer> stack = new Stack<>();
		for (String itemString : tokens) {
			if (operatorSet.contains(itemString)) { //如果是运算符，pop出两个数字 进行计算
				int right = stack.pop();
				int left = stack.pop();
				stack.push(caculator(itemString, left, right));
			}else {
				stack.push(Integer.valueOf(itemString));
			}
		}
        return stack.pop();
    }
	
	private static int caculator(String operatorString,int left,int right) {
		int value = 0;
		switch (operatorString) {
		case "+":
			value = left + right;
			break;

		case "-":
			value = left - right;
			break;
		case "*":
			value = left * right;
			break;
		case "/":
			value = left / right;
			break;
		}
		return value;
	}
	
	public static void main(String[] args) {
		String[] strings = {"0","3","/"};//{"2", "1", "+", "3", "*"};
		System.out.println(evalRPN(strings));
	}
}
