package stack;

import java.util.*;

public class GetMinStack {

    /**
     * return a array which include all ans for op3
     *
     * @param op int整型二维数组 operator
     * @return int整型一维数组
     */
    public int[] getMinStack(int[][] op) {
        MyStack stack = new MyStack();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < op.length; i++) {
            if (op[i][0] == 1){
                stack.push(op[i][1]);
            }else if (op[i][0] == 2){
                stack.pop();
            }else if (op[i][0] == 3){
                list.add(stack.getMin());
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }


    interface IStack {
        void push(int value);

        Integer pop();

        Integer getMin();
    }

    private class MyStack implements IStack {

        private Deque<Integer> mStack;
        private int min = Integer.MAX_VALUE;

        public MyStack() {
            mStack = new ArrayDeque<>();
        }

        @Override
        public void push(int value) {
            mStack.push(value);
            min = Math.min(value, min);
        }

        @Override
        public Integer pop() {
            Integer res = mStack.pop();
            if (res != min) {
                return res;
            }
            min = Integer.MAX_VALUE;
            for (Integer item : mStack) {
                min = Math.min(min, item);
            }
            return res;
        }

        @Override
        public Integer getMin() {
            return min;
        }
    }
}
