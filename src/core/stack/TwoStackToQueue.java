package core.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 解题思路：栈是先入后出，队列是先入先出
 * 入栈：第一个栈用于存储传入的数据，其栈内顺序与输入顺序相反
 * 出栈：将第一个栈的输入全部取出，放入栈2，栈2的顺序与输入顺序相同，正常出栈即打到了队列的效果
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=117&&tqId=34998&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://www.runoob.com/java/java-stack-class.html
 */
public class TwoStackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}