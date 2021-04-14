package core.stack;

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
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     * 解题思路：栈是先入后出，队列是先入先出
     * 入栈：临时栈用于存储传入的数据，其栈内顺序与输入顺序相反
     * 出栈：将临时栈的输入全部取出，放入栈2，栈2的顺序与输入顺序相同，正常出栈即打到了队列的效果
     */
    class MyQueue {
        Stack<Integer> mStack;
        Stack<Integer> mTmpStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            mStack = new Stack<>();
            mTmpStack = new Stack<>();
        }

        // 将临时栈的数据同步给栈2
        public void syncStack(){
            // * 如果栈2空了，才需要将临时栈的数据同步给栈2，否则先使用栈2的数据
            if (mStack.isEmpty()){
                while(!mTmpStack.isEmpty()){
                    mStack.push(mTmpStack.pop());
                }
            }
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            // 临时栈存放push的数据，pop或peek时，先将临时栈的数据取出并压入栈2，再进行pop或peek即可
            mTmpStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            syncStack();
            return mStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            syncStack();
            return mStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return mTmpStack.isEmpty() && mStack.isEmpty();
        }
    }
}