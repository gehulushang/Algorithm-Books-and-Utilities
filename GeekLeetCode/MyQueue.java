package GeekLeetCode;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> input = null;
    private Stack<Integer> output = null;
    public MyQueue(){
        input = new Stack<Integer>();
        output = new Stack<Integer>();

    }
    public void push(int x ){
        input.push(x);

    }
    public int pop(){
        peek();
        return output.pop();
    }

    public int peek(){
        if(output.isEmpty()){
            while(!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.peek();
    }
    public boolean empty(){
        return input.isEmpty()&&output.isEmpty();
    }
}
