
150. Evaluate Reverse Polish Notation

题目描述：
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

解决思路：
非常简单，通过栈结构存数据。遍历字符串数组，如果是数字，加入栈中，否则从栈中弹出两个元素，经过+-*/的计算后，在压入栈中。遍历结束，弹出栈中的元素即可

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0)) ||
                    (token.length() > 1 && token.charAt(0) == '-')) {
                stack.push(Integer.valueOf(token));
                continue;
            }
            int num1 = stack.pop();
            int num2 = stack.pop();
            int tempResult = 0;
            switch (token) {
                case "+":
                    tempResult = num2 + num1;
                    break;
                case "-":
                    tempResult = num2 - num1;
                    break;
                case "*":
                    tempResult = num2 * num1;
                    break;
                case "/":
                    tempResult = num2 / num1;
                    break;
            }
            stack.push(tempResult);
        }
        return stack.pop();
    }
}