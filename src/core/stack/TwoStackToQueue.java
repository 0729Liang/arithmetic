package core.stack;

import java.util.Queue;
import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 解题思路：栈是先入后出，队列是先入先出。
 * 入栈：临时栈用于存储传入的数据，其栈内顺序与输入顺序相反
 * 出栈：将临时栈的输入全部取出，放入栈2，栈2的顺序与输入顺序相同，正常出栈即打到了队列的效果
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=117&&tqId=34998&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://www.runoob.com/java/java-stack-class.html
 */
public class TwoStackToQueue {
    /**
      * 描述：
      * 两个栈实现，栈1存放当前数据，最小栈存放与栈1每个元素一一对应的最小找。
      * 我们只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。
      * 因此我们可以使用一个最小栈，与元素栈同步插入与删除，用于存储与每个元素对应的最小值。
      * 
      * 当一个元素要入栈时，我们取当前最小栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入最小栈中；
      * 当一个元素要出栈时，我们把最小栈的栈顶元素也一并弹出；
      * 在任意一个时刻，栈内元素的最小值就存储在最小栈的栈顶元素中。
      *
      */
    class MinStack {
        Stack<Integer> mStack;
        Stack<Integer> mMinStack;

        public MinStack() {
            mStack = new Stack<>();
            mMinStack = new Stack<>();
        }

        public void push(int val) {
            mStack.push(val);
            if (mMinStack.isEmpty()) {
                mMinStack.push(val);
            } else {
                // 非空，存入最小值
                mMinStack.push(Math.min(mMinStack.peek(), val));
            }
        }

        public void pop() {
            mStack.pop();
            mMinStack.pop();
        }

        public int top() {
            return mStack.peek();
        }

        public int getMin() {
            return mMinStack.peek();
        }
    }
}