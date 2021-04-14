package core.stack;

import java.util.*;

public class GetMinStack {

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

        Deque<Integer> mStack; // 栈1
        Deque<Integer> minStack; // 最小栈

        /** initialize your data structure here. */
        public MinStack() {
            mStack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MAX_VALUE); // 需要先初始化一个默认值，防止取出空
        }

        public void push(int x) {
            mStack.push(x);
            minStack.push(Math.min(minStack.peek(),x));
        }

        public void pop() {
            mStack.pop();
            minStack.pop();
        }

        public int top() {
            return mStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
