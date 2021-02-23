package stack;

import java.util.Stack;

/**
 * 描述：
 * 题目描述
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 *
 * https://www.nowcoder.com/practice/37548e94a270412c8b9fb85643c8ccc2?tpId=117&&tqId=34973&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class Parenthesis {
    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public boolean isValid (String s) {
        /**
         * 描述：
         * 遍历字符，
         * 如果是左括号则入栈，
         * 如果右括号则判断栈顶是不是对应括号
         * 遍历结束后，如果栈空了，则说明匹配
         *
         * 我这里遇到左括号时，入栈了对应的右括号，出栈时只需要判断是不是相等即可
         */
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                stack.push(')');
            }else if (c == '['){
                stack.push(']');
            }else if (c == '{'){
                stack.push('}');
            }else if (stack.empty() || stack.pop()!=c){
                return false;
            }
        }

        return stack.empty();
    }
}
