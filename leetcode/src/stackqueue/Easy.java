package stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Easy {
    class MyQueue {
        /**
         * 232.用栈实现队列
         */
        Stack<Integer> in;
        Stack<Integer> out;
        /** Initialize your data structure here. */
        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            in.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (out.isEmpty()) {
                while (!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (out.isEmpty()) {
                while (!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }

        /**
         * 225.用队列实现栈
         * 提示：只能使用 push to back, peek/pop from front, size, 和 is empty
         */
        class MyStack {
            private Queue<Integer> queue;
            /** Initialize your data structure here. */
            public MyStack() {
                queue = new LinkedList<>();
            }

            /** Push element x onto stack. */
            public void push(int x) {
                queue.add(x);
                int size = queue.size();
                while (size-- > 1) {
                    queue.add(queue.poll());
                }
            }

            /** Removes the element on top of the stack and returns that element. */
            public int pop() {
                return queue.remove();
            }

            /** Get the top element. */
            public int top() {
                return queue.peek();
            }

            /** Returns whether the stack is empty. */
            public boolean empty() {
                return queue.isEmpty();
            }
        }
    }

    /**
     * 155.最小值栈
     */
    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;
        int min;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            min = Math.min(min, x);
            stack.push(x);
            minStack.push(min);
        }

        public void pop() {
            stack.pop();
            minStack.pop();
            min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 20.有效的括号
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (stack.isEmpty() || c == '(' || c == '{' || c == '[') {
                stack.add(c);
            }else {
                char left = stack.pop();
                if ((left == '(' && c == ')') || (left == '{' && c == '}') || (left == '[' && c == ']')) {
                    continue;
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Easy easy = new Easy();
        MyQueue myQueue = easy.new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
    }
}
